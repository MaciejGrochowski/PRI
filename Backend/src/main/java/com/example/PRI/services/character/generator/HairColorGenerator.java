package com.example.PRI.services.character.generator;

import com.example.PRI.entities.character.Character;
import com.example.PRI.entities.character.HairColor;
import com.example.PRI.enums.Race;
import com.example.PRI.services.GeneralService;
import com.example.PRI.services.character.HairColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HairColorGenerator extends GeneralService {

    @Autowired
    HairColorService hairColorService;

    public Map<String, String> generateHairColor(Character character, HashMap<String, String> properties) {
        List<HairColor> hairColors = hairColorService.findAll();
        Collections.shuffle(hairColors);
        Map<String, String> newProps = new HashMap<>();
        double randomRoll = new Random().nextDouble();
        boolean strangeColor = (new Random().nextDouble()) < 0.0002;
        HairColor generated = null;
        if (!strangeColor) {
            for (HairColor hairColor : hairColors) {
                if (properties.containsKey("Włosy " + hairColor.getColor())) {
                    double chance = Double.parseDouble(properties.get("Włosy " + hairColor.getColor()));
                    if (new Random().nextDouble() < chance) {
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
                    randomRoll -= chance;
                    if (randomRoll <= 0) {
                        generated = hairColor;
                        break;
                    }
                }
            }
        } else {
            generated = hairColors.get(0);
            System.err.println("Wygenerowano ultra rzadki kolor - nie jest to zaimplementowane..."); //ToDo implement strange colors
            newProps.put("hairColor", generated.getColor());
        }
        character.setHairColor(generated);
        return newProps;
    }
}
