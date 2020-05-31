package com.example.PRI.services.character.generator;

import com.example.PRI.entities.character.Character;
import com.example.PRI.enums.PlaceType;
import com.example.PRI.enums.Race;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.example.PRI.services.character.generator.MapperJsonStringToMap.mapJsonStringToMap;

public class RaceGenerator {
    public Map<String, String> generateRace(Character character, HashMap<String, String> properties) {
        Race r = null;
        Random random = new Random();
        for (Race race : Race.values()) {
            if (properties.containsKey(race.name())) {
                double chance = Double.parseDouble(properties.get(race.name()));
                if (random.nextDouble() <= chance) {
                    r = race;
                }
            }
        }

        if (r == null && properties.containsKey("isForest") && random.nextDouble() < 0.08) r = Race.ELF;
        if (r == null && properties.containsKey("isMountains") && random.nextDouble() < 0.1) r = Race.DWARF;
        if (r == null && character.getBirthPlace().getPlaceType().equals(PlaceType.CITYSTATE) || properties.containsKey("isCapitol") && random.nextDouble() < 0.01) r = Race.HALFLING;
        if (r == null && character.getBirthPlace().getPlaceType().equals(PlaceType.CITYSTATE) || properties.containsKey("isCapitol") && random.nextDouble() < 0.01) r = Race.DWARF;
        if (r == null && properties.containsKey("isSea") && random.nextDouble() < 0.01) r = Race.ELF;
        if (r == null && properties.containsKey("isRiver") && random.nextDouble() < 0.005) r = Race.ELF;
        if (r == null && character.getBirthPlace().getPlaceType().equals(PlaceType.VILLIAGE) && random.nextDouble() < 0.04) r = Race.HALFLING;


        if (r == null) {
                double randomRoll = random.nextDouble();
                if (randomRoll <= 0.90) r = Race.HUMAN;
                else if (randomRoll <= 0.94) r = Race.HALFLING;
                else if (randomRoll <= 0.98) r = Race.DWARF;
                else r = Race.ELF;
            }
        character.setRace(r);
        return mapJsonStringToMap(r.getProperties());


    }

    public Map<String, String> getProperties(Race race) {
        return mapJsonStringToMap(race.getProperties());
    }
}
