package com.example.PRI.services.character.generator;

import com.example.PRI.entities.character.Character;
import com.example.PRI.entities.character.Name;
import com.example.PRI.entities.character.Surname;
import com.example.PRI.enums.Race;
import com.example.PRI.enums.Sex;
import com.example.PRI.exceptions.CharacterGenerationException;
import com.example.PRI.services.GeneralService;
import com.example.PRI.services.RandomService;
import com.example.PRI.services.character.NameService;
import com.example.PRI.services.character.SurnameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.example.PRI.services.character.generator.MapperJsonStringToMap.mapJsonStringToMap;

@Service
public class SurnameGenerator extends GeneralService {

    @Autowired
    SurnameService surnameService;

    @Autowired
    NameService nameService;

    RandomService randomService;

    public HashMap<String, String> generateSurname(Character character, RandomService randomService, HashMap<String, String> properties) {
        this.randomService = randomService;
        Double surnameTypeRand = randomService.nextDouble();
        List<Surname> surnames = null;
        Surname outputSurname = null;
        HashMap<String, String> newProps = new HashMap<>();
        if (character.getRace().equals(Race.HALFLING) && surnameTypeRand <= 0.3) {
            surnames = surnameService.findHalflingSurnames();
        } else if (character.getRace().equals(Race.ELF) && surnameTypeRand <= 0.25) {
            surnames = surnameService.findElfSurnames();
        } else if (character.getRace().equals(Race.HUMAN) && surnameTypeRand <= 0.1) {
            surnames = surnameService.findHumanSurnames();
        } else if (character.getRace().equals(Race.DWARF))
            outputSurname = this.generateDwarfSurname(character.getSex());
        if (outputSurname == null) outputSurname = this.generateElfHalflingHumanSurname(surnames);
        character.setSurname(outputSurname);
        if (outputSurname != null && outputSurname.isGentry()) newProps.put("isGentry", "1");
        return newProps;
    }

    private Surname generateElfHalflingHumanSurname(List<Surname> surnames) {
        if (surnames == null) return null;
        Double surnameTypeRand = randomService.nextDouble();
        surnames = surnames.stream().filter(Surname::isUsedByGenerator).collect(Collectors.toList());
        double randRoll = surnameTypeRand;
        Collections.shuffle(surnames);

        for (Surname surname : surnames) {
            randRoll -= surname.getProbability();
            if (randRoll <= 0) return surname;
        }
        throw new CharacterGenerationException("Suma prawdopodobieństw nazwisk mniejsza od 1", new Exception());

    }

    private Surname generateDwarfSurname(Sex sex) {
        Double surnameTypeRand = randomService.nextDouble();
        List<Name> names = nameService.findByIsDwarf();
        names = names.stream().filter(Name::isUsedByGenerator).collect(Collectors.toList());
        Collections.shuffle(names);
        double randomRoll = surnameTypeRand;
        Name generatedName = null;
        for (Name name : names) {
            randomRoll -= name.getProbabilityNotGentry();
            if (randomRoll <= 0) {
                generatedName = name;
                break;
            }
        }
        if (generatedName == null)
            throw new CharacterGenerationException("Suma prawdopodobieństw imion krasnoludzkich jest mniejsza od 1", new Exception());
        String surnameStr = generatedName.getName() + (sex.equals(Sex.MALE) ? "sson" : "sdotr");
        Surname surname = new Surname(surnameStr, sex.equals(Sex.MALE), sex.equals(Sex.FEMALE), false, false, true, false, false,
                true, 0);
        surnameService.save(surname);
        return surname;
    }

    public Map<String, String> getProperties(Surname surname) {
        Map<String, String> newProps = new HashMap<>();
        if (surname.isGentry()) newProps.put("isGentry", "1");
        return newProps;
    }
}
