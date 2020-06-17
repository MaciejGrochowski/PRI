package com.example.PRI.services.character.generator;

import com.example.PRI.entities.character.Character;
import com.example.PRI.enums.Race;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class WeightGenerator {
    public Map<String, String> generateWeight(Character character, HashMap<String, String> properties) {
        double averageWeight = Double.parseDouble(properties.get("averageWeight"));
        Random rand = new Random();
        double weight = averageWeight + rand.nextGaussian()*15;
        while(weight < 0.3*averageWeight || weight < 16 || weight > averageWeight*5) weight = averageWeight + rand.nextGaussian()*10;
        character.setWeight((int) Math.round(weight));
        Map<String, String> newProps = this.generateProperties(averageWeight, weight);
        return newProps;
    }

    private Map<String, String> generateProperties(double averageWeight, double weight) {
        HashMap<String, String> output = new HashMap<>();
        weight = weight/averageWeight;

        if(weight > 1.2 && weight < 1.5) output.put("Atletyczny", "0.1");
        if(weight < 0.8 || weight > 1.8 ) output.put("Atletyczny", "0");

        if(weight < 0.7 || weight > 1.7) output.put("Barczysty", "0");

        if(weight < 1) output.put("Kościsty", String.valueOf(1.0-weight));
        if(weight > 1) output.put("Kościsty", "0");

        if(weight > 1.2 && weight < 1.5) output.put("Muskularny", "0.15");
        if(weight < 0.8 || weight > 1.8 ) output.put("Muskularny", "0");

        if(weight > 0.95 && weight < 1.05) output.put("Ponętna figura", "0.05");

        if(weight > 1.3 && weight < 1.8) output.put("Potężna budowa ciała", "0.1");
        if(weight > 0.7 && weight < 0.9) output.put("Smukły", "0.15");
        if(weight > 1) output.put("Szeroka klatka piersiowa", "0.03");
        if(weight < 1) output.put("Wątły", String.valueOf(1.0-weight+0.05));
        if(weight > 1) output.put("Brzuchaty", "0.05");

        if(weight > 1.2) output.put("Piwny brzuch", "0.2");
        if(weight < 0.7) output.put("Piwny brzuch", "0");
        return output;
    }

    public Map<String, String> getProperties(Integer weight, HashMap<String, String> properties) {
        Map newProps = generateProperties(Double.parseDouble(properties.get("averageHeight")), Double.valueOf(weight));
        return newProps;
    }
}
