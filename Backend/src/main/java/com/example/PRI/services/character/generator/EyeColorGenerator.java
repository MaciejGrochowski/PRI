package com.example.PRI.services.character.generator;

import com.example.PRI.entities.character.Character;
import com.example.PRI.entities.character.EyeColor;
import com.example.PRI.enums.Race;
import com.example.PRI.services.GeneralService;
import com.example.PRI.services.character.EyeColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EyeColorGenerator extends GeneralService {

    @Autowired
    EyeColorService eyeColorService;

    public Map<String, String> generateEyeColor(Character character, HashMap<String, String> properties) {
        List<EyeColor> eyeColors = eyeColorService.findAll();
        Collections.shuffle(eyeColors);
        Map<String, String> newProps = new HashMap<>();
        double randomRoll = new Random().nextDouble();
        boolean strangeColor = (new Random().nextDouble() ) < 0.0005;
        EyeColor generated = null;
        if(!strangeColor) {
            for (EyeColor eyeColor : eyeColors) {
                double chance=0;
                if(character.getRace().equals(Race.ELF)) chance=eyeColor.getChanceIfElf();
                if(character.getRace().equals(Race.HALFLING)) chance=eyeColor.getChangeIfHalfling();
                if(character.getRace().equals(Race.DWARF)) chance=eyeColor.getChanceIfDwarf();
                if(character.getRace().equals(Race.HUMAN)) chance=eyeColor.getChanceIfHuman();
                randomRoll-=chance;
                if(randomRoll <= 0){
                    generated = eyeColor;
                    break;
                }
            }
        }
        else{
            List<EyeColor> notNormalColors = eyeColors.stream().filter(e -> e.getChanceIfHuman() == 0 && e.getChanceIfDwarf() == 0 && e.getChanceIfElf() == 0 && e.getChangeIfHalfling() == 0).collect(Collectors.toList());
            generated = notNormalColors.get(new Random().nextInt(notNormalColors.size()));
            //ToDo dopisać do JSON ultra-rzadkie kolory
            newProps.put("eyeColor", generated.getColor());
        }
        character.setEyeColor(generated);
        return newProps;
    }
}
