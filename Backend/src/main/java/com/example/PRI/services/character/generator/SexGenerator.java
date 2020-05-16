package com.example.PRI.services.character.generator;

import com.example.PRI.entities.character.Character;
import com.example.PRI.enums.Sex;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.example.PRI.services.character.generator.MapperJsonStringToMap.mapJsonStringToMap;

public class SexGenerator {
    public Map<String, String> generateSex(Character character, HashMap<String, String> properties) {
        Sex output = null;
        Random random = new Random();
        for (Sex sex : Sex.values()){
            if(properties.containsKey(sex.getName())){
                if(random.nextDouble() <= Double.parseDouble(properties.get(sex.getName())))
                    output = sex;
            }
        }

        if(output==null){
            if(random.nextDouble() <= 0.5) output=Sex.MALE;
            else output = Sex.FEMALE;
        }
        character.setSex(output);

        return mapJsonStringToMap(output.getProperties());




    }
}
