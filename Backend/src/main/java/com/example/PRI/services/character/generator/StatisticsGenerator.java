package com.example.PRI.services.character.generator;

import com.example.PRI.entities.character.Character;
import com.example.PRI.entities.character.Statistics;
import com.example.PRI.services.RandomService;

import java.util.*;
import java.util.stream.Collectors;

public class StatisticsGenerator {

    RandomService randomService;

    public Map<String, String> generateBaseStats(Character character,RandomService randomService, HashMap<String, String> properties) {
        Statistics generated = new Statistics();
        Map<String, String> output = new HashMap<>();
        this.randomService = randomService;
        generated.setWeaponSkill(22 + randomService.nextInt(10) + randomService.nextInt(10));
        generated.setBallisticSkill(22 + randomService.nextInt(10) + randomService.nextInt(10));
        generated.setStrength(22 + randomService.nextInt(10) + randomService.nextInt(10));
        generated.setToughness(22 + randomService.nextInt(10) + randomService.nextInt(10));
        generated.setIntelligence(22 + randomService.nextInt(10) + randomService.nextInt(10));
        generated.setAgility(22 + randomService.nextInt(10) + randomService.nextInt(10));
        generated.setWillPower(22 + randomService.nextInt(10) + randomService.nextInt(10));
        generated.setFellowship(22 + randomService.nextInt(10) + randomService.nextInt(10));
        generated.setAttacks(1);
        generated.setMovement(4);
        generated.setMagic(0);
        generated.setWounds(randomWounds());
        List<String> dominatingStats = setDominatingStats(generated);
        List<String> antiDominatingStats = setAntiDominatingStats(generated);
        aplicateProperties(generated, properties);
        dominatingStats.addAll(setDominatingStats(generated));
        antiDominatingStats.addAll(setAntiDominatingStats(generated));
        String dominating = dominatingStats.stream().distinct().collect(Collectors.joining(","));
        String antiDominating = antiDominatingStats.stream().distinct().collect(Collectors.joining(","));
        output.put("dominatingStats", dominating);
        output.put("antiDominatingStats", antiDominating);

        character.setBaseStats(generated);

    return output;
    }
    
    private List<String> getStatsNamesByValue(Statistics stats, int minimum) {
        List<String> output = new ArrayList<>();
        List<Integer> secondaryMaximum = new ArrayList<>();

        if(stats.getWeaponSkill() == minimum) output.add("WW"); else secondaryMaximum.add(stats.getWeaponSkill());
        if(stats.getBallisticSkill() == minimum) output.add("US");  else secondaryMaximum.add(stats.getBallisticSkill());
        if(stats.getStrength() == minimum) output.add("K"); else secondaryMaximum.add(stats.getStrength());
        if(stats.getToughness() == minimum) output.add("ODP"); else secondaryMaximum.add(stats.getToughness());
        if(stats.getIntelligence() == minimum) output.add("INT"); else secondaryMaximum.add(stats.getIntelligence());
        if(stats.getAgility() == minimum) output.add("ZR"); else secondaryMaximum.add(stats.getAgility());
        if(stats.getWillPower() == minimum) output.add("SW"); else secondaryMaximum.add(stats.getWillPower());
        if(stats.getFellowship() == minimum) output.add("OGD");  else secondaryMaximum.add(stats.getFellowship());
        if(output.size() < 2){
            minimum = Collections.max(secondaryMaximum);
            if(stats.getWeaponSkill() == minimum) output.add("WW");
            if(stats.getBallisticSkill() == minimum) output.add("US");
            if(stats.getStrength() == minimum) output.add("K");
            if(stats.getToughness() == minimum) output.add("ODP");
            if(stats.getIntelligence() == minimum) output.add("INT");
            if(stats.getAgility() == minimum) output.add("ZR");
            if(stats.getWillPower() == minimum) output.add("SW");
            if(stats.getFellowship() == minimum) output.add("OGD");

        }
        return output;
    }

    private List<String> setAntiDominatingStats(Statistics stats) {
        int minimum =  Collections.min(Arrays.asList(
                stats.getWeaponSkill(),
                stats.getBallisticSkill(),
                stats.getStrength(),
                stats.getToughness(),
                stats.getAgility(),
                stats.getIntelligence(),
                stats.getWillPower(),
                stats.getFellowship()
        ));
        return getStatsNamesByValue(stats, minimum);
    }
    
    private List<String> setDominatingStats(Statistics stats) {
        int maximum =  Collections.max(Arrays.asList(
                stats.getWeaponSkill(),
                stats.getBallisticSkill(),
                stats.getStrength(),
                stats.getToughness(),
                stats.getAgility(),
                stats.getIntelligence(),
                stats.getWillPower(),
                stats.getFellowship()
        ));
        return getStatsNamesByValue(stats, maximum);
    }

    private Statistics aplicateProperties(Statistics generated, Map<String, String> properties) {
        if(properties.containsKey("WeaponSkill")) generated.setWeaponSkill(generated.getWeaponSkill() + Integer.parseInt(properties.get("WeaponSkill")));
        if(properties.containsKey("BallisticSkill")) generated.setBallisticSkill(generated.getBallisticSkill() + Integer.parseInt(properties.get("BallisticSkill")));
        if(properties.containsKey("Strength")) generated.setStrength(generated.getStrength() + Integer.parseInt(properties.get("Strength")));
        if(properties.containsKey("Toughness")) generated.setToughness(generated.getToughness() + Integer.parseInt(properties.get("Toughness")));
        if(properties.containsKey("Agility")) generated.setAgility(generated.getAgility() + Integer.parseInt(properties.get("Agility")));
        if(properties.containsKey("Intelligence")) generated.setIntelligence(generated.getIntelligence() + Integer.parseInt(properties.get("Intelligence")));
        if(properties.containsKey("WillPower")) generated.setWillPower(generated.getWillPower() + Integer.parseInt(properties.get("WillPower")));
        if(properties.containsKey("Fellowship")) generated.setFellowship(generated.getFellowship() + Integer.parseInt(properties.get("Fellowship")));
        if(properties.containsKey("Wounds")) generated.setWounds(generated.getWounds() + Integer.parseInt(properties.get("Wounds")));
        if(properties.containsKey("Movement")) generated.setMovement(generated.getMovement() + Integer.parseInt(properties.get("Movement")));
        return generated;
    }

    private int randomWounds() {
        int randomRoll = randomService.nextInt(10) + 1;
        if (randomRoll <= 3) return 10;
        if (randomRoll <= 6) return 11;
        if (randomRoll <= 9) return 12;
        return 13;
    }

    public Map<String, String> getProperties(Statistics generated, Map<String, String> properties) {
        Map<String, String> output = new HashMap<>();
        List<String> dominatingStats = setDominatingStats(generated);
        List<String> antiDominatingStats = setAntiDominatingStats(generated);
//        aplicateProperties(generated, properties);
        dominatingStats.addAll(setDominatingStats(generated));
        antiDominatingStats.addAll(setAntiDominatingStats(generated));
        String dominating = dominatingStats.stream().distinct().collect(Collectors.joining(","));
        String antiDominating = antiDominatingStats.stream().distinct().collect(Collectors.joining(","));
        output.put("dominatingStats", dominating);
        output.put("antiDominatingStats", antiDominating);

        return output;
    }
}
