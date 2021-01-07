package com.example.PRI.services.character.generator;

import com.example.PRI.entities.character.Character;
import com.example.PRI.enums.Race;
import com.example.PRI.services.RandomService;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class HeightGenerator {

    RandomService randomService;
    public Map<String, String> generateHeight(Character character, RandomService randomService, HashMap<String, String> properties) {
        this.randomService = randomService;
        Double heightTypeRand = randomService.nextGaussian();
        double averageHeight = Double.parseDouble(properties.get("averageHeight"));
        double height = averageHeight + heightTypeRand*7;
        while(height < 0.6*averageHeight || height > averageHeight*1.4) height = averageHeight + heightTypeRand*5;
        character.setHeight((int) Math.round(height));
        Map<String, String> newProps = this.generateProperties(height, averageHeight);
        return newProps;
    }

    private Map<String, String> generateProperties(double height, double averageHeight) {
        Map<String, String> output = new HashMap<>();
        if(height > averageHeight*1.1){
            output.put("Wysoki", "1");
        }
        else if(height > averageHeight*1.2){
            output.put("Olbrzym", "1");
        }
        else if(height < averageHeight*0.9){
            output.put("Niski", "1");
            output.put("Garbus", "0.08");
        }

        return output;
    }

    public Map<String, String> getProperties(Integer height, HashMap<String, String> properties) {
        Map newProps = generateProperties(Double.valueOf(height), Double.parseDouble(properties.get("averageHeight")));
        return newProps;
    }
}
