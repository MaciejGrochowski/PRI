package com.example.PRI.services.character.generator;

import com.example.PRI.entities.character.Apperance;
import com.example.PRI.entities.character.Character;
import com.example.PRI.entities.character.Personality;
import com.example.PRI.exceptions.CharacterGenerationException;
import com.example.PRI.services.GeneralService;
import com.example.PRI.services.RandomService;
import com.example.PRI.services.character.PersonalityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PersonalityGenerator extends GeneralService {

    @Autowired
    PersonalityService personalityService;

    RandomService randomService;

    public Map<String, String> generatePersonalities(Character character, RandomService randomService, HashMap<String, String> properties) {
        this.randomService = randomService;
        Map<String, String> output = new HashMap<>();
        List<Personality> personalities = personalityService.findAll();
        personalities = personalities.stream().filter(a -> !properties.containsKey(a.getName()) || (properties.containsKey(a.getName()) && !properties.get(a.getName()).equals("0"))).collect(Collectors.toList());
        List<Personality> characterPersonalities = new ArrayList<>();

        for (Personality personality : personalities) {
            if (properties.containsKey(personality.getName())) {
                double chance = Double.parseDouble(properties.get(personality.getName()));
                if (randomService.nextDouble() < chance) {
                    if (characterPersonalities.stream().noneMatch(d -> d.getType().equals(personality.getType())))
                        characterPersonalities.add(personality);
                }
            }
        }
        int personalityCount = randomPersonalityCount(randomService.nextDouble());
        while (characterPersonalities.size() < personalityCount) {
            Personality newPersonality = this.getPersonalityRandom(personalities);
            boolean noMatch = characterPersonalities.stream().map(Personality::getType).noneMatch(e -> e.equals(newPersonality.getType()));
            if (noMatch) characterPersonalities.add(newPersonality);
        }
        if(characterPersonalities.size() > personalityCount){
            Collections.shuffle(characterPersonalities, randomService.getRandom());
            characterPersonalities = characterPersonalities.subList(0, personalityCount);
        }
        character.setPersonality(characterPersonalities);
        return output;
    }


    private Personality getPersonalityRandom(List<Personality> personalities) {
        double sum = personalities.stream().mapToDouble(Personality::getProbability).sum();
        double randomRoll = randomService.nextDouble() * sum;

        for(Personality personality: personalities){
            randomRoll -= personality.getProbability();
            if(randomRoll<=0) return personality;
        }
        throw new CharacterGenerationException("Stworzono nullowy charakter!", new NullPointerException());
    }

    private int randomPersonalityCount(double randomRoll) {
        if (randomRoll < 0.01) return 0;
        if (randomRoll < 0.20) return 1;
        if (randomRoll < 0.60) return 2;
        if (randomRoll < 0.90) return 3;
        return 4;
    }

    public Map<String, String> getProperties(Character character, List<Personality> personalities) {
        return new HashMap<>();
    }
}
