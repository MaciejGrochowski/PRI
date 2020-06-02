package com.example.PRI.services.character.generator;

import com.example.PRI.entities.character.Apperance;
import com.example.PRI.entities.character.Character;
import com.example.PRI.enums.Religion;
import com.example.PRI.exceptions.CharacterGenerationException;
import com.example.PRI.services.GeneralService;
import com.example.PRI.services.character.ApperanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ApperanceGenerator extends GeneralService {


    @Autowired
    ApperanceService apperanceService;

    public Map<String, String> generateApperances(Character character, HashMap<String, String> properties) {
        Map<String, String> output = new HashMap<>();
        List<Apperance> apperances = apperanceService.findAll();
        apperances = apperances.stream().filter(a -> !properties.containsKey(a.getName()) || (properties.containsKey(a.getName()) && !properties.get(a.getName()).equals("0"))).collect(Collectors.toList());
        Random rand = new Random();
        List<Apperance> characterApperances = new ArrayList<>();

        if(character.getCurrentCareer().getName().equals("Kapłan") || character.getCurrentCareer().getName().equals("Wybraniec boży") || character.getCurrentCareer().getName().equals("Arcykapłan")
        && character.getReligion().equals(Religion.SIGMAR))
            apperances.stream().filter(a -> a.getName().equals("Łysy")).findFirst().ifPresent(characterApperances::add);

        for (Apperance apperance : apperances) {
            if (properties.containsKey(apperance.getName())) {
                double chance = Double.parseDouble(properties.get(apperance.getName()));
                if (rand.nextDouble() < chance) {
                    if (characterApperances.stream().noneMatch(d -> d.getType().equals(apperance.getType())))
                        characterApperances.add(apperance);
                }
            }
        }
        int apperanceCount = randomApperanceCount(rand.nextDouble());
        while (characterApperances.size() < apperanceCount) {
            Apperance newApperance = this.getApperanceRandom(apperances);
            boolean noMatch = characterApperances.stream().map(Apperance::getType).noneMatch(e -> e.equals(newApperance.getType()));
            if (noMatch) characterApperances.add(newApperance);
        }
        if(characterApperances.size() > apperanceCount){
            Collections.shuffle(characterApperances);
            characterApperances = characterApperances.subList(0, apperanceCount);
        }
        character.setApperance(characterApperances);
        return output;


    }

    private Apperance getApperanceRandom(List<Apperance> apperances) {
        double sum = apperances.stream().mapToDouble(Apperance::getProbability).sum();
        double randomRoll = new Random().nextDouble() * sum;

        for(Apperance apperance: apperances){
            randomRoll -= apperance.getProbability();
            if(randomRoll<=0) return apperance;
        }
        throw new CharacterGenerationException("Stworzono nullowy wygląd!", new NullPointerException());
    }

    private int randomApperanceCount(double randomRoll) {
        if (randomRoll < 0.01) return 0;
        if (randomRoll < 0.05) return 1;
        if (randomRoll < 0.30) return 2;
        if (randomRoll < 0.65) return 3;
        if (randomRoll < 0.85) return 4;
        return 5;

    }

    public Map<String, String> getProperties(ApperanceGenerator service, List<Apperance> apperance) {
        return new HashMap<>();
    }
}
