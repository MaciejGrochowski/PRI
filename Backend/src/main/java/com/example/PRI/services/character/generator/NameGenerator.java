package com.example.PRI.services.character.generator;

import com.example.PRI.entities.character.Character;
import com.example.PRI.entities.character.Name;
import com.example.PRI.enums.Race;
import com.example.PRI.enums.Sex;
import com.example.PRI.services.GeneralService;
import com.example.PRI.services.RandomService;
import com.example.PRI.services.character.NameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class NameGenerator extends GeneralService {

    @Autowired
    NameService nameService;

    RandomService randomService;

    public Map<String, String> generateName(Character character, RandomService randomService, HashMap<String, String> properties) {
        this.randomService = randomService;
        List<Name> allNames = nameService.findAll(); //ToDo mikrooptymalizacja w zapytaniu, findNameBySexAndRaceAndIsUsedByGenerator


        allNames = allNames.stream().filter(n -> n.isUsedByGenerator() && n.isMale() == character.getSex().equals(Sex.MALE)
                && n.isHuman() == character.getRace().equals(Race.HUMAN) && n.isElf() == character.getRace().equals(Race.ELF)
                && n.isDwarf() == character.getRace().equals(Race.DWARF) && n.isHalfling() == character.getRace().equals(Race.HALFLING)
        ).collect(Collectors.toList());

        if(character.getSurname()!= null && character.getSurname().isGentry()) character.setName(this.generateGentryName(allNames));
        else character.setName(this.generateNotGentryName(allNames));


        return new HashMap<>();
    }

    private Name generateNotGentryName(List<Name> allNames) {
        double maxRandomRoll = allNames.stream().mapToDouble(Name::getProbabilityNotGentry).sum();
        double randomRoll = randomService.nextDouble() * maxRandomRoll;
        for(Name name : allNames){
            randomRoll -= name.getProbabilityNotGentry();
            if(randomRoll <= 0) return name;
        }
        return null;
    }

    private Name generateGentryName(List<Name> allNames) {
        double maxRandomRoll = allNames.stream().mapToDouble(Name::getProbabilityGentry).sum();
        double randomRoll = randomService.nextDouble() * maxRandomRoll;

        for(Name name : allNames){
            randomRoll -= name.getProbabilityGentry();
            if(randomRoll <= 0) return name;
        }
        return null;
    }

    public Map<String, String> getProperties(Name name) {
        return new HashMap<String, String>();
    }
}
