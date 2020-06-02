package com.example.PRI.services.character.generator;

import com.example.PRI.entities.character.Career;
import com.example.PRI.entities.character.CareerTalent;
import com.example.PRI.entities.character.Character;
import com.example.PRI.entities.character.Talent;
import com.example.PRI.enums.Race;
import com.example.PRI.services.GeneralService;
import com.example.PRI.services.character.CareerTalentService;
import com.example.PRI.services.character.TalentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TalentGenerator extends GeneralService {

    @Autowired
    TalentService talentService;

    @Autowired
    CareerTalentService careerTalentService;

    public Map<String, String> generateTalents(Character character, HashMap<String, String> properties) {
        List<Talent> talents = talentService.findAll();
        List<String> raceTalents = this.generateRaceTalents(character.getRace());
        List<Talent> characterTalents = talents.stream().filter(t -> raceTalents.contains(t.getName())).collect(Collectors.toList());
        Random rand = new Random();

        for(Talent talent: talents){
            if(properties.containsKey(talent.getName())){
                double chance = rand.nextDouble();
                if(chance < Double.parseDouble(properties.get(talent.getName()))){
                    characterTalents.add(talent);
                }
            }
        }
        if(character.getPreviousCareers().size() == 0) characterTalents.addAll(generateAllTalentsFromCareer(character.getCurrentCareer(), characterTalents));
        else{
            for(Career career: character.getPreviousCareers()){
                characterTalents.addAll(generateAllTalentsFromCareer(career, characterTalents));
            }
            characterTalents.addAll(this.generateSomeTalentsFromCareer(character.getCurrentCareer(), characterTalents));
        }
        characterTalents = characterTalents.stream().distinct().collect(Collectors.toList());
        character.setTalents(characterTalents);
        return new HashMap<>();
    }

    private List<Talent> generateAllTalentsFromCareer(Career career, List<Talent> talentsHad) {
        List<Talent> output = new ArrayList<>();
        List<CareerTalent> careerTalentsData = careerTalentService.findByCareer(career);
        careerTalentsData = careerTalentsData.stream().filter(t -> !talentsHad.contains(t.getTalent())).collect(Collectors.toList());
        List<Integer> types = careerTalentsData.stream().map(CareerTalent::getType).distinct().collect(Collectors.toList());
        for(int type: types){
            List<CareerTalent> talents = careerTalentsData.stream().filter(c -> c.getType()==type).collect(Collectors.toList());
            output.add(randomTalentFromTalentCarrer(talents));
        }

        return output;
    }

    private List<Talent> generateSomeTalentsFromCareer(Career career, List<Talent> talentsHad) {
        List<Talent> output = new ArrayList<>();
        List<CareerTalent> careerTalentsData = careerTalentService.findByCareer(career);
        careerTalentsData = careerTalentsData.stream().filter(t -> !talentsHad.contains(t.getTalent())).collect(Collectors.toList());
        List<Integer> types = careerTalentsData.stream().map(CareerTalent::getType).distinct().collect(Collectors.toList());
        Random rand = new Random();
        for(int type: types){
            if(rand.nextDouble() < 0.5) continue;
            List<CareerTalent> talents = careerTalentsData.stream().filter(c -> c.getType()==type).collect(Collectors.toList());
            output.add(randomTalentFromTalentCarrer(talents));
        }
        return output;
    }

    private Talent randomTalentFromTalentCarrer(List<CareerTalent> talents) {
        double maxRandom = talents.stream().mapToDouble(CareerTalent::getProbability).sum();
        double randomRoll = new Random().nextDouble() * maxRandom;

        for(CareerTalent careerTalent : talents){
            randomRoll -= careerTalent.getProbability();
            if(randomRoll<=0) return careerTalent.getTalent();
        }
        return null;
    }

    private List<String> generateRaceTalents(Race race) {
        List<String> output = new ArrayList<>();
        Random rand = new Random();

        if(race.equals(Race.HUMAN)){
            output.add(randomHumanTalent(output));
            output.add(randomHumanTalent(output));
        }

        if(race.equals(Race.ELF)){
            if(rand.nextDouble() < 0.5) output.add("Broń specjalna (długi łuk)");
            else output.add("Zmysł magii");
            output.add("Bystry wzrok");
            if(rand.nextDouble() < 0.5) output.add("Opanowanie");
            else output.add("Błyskotliwość");
            output.add("Widzenie w ciemności");
        }

        if(race.equals(Race.DWARF)){
            output.add("Krasnoludzki fach");
            output.add("Krzepki");
            output.add("Odporność na magię");
            output.add("Odwaga");
            output.add("Widzenie w ciemności");
            output.add("Zapiekła nienawiść");
        }

        if(race.equals(Race.HALFLING)){
            output.add("Broń specjalna (proca)");
            output.add("Odporność na Chaos");
            output.add("Widzenie w ciemności");
            output.add(randomHumanTalent(output));
        }
            return output;
    }

    private String randomHumanTalent(List<String> talentsHad) {
        List<String> humanTalentNames = Arrays.asList("Bardzo silny", "Bardzo szybki", "Błyskotliwość", "Bystry wzrok",
                "Charyzmatyczny", "Czuły słuch", "Geniusz arytmetyczny", "Krzepki", "Naśladowca", "Niezwykle odporny",
                "Oburęczność", "Odporność na choroby", "Odporność na magię", "Odporność na trucizny", "Odporność psychiczna",
                "Opanowanie", "Strzelec wyborowy", "Szczęście", "Szósty zmysł", "Szybki refleks", "Twardziel", "Urodzony wojownik",
                "Widzenie w ciemności");
        humanTalentNames = humanTalentNames.stream().filter(t -> !talentsHad.contains(t)).collect(Collectors.toList());
        return humanTalentNames.get(new Random().nextInt(humanTalentNames.size()));
    }

    public Map<String, String> getProperties(Character character, List<Talent> talents) {
        return new HashMap<>();
    }
}
