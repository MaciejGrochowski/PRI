package com.example.PRI.services.character.generator;

import com.example.PRI.entities.character.Career;
import com.example.PRI.entities.character.Character;
import com.example.PRI.entities.character.Statistics;
import com.example.PRI.entities.character.Talent;
import com.example.PRI.exceptions.CharacterGenerationException;
import com.example.PRI.services.RandomService;

import java.util.*;
import java.util.stream.Collectors;

public class CareerStatisticsGenerator {
    RandomService randomService;
    public Map<String, String> generateCareerStatisticsGenerator(Character character, RandomService randomService, HashMap<String, String> properties) {
        this.randomService = randomService;
        List<Career> allCareers = new ArrayList<>(character.getPreviousCareers());
        allCareers.add(character.getCurrentCareer());
        Set<Integer> weaponSkillMaxFromCareers = new HashSet<>();
        Set<Integer> ballisticSkillMaxFromCareers = new HashSet<>();
        Set<Integer> strengthMaxFromCareers = new HashSet<>();
        Set<Integer> toughnessMaxFromCareers = new HashSet<>();
        Set<Integer> agilityMaxFromCareers = new HashSet<>();
        Set<Integer> intelligenceMaxFromCareers = new HashSet<>();
        Set<Integer> willPowerMaxFromCareers = new HashSet<>();
        Set<Integer> fellowshipMaxFromCareers = new HashSet<>();
        Set<Integer> attacksSkillMaxFromCareers = new HashSet<>();
        Set<Integer> woundsSkillMaxFromCareers = new HashSet<>();
        Set<Integer> movementSkillMaxFromCareers = new HashSet<>();
        Set<Integer> magicSkillMaxFromCareers = new HashSet<>();

        if(allCareers.size() > 1){
            weaponSkillMaxFromCareers.add(allCareers.get(0).getStatistics().getWeaponSkill());
            ballisticSkillMaxFromCareers.add(allCareers.get(0).getStatistics().getBallisticSkill());
            strengthMaxFromCareers.add(allCareers.get(0).getStatistics().getStrength());
            toughnessMaxFromCareers.add(allCareers.get(0).getStatistics().getToughness());
            agilityMaxFromCareers.add(allCareers.get(0).getStatistics().getAgility());
            intelligenceMaxFromCareers.add(allCareers.get(0).getStatistics().getIntelligence());
            willPowerMaxFromCareers.add(allCareers.get(0).getStatistics().getWillPower());
            fellowshipMaxFromCareers.add(allCareers.get(0).getStatistics().getFellowship());
            attacksSkillMaxFromCareers.add(allCareers.get(0).getStatistics().getAttacks());
            woundsSkillMaxFromCareers.add(allCareers.get(0).getStatistics().getWounds());
            movementSkillMaxFromCareers.add(allCareers.get(0).getStatistics().getMovement());
            magicSkillMaxFromCareers.add(allCareers.get(0).getStatistics().getMagic());
        }
        for(Career c : allCareers){
            Statistics stats = c.getStatistics();
            if(stats.getWeaponSkill() > 0) weaponSkillMaxFromCareers.add(randomService.nextInt(stats.getWeaponSkill()/5 +1)*5);
            if(stats.getBallisticSkill() > 0) ballisticSkillMaxFromCareers.add(randomService.nextInt(stats.getBallisticSkill()/5 +1)*5);
            if(stats.getStrength() > 0) strengthMaxFromCareers.add(randomService.nextInt(stats.getStrength()/5 +1)*5);
            if(stats.getToughness() > 0) toughnessMaxFromCareers.add(randomService.nextInt(stats.getToughness()/5 +1)*5);
            if(stats.getAgility() > 0) agilityMaxFromCareers.add(randomService.nextInt(stats.getAgility()/5 +1)*5);
            if(stats.getIntelligence() > 0) intelligenceMaxFromCareers.add(randomService.nextInt(stats.getIntelligence()/5 +1)*5);
            if(stats.getWillPower() > 0) willPowerMaxFromCareers.add(randomService.nextInt(stats.getWillPower()/5 +1)*5);
            if(stats.getFellowship() > 0) fellowshipMaxFromCareers.add(randomService.nextInt(stats.getFellowship()/5 +1)*5);
            if(stats.getAttacks() > 0) attacksSkillMaxFromCareers.add(randomService.nextInt(stats.getAttacks() +1));
            if(stats.getWounds() > 0) woundsSkillMaxFromCareers.add(randomService.nextInt(stats.getWounds() +1));
            if(stats.getMovement() > 0) movementSkillMaxFromCareers.add(randomService.nextInt(stats.getMovement() +1));
            if(stats.getMagic() > 0) magicSkillMaxFromCareers.add(randomService.nextInt(stats.getMagic()));
        }
        if (character.getBaseStats() == null) throw new CharacterGenerationException("By wylosować obecne umiejętności bojowe, wprowadź poprawne bazowe umiejętności bojowe.", new IllegalArgumentException());
        weaponSkillMaxFromCareers.stream().mapToInt(v -> v).max().ifPresentOrElse(s -> character.setEndWeaponSkills(character.getBaseStats().getWeaponSkill() + s), () -> character.setEndWeaponSkills(character.getBaseStats().getWeaponSkill()));
        ballisticSkillMaxFromCareers.stream().mapToInt(v -> v).max().ifPresentOrElse(s -> character.setEndBallisticSkills(character.getBaseStats().getBallisticSkill() + s), () -> character.setEndBallisticSkills(character.getBaseStats().getBallisticSkill()));
        strengthMaxFromCareers.stream().mapToInt(v -> v).max().ifPresentOrElse(s -> character.setEndStrength(character.getBaseStats().getStrength() + s), () -> character.setEndStrength(character.getBaseStats().getStrength()));
        toughnessMaxFromCareers.stream().mapToInt(v -> v).max().ifPresentOrElse(s -> character.setEndToughness(character.getBaseStats().getToughness() + s), () -> character.setEndToughness(character.getBaseStats().getToughness()));
        agilityMaxFromCareers.stream().mapToInt(v -> v).max().ifPresentOrElse(s -> character.setEndAgility(character.getBaseStats().getAgility() + s), () -> character.setEndAgility(character.getBaseStats().getAgility()));
        intelligenceMaxFromCareers.stream().mapToInt(v -> v).max().ifPresentOrElse(s -> character.setEndIntelligence(character.getBaseStats().getIntelligence() + s), () -> character.setEndIntelligence(character.getBaseStats().getIntelligence()));
        willPowerMaxFromCareers.stream().mapToInt(v -> v).max().ifPresentOrElse(s -> character.setEndWillPower(character.getBaseStats().getWillPower() + s), () -> character.setEndWillPower(character.getBaseStats().getWillPower()));
        fellowshipMaxFromCareers.stream().mapToInt(v -> v).max().ifPresentOrElse(s -> character.setEndFellowship(character.getBaseStats().getFellowship() + s), () -> character.setEndFellowship(character.getBaseStats().getFellowship()));
        attacksSkillMaxFromCareers.stream().mapToInt(v -> v).max().ifPresentOrElse(s -> character.setEndAttacks(character.getBaseStats().getAttacks() + s), () -> character.setEndAttacks(character.getBaseStats().getAttacks()));
        woundsSkillMaxFromCareers.stream().mapToInt(v -> v).max().ifPresentOrElse(s -> character.setEndWounds(character.getBaseStats().getWounds() + s), () -> character.setEndWounds(character.getBaseStats().getWounds()));
        movementSkillMaxFromCareers.stream().mapToInt(v -> v).max().ifPresentOrElse(s -> character.setEndMovement(character.getBaseStats().getMovement() + s), () -> character.setEndMovement(character.getBaseStats().getMovement()));
        magicSkillMaxFromCareers.stream().mapToInt(v -> v).max().ifPresentOrElse(s -> character.setEndMagic(character.getBaseStats().getMagic() + s), () -> character.setEndMagic(character.getBaseStats().getMagic()));

        List<String> talentNames = character.getTalents().stream().map(Talent::getName).collect(Collectors.toList());
        if(talentNames.contains("Bardzo silny")) character.setEndStrength(character.getEndStrength()+5);
        if(talentNames.contains("Bardzo szybki")) character.setEndMovement(character.getEndMovement()+1);
        if(talentNames.contains("Błyskotliwość")) character.setEndIntelligence(character.getEndIntelligence()+5);
        if(talentNames.contains("Charyzmatyczny")) character.setEndFellowship(character.getEndFellowship()+5);
        if(talentNames.contains("Niezwykle odporny")) character.setEndToughness(character.getEndToughness()+5);
        if(talentNames.contains("Opanowanie")) character.setEndWillPower(character.getEndWillPower()+5);
        if(talentNames.contains("Strzelec wyborowy")) character.setEndBallisticSkills(character.getEndBallisticSkills()+5);
        if(talentNames.contains("Szybki refleks")) character.setEndAgility(character.getEndAgility()+5);
        if(talentNames.contains("Twardziel")) character.setEndWounds(character.getEndWounds()+1);
        if(talentNames.contains("Urodzony wojownik")) character.setEndWeaponSkills(character.getEndWeaponSkills()+5);
        return new HashMap<>();
    }
}
