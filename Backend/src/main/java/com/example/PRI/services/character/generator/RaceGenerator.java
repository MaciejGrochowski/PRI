package com.example.PRI.services.character.generator;

import com.example.PRI.entities.character.Character;
import com.example.PRI.enums.PlaceType;
import com.example.PRI.enums.Race;
import com.example.PRI.services.RandomService;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.example.PRI.services.character.generator.MapperJsonStringToMap.mapJsonStringToMap;


public class RaceGenerator {

    RandomService randomService;

    public HashMap<String, String> generateRace(Character character, RandomService randomService, HashMap<String,String> properties) {
        this.randomService = randomService;
        Double raceTypeRand = randomService.nextDouble();
        Race r = null;
        for (Race race : Race.values()) {
            if (properties.containsKey(race.name())) {
                double chance = Double.parseDouble(properties.get(race.name()));
                if (raceTypeRand <= chance) {
                    r = race;
                }
            }
        }
//TODO character birthplace must work
        if (r == null && properties.containsKey("isForest") && raceTypeRand < 0.08) r = Race.ELF;
        if (r == null && properties.containsKey("isMountains") && raceTypeRand < 0.1) r = Race.DWARF;
//        if (r == null && (character.getBirthPlace().getPlaceType().equals(PlaceType.CITYSTATE) || properties.containsKey("isCapitol")) && raceTypeRand < 0.01) r = Race.HALFLING;
//        if (r == null && (character.getBirthPlace().getPlaceType().equals(PlaceType.CITYSTATE) || properties.containsKey("isCapitol")) && raceTypeRand < 0.01) r = Race.DWARF;
        if (r == null && properties.containsKey("isSea") && raceTypeRand < 0.01) r = Race.ELF;
        if (r == null && properties.containsKey("isRiver") && raceTypeRand < 0.005) r = Race.ELF;
 //       if (r == null && character.getBirthPlace().getPlaceType().equals(PlaceType.VILLIAGE) && raceTypeRand < 0.04) r = Race.HALFLING;


        if (r == null) {
                double randomRoll = raceTypeRand;
                if (randomRoll <= 0.90) r = Race.HUMAN;
                else if (randomRoll <= 0.94) r = Race.HALFLING;
                else if (randomRoll <= 0.98) r = Race.DWARF;
                else r = Race.ELF;
            }
        character.setRace(r);
        return (HashMap<String, String>) mapJsonStringToMap(r.getProperties());


    }

    public Map<String, String> getProperties(Race race) {
        return mapJsonStringToMap(race.getProperties());
    }
}
