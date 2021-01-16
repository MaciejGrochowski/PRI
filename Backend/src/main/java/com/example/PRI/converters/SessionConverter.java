package com.example.PRI.converters;

import com.example.PRI.dtos.sessions.AttributesVisibilityInputDto;
import com.example.PRI.dtos.sessions.SessionOutputDto;
import com.example.PRI.entities.session.AttributesVisibility;
import com.example.PRI.entities.session.Session;

import java.lang.reflect.Field;

public class SessionConverter {

    public static SessionOutputDto convert(Session s){
        return convert(s, false);
    }

    public static SessionOutputDto convert(Session s, Boolean isHashcodeVisible){
        SessionOutputDto sessionOutputDto = new SessionOutputDto();
        sessionOutputDto.setDescription(s.getDescription());
        sessionOutputDto.setName(s.getName());
        sessionOutputDto.setId(String.valueOf(s.getId()));
        sessionOutputDto.setCreatedDate(s.getCreatedDate());
        sessionOutputDto.setLastModifiedDate(s.getLastModifiedDate());
        if(s.getCreatedUserOfApp()!=null) sessionOutputDto.setCreatedUserBy(s.getCreatedUserOfApp().getUsername());
        if(isHashcodeVisible) sessionOutputDto.setHashcode(s.getRandomIdSession());
        return sessionOutputDto;

    }

    public static AttributesVisibility convertAttributesVisibilityInput(AttributesVisibilityInputDto input){
        AttributesVisibility output = new AttributesVisibility();
        if(input.getName()!=null) output.setName(input.getName());
        if(input.getSurname()!=null) output.setSurname(input.getSurname());
        if(input.getRace()!=null) output.setRace(input.getRace());
        if(input.getSex()!=null) output.setSex(input.getSex());
        if(input.getLivePlace()!=null) output.setLivePlace(input.getLivePlace());
        if(input.getCurrentCareer()!=null) output.setCurrentCareer(input.getCurrentCareer());
        if(input.getBirthPlace()!=null) output.setBirthPlace(input.getBirthPlace());
        if(input.getDayOfBirth()!=null) output.setDayOfBirth(input.getDayOfBirth());
        if(input.getMonthOfBirth()!=null) output.setMonthOfBirth(input.getMonthOfBirth());
        if(input.getYearOfBirth()!=null) output.setYearOfBirth(input.getYearOfBirth());
        if(input.getReligion()!=null) output.setReligion(input.getReligion());
        if(input.getHairColor()!=null) output.setHairColor(input.getHairColor());
        if(input.getPersonality()!=null) output.setPersonality(input.getPersonality());
        if(input.getPreviousCareers()!=null) output.setPreviousCareers(input.getPreviousCareers());
        if(input.getEmotion()!=null) output.setEmotion(input.getEmotion());
        if(input.getSkills()!=null) output.setSkills(input.getSkills());
        if(input.getTalents()!=null) output.setTalents(input.getTalents());
        if(input.getPrediction()!=null) output.setPrediction(input.getPrediction());
        if(input.getStarSign()!=null) output.setStarSign(input.getStarSign());
        if(input.getHeight()!=null) output.setHeight(input.getHeight());
        if(input.getWeight()!=null) output.setWeight(input.getWeight());
        if(input.getEyeColor()!=null) output.setEyeColor(input.getEyeColor());
        if(input.getApperance()!=null) output.setApperance(input.getApperance());


        if(input.getWeaponSkill()!=null) output.setWeaponSkill(input.getWeaponSkill());
        if(input.getBallisticSkill()!=null) output.setBallisticSkill(input.getBallisticSkill());
        if(input.getStrength()!=null) output.setStrength(input.getStrength());
        if(input.getToughness()!=null) output.setToughness(input.getToughness());
        if(input.getAgility()!=null) output.setAgility(input.getAgility());
        if(input.getIntelligence()!=null) output.setIntelligence(input.getIntelligence());
        if(input.getWillPower()!=null) output.setWillPower(input.getWillPower());
        if(input.getFellowship()!=null) output.setFellowship(input.getFellowship());
        if(input.getAttacks()!=null) output.setAttacks(input.getAttacks());
        if(input.getWounds()!=null) output.setWounds(input.getWounds());
        if(input.getMagic()!=null) output.setMagic(input.getMagic());
        if(input.getMovement()!=null) output.setMovement(input.getMovement());
        return output;

    }
}
