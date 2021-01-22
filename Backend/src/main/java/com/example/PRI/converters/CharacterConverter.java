package com.example.PRI.converters;

import com.example.PRI.dtos.characters.*;
import com.example.PRI.entities.character.*;
import com.example.PRI.entities.character.Character;
import com.example.PRI.entities.session.AttributesVisibility;
import com.example.PRI.entities.session.SessionCharacter;
import com.example.PRI.enums.CharacterAttribute;
import com.example.PRI.enums.Sex;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CharacterConverter {


    public static UserOfAppCharacterOutputDto convertToUserProfileCharacter(Character character){
        UserOfAppCharacterOutputDto output = new UserOfAppCharacterOutputDto();
        output.setName(character.getName().getName());
        output.setSurname(character.getSurname()==null ? "" : character.getSurname().getSurname());
        output.setId(character.getId());
        output.setRace(character.getRace().getName());
        output.setSex(character.getSex().getName());
        output.setCareer(character.getCurrentCareer().getName());
        output.setLivePlace(character.getLivePlace().getName());
        return output;

    }

    public static String convertFilterAttributeToClassFieldName(String filterAttribute){
        if(filterAttribute==null) return filterAttribute;
        if(filterAttribute.equals(CharacterAttribute.LIVEPLACE.getName())) return filterAttribute +".name";
        if(filterAttribute.equals(CharacterAttribute.BIRTHPLACE.getName())) return filterAttribute +".name";
        if(filterAttribute.equals(CharacterAttribute.EYECOLOR.getName())) return filterAttribute +".color";
        if(filterAttribute.equals(CharacterAttribute.HAIRCOLOR.getName())) return filterAttribute +".color";
        if(filterAttribute.equals(CharacterAttribute.STARSIGN.getName())) return filterAttribute +".name";
        if(filterAttribute.equals(CharacterAttribute.SEX.getName())) return filterAttribute +".name";
        if(filterAttribute.equals(CharacterAttribute.RELIGION.getName())) return filterAttribute +".godName";
        if(filterAttribute.equals(CharacterAttribute.SURNAME.getName())) return filterAttribute +".surname";
        if(filterAttribute.equals(CharacterAttribute.NAME.getName())) return filterAttribute +".name";
        if(filterAttribute.equals(CharacterAttribute.CURRENTCAREER.getName())) return filterAttribute +".name";
        return filterAttribute;
    }

    public static CharacterDefaultAttributesOutputDto convert(Character character) {
        CharacterDefaultAttributesOutputDto output = new CharacterDefaultAttributesOutputDto();
        output.setId(character.getId());
        output.setName(character.getName().getName());
        output.setBirthPlace(character.getBirthPlace() == null ? "" : character.getBirthPlace().getName());
        output.setSurname(character.getSurname() != null ? character.getSurname().getSurname() : "");
        output.setRace(character.getRace() == null ? "" : character.getRace().name());
        output.setSex(character.getSex().name()); //ToDo używać getName i wprowadzić poprawkę na frontendzie
        output.setCurrentCareer(character.getCurrentCareer().getName());
//        output.setCareers(character.getCareers().stream().map(Career::getName).collect(Collectors.toList()));
        output.setLivePlace(character.getLivePlace().getName());
        output.setApperance(character.getApperance().stream().map(a -> convertApperance(a, character.getSex())).collect(Collectors.toList()));
//        output.setBaseStats(convert(character.getEndStats())); ToDo BaseStats jako odrębne pola w klasie!!
        output.setDayOfBirth(character.getBirthDate().getDay().toString());
        if (character.getBirthDate() != null) {
            output.setDayOfBirth(String.valueOf(character.getBirthDate().getDay()));
            if (character.getBirthDate().getMonth() != null)
                output.setMonthOfBirth(String.valueOf(character.getBirthDate().getMonth().getMonthName()));
            output.setYearOfBirth(String.valueOf(character.getBirthDate().getYear()));
        }


//        output.setMonthOfBirth(character.getBirthDate().getMonth().getMonthName());
//        output.setYearOfBirth(character.getBirthDate().getYear().toString());
//        output.setBirthPlace(character.getBirthPlace()==null ? "" : character.getBirthPlace().getName());
        output.setCreatedBy(character.getCreatedBy() == null ? "" : character.getCreatedBy().getUsername());
        output.setDominatingEmotions(character.getDominatingEmotions().stream().map(Emotion::getName).collect(Collectors.toList()));
        output.setEyeColor(character.getEyeColor() == null ? "" : character.getEyeColor().getColor());
        output.setHairColor(character.getHairColor() == null ? "" : character.getHairColor().getColor());
        output.setHeight(character.getHeight());
        output.setPersonality(character.getPersonality().stream().map(p -> convertPersonality(p, character.getSex())).collect(Collectors.toList()));
        output.setPrediction(character.getPrediction() == null ? "" : character.getPrediction().getText());
        output.setReligion(character.getReligion() == null ? "" : character.getReligion().getGodName());
        output.setSkills(character.getSkills().stream().map(CharacterConverter::convert).collect(Collectors.toList()));
        output.setStarSign(character.getStarSign() == null ? "" : character.getStarSign().getName());
        output.setTalents(character.getTalents().stream().map(Talent::getName).collect(Collectors.toList()));
        output.setWeight(character.getWeight());
        return output;
    }

    private static StatisticsOutputDto convert(Statistics statistics) {
        StatisticsOutputDto output = new StatisticsOutputDto();
        if (statistics == null) return output;
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

    private static SkillOutputDto convert(Skill skill) {
        return skill == null ? new SkillOutputDto() : new SkillOutputDto(skill.getName(), skill.getLevel());
    }

    private static String convertPersonality(Personality p, Sex s){
        if(s==null || s.equals(Sex.MALE)){
            return p.getName();
        }
        else{
            return p.getFemaleName();
        }
    }

    private static String convertApperance(Apperance a, Sex s){
        if(s==null || s.equals(Sex.MALE)){
            return a.getName();
        }
        else{
            return a.getFemaleName();
        }
    }

    private static String getStringFromArrayProperties(List<String> properties) {
        if(properties==null) return "";
        if (properties.size() < 1) return "";
        String output = "";
        for (String property : properties) {
            output += property + ", ";
        }
        return output.substring(0, output.length() - 2);
    }

    public static CharacterDetailsOutputDto convertDetails(Character character) {
        CharacterDetailsOutputDto output = new CharacterDetailsOutputDto();


        output.setBirthPlace(character.getBirthPlace()==null ? "" : character.getBirthPlace().getName());
        output.setRace(character.getRace()==null? "" : character.getRace().getName());
        output.setEyeColor(character.getEyeColor()==null? "" : character.getEyeColor().getColor());
        output.setHairColor(character.getHairColor()==null? "" : character.getHairColor().getColor());
        if (character.getBirthDate() != null) {
            output.setDayOfBirth(String.valueOf(character.getBirthDate().getDay()));
            output.setMonthOfBirth(String.valueOf(character.getBirthDate().getMonth().getMonthName()));
            output.setYearOfBird(String.valueOf(character.getBirthDate().getYear()));
        }
        output.setStarSign(character.getStarSign() == null ? "" : character.getStarSign().getName());
        output.setDominatingEmotions(character.getDominatingEmotions()==null? "" :getStringFromArrayProperties(character.getDominatingEmotions().stream().map(Emotion::getName).collect(Collectors.toList())));
        output.setSex(character.getSex()==null? "" : character.getSex().getName());
        output.setReligion(character.getReligion()==null? "" : character.getReligion().getGodName());
        output.setWeight(character.getWeight());
        output.setHeight(character.getHeight());
        output.setSurname(character.getSurname() != null ? character.getSurname().getSurname() : "");
        output.setName(character.getName()==null? "" :character.getName().getName());
        output.setPrediction(character.getPrediction() != null ? character.getPrediction().getText() : "");
        output.setCurrentCareer(character.getCurrentCareer()==null? "" : character.getCurrentCareer().getName());
        output.setPreviousCareers(character.getPreviousCareers()==null? "" :getStringFromArrayProperties(character.getPreviousCareers().stream().map(Career::getName).collect(Collectors.toList())));
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

        if (character.getBaseStats() != null) {
            output.setBaseWeaponSkills(character.getBaseStats().getWeaponSkill());
            output.setBaseBallisticSkills(character.getBaseStats().getBallisticSkill());
            output.setBaseStrength(character.getBaseStats().getStrength());
            output.setBaseToughness(character.getBaseStats().getToughness());
            output.setBaseAgility(character.getBaseStats().getAgility());
            output.setBaseIntelligence(character.getBaseStats().getIntelligence());
            output.setBaseWillPower(character.getBaseStats().getWillPower());
            output.setBaseFellowship(character.getBaseStats().getFellowship());
            output.setBaseAttacks(character.getBaseStats().getAttacks());
            output.setBaseWounds(character.getBaseStats().getWounds());
            output.setBaseMagic(character.getBaseStats().getMagic());
            output.setBaseMovement(character.getBaseStats().getMovement());
        }
        output.setPersonality(character.getPersonality()==null? "" : getStringFromArrayProperties(character.getPersonality().stream().map(p -> convertPersonality(p, character.getSex())).collect(Collectors.toList())));
        output.setApperance(character.getApperance()==null? "" : getStringFromArrayProperties(character.getApperance().stream().map(a -> convertApperance(a, character.getSex())).collect(Collectors.toList())));
        output.setLivePlace(character.getLivePlace()==null? "" : character.getLivePlace().getName());

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
        List<Statistics> stats = new ArrayList<>();
        if(careers!=null){
            careers.add(character.getCurrentCareer());
            stats = careers.stream().map(Career::getStatistics).collect(Collectors.toList());

        }


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


    public static CharacterSessionOutputDto convertToSessionCharacter(SessionCharacter sessionCharacter) {
        AttributesVisibility attributesVisibility = sessionCharacter.getAttributesVisibility();
        Character character = sessionCharacter.getCharacter();

        CharacterSessionOutputDto output = new CharacterSessionOutputDto();


        if(attributesVisibility.isBirthPlace() && character.getBirthPlace()!=null){
            output.setBirthPlace(character.getBirthPlace().getName());
        }

        if(attributesVisibility.isRace() && character.getRace()!=null){
            output.setRace(character.getRace().getName());
        }

        if(attributesVisibility.isEyeColor() && character.getEyeColor()!=null){
            output.setEyeColor(character.getEyeColor().getColor());
        }

        if(attributesVisibility.isHairColor() && character.getHairColor()!=null){
            output.setHairColor(character.getHairColor().getColor());
        }

        if(attributesVisibility.isDayOfBirth() && character.getBirthDate().getDay()!=null){
            output.setDayOfBirth(character.getBirthDate().getDay().toString());
        }

        if(attributesVisibility.isMonthOfBirth() && character.getBirthDate().getMonth().getMonthName()!=null){
            output.setMonthOfBirth(character.getBirthDate().getMonth().getMonthName());
        }

        if(attributesVisibility.isYearOfBirth() && character.getBirthDate().getYear()!=null){
            output.setYearOfBird(character.getBirthDate().getYear().toString());
        }

        if(attributesVisibility.isStarSign() && character.getStarSign()!=null){
            output.setStarSign(character.getStarSign().getName());
        }

        if(attributesVisibility.isSex() && character.getSex()!=null){
            output.setSex(character.getSex().getName());
        }

        if(attributesVisibility.isLivePlace() && character.getLivePlace()!=null){
            output.setLivePlace(character.getLivePlace().getName());
        }

        if(attributesVisibility.isReligion() && character.getReligion()!=null){
            output.setReligion(character.getReligion().getGodName());
        }

        if(attributesVisibility.isWeight() && character.getWeight()!=null){
            output.setWeight(character.getWeight());
        }

        if(attributesVisibility.isHeight() && character.getHeight()!=null){
            output.setHeight(character.getHeight());
        }

        if(attributesVisibility.isSurname() && character.getSurname()!=null){
            output.setSurname(character.getSurname().getSurname());
        }

        if(attributesVisibility.isName() && character.getName()!=null){
            output.setName(character.getName().getName());
        }

        if(attributesVisibility.isPrediction() && character.getPrediction()!=null){
            output.setPrediction(character.getPrediction().getText());
        }

        if(attributesVisibility.isCurrentCareer() && character.getCurrentCareer()!=null){
            output.setCurrentCareer(character.getCurrentCareer().getName());
        }

        if(attributesVisibility.isPreviousCareers() && character.getPreviousCareers()!=null){
            output.setPreviousCareers(getStringFromArrayProperties(character.getPreviousCareers().stream().map(Career::getName).collect(Collectors.toList())));
        }

        if(attributesVisibility.isWeaponSkill() && character.getEndWeaponSkills()!=null){
            output.setEndWeaponSkills(character.getEndWeaponSkills());
        }

        if(attributesVisibility.isBallisticSkill() && character.getEndBallisticSkills()!=null){
            output.setEndBallisticSkills(character.getEndBallisticSkills());
        }

        if(attributesVisibility.isStrength() && character.getEndStrength()!=null){
            output.setEndStrength(character.getEndStrength());
        }

        if(attributesVisibility.isToughness() && character.getEndToughness()!=null){
            output.setEndToughness(character.getEndToughness());
        }

        if(attributesVisibility.isAgility() && character.getEndAgility()!=null){
            output.setEndAgility(character.getEndAgility());
        }

        if(attributesVisibility.isIntelligence() && character.getEndIntelligence()!=null){
            output.setEndIntelligence(character.getEndIntelligence());
        }

        if(attributesVisibility.isWillPower() && character.getEndWillPower()!=null){
            output.setEndWillPower(character.getEndWillPower());
        }

        if(attributesVisibility.isFellowship() && character.getEndFellowship()!=null){
            output.setEndFellowship(character.getEndFellowship());
        }

        if(attributesVisibility.isAttacks() && character.getEndAttacks()!=null){
            output.setEndAttacks(character.getEndAttacks());
        }

        if(attributesVisibility.isWounds() && character.getEndWounds()!=null){
            output.setEndWounds(character.getEndWounds());
        }

        if(attributesVisibility.isMagic() && character.getEndMagic()!=null){
            output.setEndMagic(character.getEndMagic());
        }

        if(attributesVisibility.isMovement() && character.getEndMovement()!=null){
            output.setEndMovement(character.getEndMovement());
        }

        if(attributesVisibility.isSkills() && character.getSkills()!=null){
            output.setSkills(getStringFromArrayProperties(character.getSkills().stream().map(Skill::getName).collect(Collectors.toList())));
        }


        if(attributesVisibility.isTalents() && character.getTalents()!=null){
            output.setTalents(getStringFromArrayProperties(character.getTalents().stream().map(Talent::getName).collect(Collectors.toList())));
        }

        if(attributesVisibility.isPersonality() && character.getPersonality()!=null){
            output.setPersonality(getStringFromArrayProperties(character.getPersonality().stream().map(p -> convertPersonality(p, character.getSex())).collect(Collectors.toList())));
        }

        if(attributesVisibility.isApperance() && character.getApperance()!=null){
            output.setApperance(getStringFromArrayProperties(character.getApperance().stream().map(a -> convertApperance(a, character.getSex())).collect(Collectors.toList())));
        }

        if(attributesVisibility.isEmotion() && character.getDominatingEmotions()!=null){
            output.setDominatingEmotions(getStringFromArrayProperties(character.getDominatingEmotions().stream().map(Emotion::getName).collect(Collectors.toList())));
        }
        return output;
    }
}
