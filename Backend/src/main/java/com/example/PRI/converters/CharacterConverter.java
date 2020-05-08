package com.example.PRI.converters;

import com.example.PRI.dtos.characters.CharacterDefaultAttributesOutputDto;
import com.example.PRI.dtos.characters.CharacterDetailsOutputDto;
import com.example.PRI.dtos.characters.SkillOutputDto;
import com.example.PRI.dtos.characters.StatisticsOutputDto;
import com.example.PRI.entities.character.*;
import com.example.PRI.entities.character.Character;
import com.example.PRI.enums.Race;
import com.example.PRI.enums.Religion;
import com.example.PRI.enums.Sex;

import javax.persistence.ManyToMany;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CharacterConverter {

    public static CharacterDefaultAttributesOutputDto convert(Character character){
        CharacterDefaultAttributesOutputDto output = new CharacterDefaultAttributesOutputDto();
        output.setId(character.getId());
        output.setName(character.getName().getName());
        output.setSurname(character.getSurname()!=null ? character.getSurname().getSurname() : "");
        output.setRace(character.getRace()==null ? "" : character.getRace().name());
        output.setSex(character.getSex().name());
        output.setCurrentCareer(character.getCurrentCareer().getName());
//        output.setCareers(character.getCareers().stream().map(Career::getName).collect(Collectors.toList()));
        output.setLivePlace(character.getLivePlace().getName());
        output.setApperance(character.getApperance().stream().map(Apperance::getName).collect(Collectors.toList()));
//        output.setBaseStats(convert(character.getEndStats())); ToDo BaseStats jako odrębne pola w klasie!!
        output.setBirthDate(character.getBirthDate());
        output.setBirthPlace(character.getBirthPlace()==null ? "" : character.getBirthPlace().getName());
        output.setCreatedBy(character.getCreatedBy()==null ? "" : character.getCreatedBy().getUsername());
        output.setDominatingEmotions(character.getDominatingEmotions().stream().map(Emotion::getName).collect(Collectors.toList()));
        output.setEyeColor(character.getEyeColor()==null ? "" : character.getEyeColor().getColor());
        output.setHairColor(character.getHairColor()==null? "" : character.getHairColor().getColor());
        output.setHeight(character.getHeight());
        output.setPersonality(character.getPersonality().stream().map(Personality::getName).collect(Collectors.toList()));
        output.setPrediction(character.getPrediction() == null ? "" : character.getPrediction().getText());
        output.setReligion(character.getReligion()==null ? "" : character.getReligion().getGodName());
        output.setSkills(character.getSkills().stream().map(CharacterConverter::convert).collect(Collectors.toList()));
        output.setStarSign(character.getStarSign()==null ? "" : character.getStarSign().getShortName());
        output.setTalents(character.getTalents().stream().map(Talent::getName).collect(Collectors.toList()));
        output.setWeight(character.getWeight());
        return output;
    }

    private static StatisticsOutputDto convert(Statistics statistics){
        StatisticsOutputDto output = new StatisticsOutputDto();
        if(statistics==null) return output;
        output.setWeaponSkill(statistics.getWeaponSkill());
        output.setBallisticSkill(statistics.getBallisticSkill());
        output.setStrength(statistics.getStrength());
        output.setToughness(statistics.getToughness());
        output.setAgility(statistics.getAgility());
        output.setIntelligence(statistics.getIntelligence());
        output.setWillPower(statistics.getWillPower());
        output.setFellowship(statistics.getFellowship());
        output.setAttacks(statistics.getAttacks());
        output.setWounds(statistics.getWounds());
        output.setMagic(statistics.getMagic());
        output.setMovement(statistics.getMovement());
        return output;
    }

    private static SkillOutputDto convert(Skill skill){
        return skill==null ? new SkillOutputDto() : new SkillOutputDto(skill.getName(), skill.getLevel());
    }

    private static String getStringFromArrayProperties(List<String> properties) {
        if(properties.size() < 1) return "";
        String output = "";
        for (String property: properties){
            output+= property + ", ";
        }
        return output.substring(0, output.length()-2);
    }

    public static CharacterDetailsOutputDto convertDetails(Character character){
        CharacterDetailsOutputDto output = new CharacterDetailsOutputDto();

        output.setBirthPlace(character.getBirthPlace().getName());
        output.setRace(character.getRace());
        output.setEyeColor(character.getEyeColor().getColor());
        output.setHairColor(character.getHairColor().getColor());
        if (character.getBirthDate() != null){
            output.setDayOfBirth(String.valueOf(character.getBirthDate().getDay()));
            output.setMonthOfBirth(String.valueOf(character.getBirthDate().getMonth()));
            output.setYearOfBird(String.valueOf(character.getBirthDate().getYear()));
        }
        output.setStarSign(character.getStarSign().getShortName());
        output.setDominatingEmotions(getStringFromArrayProperties(character.getDominatingEmotions().stream().map(Emotion::getName).collect(Collectors.toList())));
        output.setSex(character.getSex());
        output.setReligion(character.getReligion());
        output.setWeight(character.getWeight());
        output.setHeight(character.getHeight());
        output.setSurname(character.getSurname() != null ? character.getSurname().getSurname() : "");
        output.setName(character.getName().getName());
        output.setPrediction(character.getPrediction().getText());
        output.setCurrentCareer(character.getCurrentCareer().getName());
        output.setPreviousCareers(getStringFromArrayProperties(character.getPreviousCareers().stream().map(Career::getName).collect(Collectors.toList())));
        output.setSkills(character.getSkills());
        output.setTalents(character.getTalents());
        output.setEndWeaponSkills(character.getEndWeaponSkills());
        output.setEndBallisticSkills(character.getEndBallisticSkills());
        output.setEndStrength(character.getEndStrength());
        output.setEndToughness(character.getEndToughness());
        output.setEndAgility(character.getEndAgility());
        output.setEndIntelligence(character.getEndIntelligence());
        output.setEndWillPower(character.getEndWillPower());
        output.setEndFellowship(character.getEndFellowship());
        output.setEndAttacks(character.getEndAttacks());
        output.setEndWounds(character.getEndWounds());
        output.setEndMagic(character.getEndMagic());
        output.setEndMovement(character.getEndMovement());

        if(character.getBaseStats() != null){
            output.setBaseWeaponSkills(character.getBaseStats().getWeaponSkill());
            output.setBaseBallisticSkills(character.getBaseStats().getBallisticSkill());
            output.setBaseStrength(character.getBaseStats().getStrength());
            output.setBaseToughness(character.getBaseStats().getToughness());
            output.setBaseAgility(character.getBaseStats().getToughness());
            output.setBaseIntelligence(character.getBaseStats().getIntelligence());
            output.setBaseWillPower(character.getBaseStats().getWillPower());
            output.setBaseFellowship(character.getBaseStats().getFellowship());
            output.setBaseAttacks(character.getBaseStats().getAttacks());
            output.setBaseWounds(character.getBaseStats().getWounds());
            output.setBaseMagic(character.getBaseStats().getMagic());
            output.setBaseMovement(character.getBaseStats().getMovement());
        }
        output.setPersonality(getStringFromArrayProperties(character.getPersonality().stream().map(Personality::getName).collect(Collectors.toList())));
        output.setApperance(getStringFromArrayProperties(character.getApperance().stream().map(Apperance::getName).collect(Collectors.toList())));
        output.setLivePlace(character.getLivePlace().getName());

        StatisticsOutputDto careerStats = convertCareersStats(character);
        output.setCareerWeaponSkills(careerStats.getWeaponSkill());
        output.setCareerBallisticSkills(careerStats.getBallisticSkill());
        output.setCareerStrength(careerStats.getStrength());
        output.setCareerToughness(careerStats.getToughness());
        output.setCareerAgility(careerStats.getAgility());
        output.setCareerIntelligence(careerStats.getIntelligence());
        output.setCareerWillPower(careerStats.getWillPower());
        output.setCareerFellowship(careerStats.getFellowship());
        output.setCareerAttacks(careerStats.getAttacks());
        output.setCareerWounds(careerStats.getWounds());
        output.setCareerMagic(careerStats.getMagic());
        output.setCareerMovement(careerStats.getMovement());
        return output;
    }

    private static StatisticsOutputDto convertCareersStats(Character character) {
        StatisticsOutputDto output = new StatisticsOutputDto();
        List<Career> careers = character.getPreviousCareers();
        careers.add(character.getCurrentCareer());
        List<Statistics> stats = careers.stream().map(Career::getStatistics).collect(Collectors.toList());


        output.setWeaponSkill(stats.stream().map(Statistics::getWeaponSkill).max(Integer::compareTo).orElse(0));
        output.setBallisticSkill(stats.stream().map(Statistics::getBallisticSkill).max(Integer::compareTo).orElse(0));
        output.setStrength(stats.stream().map(Statistics::getStrength).max(Integer::compareTo).orElse(0));
        output.setToughness(stats.stream().map(Statistics::getToughness).max(Integer::compareTo).orElse(0));
        output.setAgility(stats.stream().map(Statistics::getAgility).max(Integer::compareTo).orElse(0));
        output.setIntelligence(stats.stream().map(Statistics::getIntelligence).max(Integer::compareTo).orElse(0));
        output.setWillPower(stats.stream().map(Statistics::getWillPower).max(Integer::compareTo).orElse(0));
        output.setFellowship(stats.stream().map(Statistics::getFellowship).max(Integer::compareTo).orElse(0));
        output.setAttacks(stats.stream().map(Statistics::getAttacks).max(Integer::compareTo).orElse(0));
        output.setWounds(stats.stream().map(Statistics::getWounds).max(Integer::compareTo).orElse(0));
        output.setMovement(stats.stream().map(Statistics::getMovement).max(Integer::compareTo).orElse(0));
        output.setMagic(stats.stream().map(Statistics::getMagic).max(Integer::compareTo).orElse(0));
        return output;
    }
}
