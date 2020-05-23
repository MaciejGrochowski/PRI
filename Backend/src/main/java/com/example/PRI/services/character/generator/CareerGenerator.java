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

    public Map<String, String> buildFirstCareer(Character character, HashMap<String, String> properties) {
        List<Career> careerList = careerService.findAllBaseCareers();

        for(Career career : careerList){
            String chanceStr = properties.get(career.getName());
            Double chance = 0.0;
            if(chanceStr == null) chance = 0.001;
            else chance = Double.valueOf(chanceStr);
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

        List<String> careerNames = careerList.stream().map(Career::getName).collect(Collectors.toList());

        double probability = 0;
//        System.err.println(character.getRace().getName());
//        System.err.println(character.getBirthPlace().getProperties());
//        System.err.println(character.getBirthPlace().getPlaceType());
        for(String careerName : careerNames){
            if(properties.containsKey(careerName)){
                probability += Double.parseDouble(properties.get(careerName));
//                System.out.println(careerName + ": " + properties.get(careerName));
            }
        }
//        System.err.println(probability);
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
        character.setCurrentCareer(randomCareer);

        return mapJsonStringToMap(randomCareer.getProperties());
    }

    private int getDominatingStatsCount(String dominatingStat) {
        return (int) dominatingStat.chars().filter(c -> c == '+').count();
    }
}
