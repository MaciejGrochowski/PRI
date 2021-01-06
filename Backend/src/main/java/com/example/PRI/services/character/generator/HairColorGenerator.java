package com.example.PRI.services.character.generator;

import com.example.PRI.entities.character.Character;
import com.example.PRI.entities.character.EyeColor;
import com.example.PRI.entities.character.HairColor;
import com.example.PRI.enums.Race;
import com.example.PRI.services.GeneralService;
import com.example.PRI.services.RandomService;
import com.example.PRI.services.character.HairColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class HairColorGenerator extends GeneralService {

    @Autowired
    HairColorService hairColorService;

    RandomService randomService;

    public Map<String, String> generateHairColor(Character character, RandomService randomService, HashMap<String, String> properties) {
        this.randomService = randomService;
        Double hairColorTypeRand = randomService.nextDouble();
        List<HairColor> hairColors = hairColorService.findAll();
        Collections.shuffle(hairColors, randomService.getRandom());
        Map<String, String> newProps = new HashMap<>();
        boolean strangeColor = (hairColorTypeRand < 0.0002);
        HairColor generated = null;
        if (!strangeColor) {
            for (HairColor hairColor : hairColors) {
                if (properties.containsKey("Włosy " + hairColor.getColor())) {
                    double chance = Double.parseDouble(properties.get("Włosy " + hairColor.getColor()));
                    if (randomService.nextDouble() < chance) {
                        generated = hairColor;
                    }
                }
            }

            if (generated == null) {
                for (HairColor hairColor : hairColors) {
                    double chance = 0;
                    if (character.getRace().equals(Race.ELF)) chance = hairColor.getChanceIfElf();
                    if (character.getRace().equals(Race.HALFLING)) chance = hairColor.getChangeIfHalfling();
                    if (character.getRace().equals(Race.DWARF)) chance = hairColor.getChanceIfDwarf();
                    if (character.getRace().equals(Race.HUMAN)) chance = hairColor.getChanceIfHuman();
                    hairColorTypeRand -= chance;
                    if (hairColorTypeRand <= 0) {
                        generated = hairColor;
                        break;
                    }
                }
            }
        } else {
            List<HairColor> notNormalColors = hairColors.stream().filter(e -> e.getChanceIfHuman() == 0 && e.getChanceIfDwarf() == 0 && e.getChanceIfElf() == 0 && e.getChangeIfHalfling() == 0).collect(Collectors.toList());
            generated = notNormalColors.get(randomService.nextInt(notNormalColors.size()));
            //ToDo dopisać do JSON ultra-rzadkie kolory
            newProps.put("hairColor", generated.getColor());
        }
        character.setHairColor(generated);
        return newProps;
    }

    public Map<String, String> getProperties(HairColor hairColor) {
        Map<String, String> newProps = new HashMap<>();
        newProps.put("hairColor",hairColor.getColor());
        return newProps;

    }
}
