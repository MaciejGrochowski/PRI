package com.example.PRI.converters;

import com.example.PRI.dtos.characters.CharacterDefaultAttributesOutputDto;
import com.example.PRI.dtos.characters.SkillOutputDto;
import com.example.PRI.dtos.characters.StatisticsOutputDto;
import com.example.PRI.entities.character.*;
import com.example.PRI.entities.character.Character;

import java.util.stream.Collectors;

public class CharacterConverter {

    public static CharacterDefaultAttributesOutputDto convert(Character character){
        CharacterDefaultAttributesOutputDto output = new CharacterDefaultAttributesOutputDto();
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
        output.setReligion(character.getReligion()==null ? "" : character.getReligion().name());
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

}
