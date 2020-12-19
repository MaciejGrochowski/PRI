package com.example.PRI.services.character.generator;

import com.example.PRI.entities.character.Character;
import com.example.PRI.enums.Sex;
import com.example.PRI.services.RandomService;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.example.PRI.services.character.generator.MapperJsonStringToMap.mapJsonStringToMap;

public class SexGenerator {

    RandomService randomService;

    public HashMap<String, String> generateSex(Character character, RandomService randomService, HashMap<String, String> properties) {
        this.randomService = randomService;
        Double sexTypeRand = randomService.nextDouble();
        Sex output = null;
        for (Sex sex : Sex.values()){
            if(properties.containsKey(sex.getName())){
                if(sexTypeRand <= Double.parseDouble(properties.get(sex.getName())))
                    output = sex;
            }
        }

        if(output==null){
            if(sexTypeRand <= 0.5) output=Sex.MALE;
            else output = Sex.FEMALE;
        }
        character.setSex(output);

        return (HashMap<String, String>) mapJsonStringToMap(output.getProperties());




    }

    public Map<String, String> getProperties(Sex sex) {
        return mapJsonStringToMap(sex.getProperties());
    }
}
