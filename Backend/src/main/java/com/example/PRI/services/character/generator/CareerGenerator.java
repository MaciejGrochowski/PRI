package com.example.PRI.services.character.generator;

import com.example.PRI.entities.character.Career;
import com.example.PRI.entities.character.Character;
import com.example.PRI.exceptions.CharacterGenerationException;
import com.example.PRI.services.GeneralService;
import com.example.PRI.services.character.CareerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.example.PRI.services.character.generator.MapperJsonStringToMap.mapJsonStringToMap;

@Service
public class CareerGenerator extends GeneralService {

    @Autowired
    CareerService careerService;
    //ToDo Strongly test after get data jsons

    private Career getFirstCareer(Map<String, String> properties){
        List<Career> careerList = careerService.findAllBaseCareers();
        List<String> careerNames = careerList.stream().map(Career::getName).collect(Collectors.toList());
        double probability = 0;
        for(String careerName : careerNames){
            if(properties.containsKey(careerName)){
                probability += Double.parseDouble(properties.get(careerName));
            }
        }
        double randomRoll = new Random().nextDouble() * probability;
        Career randomCareer = null;

        for(Career career: careerList){
            randomRoll -= Double.parseDouble(properties.get(career.getName()));
            if(randomRoll <= 0){
                randomCareer = career;
                break;
            }
        }
        if(randomCareer==null) throw new CharacterGenerationException("Pierwsza profesja się nie wygenerowała", new IllegalStateException());
        return randomCareer;

    }

    public Map<String, String> buildFirstCareer(Character character, HashMap<String, String> properties) {
        List<Career> careerList = careerService.findAllBaseCareers();

        List<Career> tmp = careerService.findAll();
        for(Career c : tmp){
            try{
                Map<String, String> careerProperties = mapJsonStringToMap(c.getProperties());
            }
            catch (Exception e){
                System.out.println(c.getName());
                System.out.println(e.getMessage());
                System.out.println("----");
            }

        }

        for(Career career : careerList){
            String chanceStr = properties.get(career.getName());
            Double chance = 0.0;

            if(chanceStr == null) chance = 0.001;
            else chance = Double.valueOf(chanceStr);


            Map<String, String> careerProperties = mapJsonStringToMap(career.getProperties());

            if(careerProperties.get("isNotVilliage")!=null && properties.get("isVilliage")!=null){
                chance = chance/10;
            }
            else{
                for(String careerProperty : careerProperties.keySet()){
                    if(properties.get(careerProperty)!=null && careerProperty.startsWith("is")){
                        chance = chance*2;
                    }
                }
            }




            int dominatingStatsCount = this.getDominatingStatsCount(career.getDominatingStat());
            Double dominatingStatMultiplier = 2 - 0.1*dominatingStatsCount;
            Double antiDominatingStatMultiplier = 0.4 + 0.1*dominatingStatsCount;
            String characterDominatingStats = properties.get("dominatingStats");
            String characterAntiDominatingStats = properties.get("antiDominatingStats");
            List<String> careerDominatingStats = Arrays.asList(career.getDominatingStat().split(","));

            for(String careerDominatingStat: careerDominatingStats){
                String statName = careerDominatingStat.split("\\+")[0];
                if(characterDominatingStats.contains(statName)) chance = chance * dominatingStatMultiplier;
                if(characterAntiDominatingStats.contains(statName)) chance = chance * antiDominatingStatMultiplier;
            }
            properties.put(career.getName(), String.valueOf(chance));
        }

        Career randomCareer = this.getFirstCareer(properties);
        character.setCurrentCareer(randomCareer);
        return mapJsonStringToMap(randomCareer.getProperties());
    }

    private int getDominatingStatsCount(String dominatingStat) {
        return (int) dominatingStat.chars().filter(c -> c == '+').count();
    }

    public List<Map<String, String>> buildNextCareers(Character character, HashMap<String, String> properties) {
        Career firstCareer = character.getCurrentCareer();
        Career currentCareer = character.getCurrentCareer();
        List<Career> allCareers = new ArrayList<>();
        allCareers.add(firstCareer);
        Random rand = new Random();
        List<Map<String, String>> newProps = new ArrayList<>();

        while(true){
            if(rand.nextDouble() > allCareers.get(allCareers.size()-1).getExitChance()) break;
            Career nextCareer = this.buildNextCareer(allCareers, currentCareer, properties);
            allCareers.add(nextCareer);
        }

        for(Career career: allCareers){
            newProps.add(mapJsonStringToMap(career.getProperties()));
        }

        character.setCurrentCareer(allCareers.get(allCareers.size()-1));
        allCareers = allCareers.subList(0, allCareers.size()-1);
        character.setPreviousCareers(allCareers.subList(0, allCareers.size()));

        return newProps;
    }

    private Career buildNextCareer(List<Career> allCareers, Career currentCareer, HashMap<String, String> properties) {
        Career output = null;

        while (output == null) { //ToDo mikrooptymalizacja[jeśli np. prof nie ma nextBestCareer, to w 45% przypadków pętla będzie się powtarzać
            double randomRoll = new Random().nextDouble();
            if (randomRoll < 0.45) output = getNextBestCareer(currentCareer);
            else if (randomRoll < 0.75) output = getNextNotBaseCareer(currentCareer);
            else if (randomRoll < 0.95) output = getNextBaseCareer(currentCareer, properties);
            else output = getFirstCareer(properties);
            if(output!=null && properties.getOrDefault(output.getName(),"").equals("0")) output=null; //Jeśli profesja zakazana, spróbuj jeszcze raz
            if(allCareers.contains(output)) output=null; //Jeśli profesja już wystąpiła, spróbuj jeszcze raz
    }
        return output;
    }

    private Career getNextBaseCareer(Career currentCareer, HashMap<String, String> properties) {
        List<Career> careers = currentCareer.getCareerExits().stream().filter(Career::isBaseProfession).collect(Collectors.toList());
        Collections.shuffle(careers);
        for(Career career : careers){
            if(properties.containsKey(career.getName())){
                if(new Random().nextDouble() < Double.parseDouble(properties.get(career.getName()))) return career;
            }
        }
        if(careers.size() > 0) return careers.get(new Random().nextInt(careers.size()));
        return null;
    }

    private Career getNextNotBaseCareer(Career currentCareer) {
        List<Career> careers = currentCareer.getCareerExits().stream().filter(c -> !c.isBaseProfession()).collect(Collectors.toList());;
        if(careers.size()>0) return careers.get(new Random().nextInt(careers.size()));
        return null;
    }

    private Career getNextBestCareer(Career currentCareer) { //ToDo otestować po wprowadzeniu danych do JSONa
        List<String> careersBest = Arrays.asList(currentCareer.getBestNextCareer().split(","));
        List<Career> careers = currentCareer.getCareerExits().stream().filter(c -> careersBest.contains(c.getName())).collect(Collectors.toList());;
        if(careers.size() > 0) return careers.get(new Random().nextInt(careers.size()));
        return null;
    }


    public Map<String, String> getProperties(List<Career> careers) {
        HashMap <String, String> output = new HashMap<>();
        for(Career caeer : careers){
            output.putAll(mapJsonStringToMap(caeer.getProperties()));
        }
        return output;
    }

    public Map<String, String> buildCurrentCareer(List<Career> previousCareers, HashMap<String, String> properties, Character character) {
      character.setCurrentCareer(this.buildNextCareer(previousCareers, previousCareers.get(previousCareers.size()- 1), properties));
      return mapJsonStringToMap(character.getCurrentCareer().getProperties());
    }
}
