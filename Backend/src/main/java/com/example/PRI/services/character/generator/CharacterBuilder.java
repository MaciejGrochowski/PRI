package com.example.PRI.services.character.generator;

import com.example.PRI.entities.ImperialDate;
import com.example.PRI.entities.Place;
import com.example.PRI.entities.character.Character;
import com.example.PRI.entities.character.*;
import com.example.PRI.enums.Race;
import com.example.PRI.enums.Religion;
import com.example.PRI.enums.Sex;
import com.example.PRI.services.PlaceService;
import com.example.PRI.services.RandomService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Access;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.PRI.services.character.generator.MapperJsonStringToMap.mapJsonStringToMap;

@NoArgsConstructor
@AllArgsConstructor
public class CharacterBuilder {


    private Character character;
    private HashMap<String, String> properties;
    private RandomService randomService;

    public CharacterBuilder initialize(){
        return this.initialize(System.currentTimeMillis());
    }

    public CharacterBuilder initialize(Long seed){
        character = new Character();
        properties = new HashMap<>();
        randomService = new RandomService(seed);
        System.out.println("Generating character, seed: " + seed);
        System.out.println(new Date());
        return this;
    }

    public Character getCharacter(){
        if(character.getName() != null){
            System.out.println("CharacterName: " + character.getName().getName());
        }
        if(character.getSurname()!=null){
            System.out.println("CharacterSurname: " + character.getName().getName());
        }
        if(character.getCurrentCareer()!=null){
            System.out.println("CharacterCareer: " + character.getCurrentCareer().getName());
        }
        System.out.println("Generated character");//ToDo better logs in other class
        return character;
    }

    public HashMap<String, String> getProperties(){
        return properties;
    }

    public RandomService getRandomService() { return randomService;}


    public CharacterBuilder buildBirthPlace(CharacterBirthPlaceGenerator service){
        HashMap<String, String> newProps = service.generateBirthPlace(character, randomService); //ToDo use randomService in all generators.
        this.putAllProperties(newProps);
        Place parent = character.getBirthPlace().getParent();
        while(parent != null){
            this.putAllProperties(mapJsonStringToMap(parent.getProperties()));
            parent = parent.getParent();
        }
        return this;
    }
    //ToDo Kasia
    public CharacterBuilder buildBirthPlace(CharacterBirthPlaceGenerator service, Place place){
        Map<String,String> newProps = service.getProperties(place);
        character.setBirthPlace(place);
        putAllProperties(newProps);
        return this;
    }

    public CharacterBuilder buildRace(RaceGenerator service){
        HashMap<String, String> newProps = service.generateRace(character, randomService, properties);
        this.putAllProperties(newProps);
        return this;
    }


    public CharacterBuilder buildRace(RaceGenerator service, Race race){
        Map<String, String> newProp = service.getProperties(race);
        character.setRace(race);
        putAllProperties(newProp);
        return this;
    }

    public CharacterBuilder buildSex(SexGenerator service) {
        HashMap<String, String> newProps = service.generateSex(character,randomService, properties);
        this.putAllProperties(newProps);
        return this;
    }

    public CharacterBuilder buildSex(SexGenerator service, Sex sex){
        Map<String, String> newProp = service.getProperties(sex);
        character.setSex(sex);
        putAllProperties(newProp);
        return this;
    }

    public CharacterBuilder buildSurname(SurnameGenerator service) {
        Map<String, String> newProps = service.generateSurname(character, randomService, properties);
        this.putAllProperties(newProps);
        return this;
    }

    public CharacterBuilder buildSurname(SurnameGenerator service, Surname surname) {
        Map<String, String> newProp = service.getProperties(surname);
        character.setSurname(surname);
        putAllProperties(newProp);
    return this;
    }
    public CharacterBuilder buildBaseStats(StatisticsGenerator service) {
        Map<String, String> newProps = service.generateBaseStats(character, randomService, properties);
        putAllProperties(newProps);
        return this;
    }

    public CharacterBuilder buildBaseStats(StatisticsGenerator service, Statistics stats) {
        Map<String, String> newProps = service.getProperties(stats, properties);
        character.setBaseStats(stats);
        putAllProperties(newProps);
        return this;
    }

    public CharacterBuilder buildName(NameGenerator service) {
        Map<String, String> newProps = service.generateName(character, randomService, properties);
        putAllProperties(newProps);
        return this;
    }

    public CharacterBuilder buildName(NameGenerator service, Name name){
        Map<String,String> newProps = service.getProperties(name);
        character.setName(name);
        putAllProperties(newProps);
        return this;
    }

    public CharacterBuilder buildWeight(WeightGenerator service) {
        Map<String, String> newProps = service.generateWeight(character, randomService, properties);
        putAllProperties(newProps);
        return this;
    }

    public CharacterBuilder buildWeight(WeightGenerator service, Integer weight) {
        Map<String,String> newProps = service.getProperties(weight, properties);
        character.setWeight(weight);
        putAllProperties(newProps);
        return this;
    }

    public CharacterBuilder buildHeight(HeightGenerator service) {
        Map<String, String> newProps = service.generateHeight(character, randomService, properties);
        putAllProperties(newProps);
        return this;
    }

    public CharacterBuilder buildHeight(HeightGenerator service, Integer height) {
        Map<String, String> newProps = service.getProperties(height, properties);
        character.setHeight(height);
        putAllProperties(newProps);
        return this;
    }
    public CharacterBuilder buildEyeColor(EyeColorGenerator service) {
        Map<String, String> newProps = service.generateEyeColor(character, randomService, properties);
        putAllProperties(newProps);
        return this;
    }

    public CharacterBuilder buildEyeColor(EyeColorGenerator service, EyeColor eyeColor) {
        Map<String, String> newProps = service.getProperties(eyeColor);
        character.setEyeColor(eyeColor);
        putAllProperties(newProps);
        return this;
    }

    public CharacterBuilder buildHairColor(HairColorGenerator service) {
        Map<String, String> newProps = service.generateHairColor(character,randomService, properties);
        putAllProperties(newProps);
        return this;
    }

    public CharacterBuilder buildHairColor(HairColorGenerator service, HairColor hairColor) {
        Map<String, String> newProps = service.getProperties(hairColor);
        character.setHairColor(hairColor);
        putAllProperties(newProps);
        return this;
    }

    public CharacterBuilder buildBirthDate(BirthDateGenerator service) {
        Map<String, String> newProps = service.generateBirthDate(character, randomService, properties);
        putAllProperties(newProps);
        return this;
    }

    public CharacterBuilder buildBirthDate(BirthDateGenerator service, ImperialDate imperialDate ) {
        Map<String, String> newProps = service.getProperties(imperialDate, properties);
        character.setBirthDate(imperialDate);
        putAllProperties(newProps);
        return this;
    }

    public CharacterBuilder buildEmotions(EmotionGenerator service) {
        Map<String, String> newProps = service.generateEmotions(character,randomService, properties);
        putAllProperties(newProps);
        return this;
    }

    public CharacterBuilder buildEmotions(EmotionGenerator service, List<Emotion> emotion) {
        Map<String, String> newProps = service.getProperties(emotion);
        character.setDominatingEmotions(emotion);
        putAllProperties(newProps);
        return this;
    }

    public CharacterBuilder buildPrediction(PredictionGenerator service) {
        Map<String, String> newProps = service.generatePrediction(character, randomService);
        putAllProperties(newProps);
        return this;
    }

    public CharacterBuilder buildPrediction(PredictionGenerator service, Prediction prediction) {
        Map<String, String> newProps = service.getProperties(prediction);
        character.setPrediction(prediction);
        putAllProperties(newProps);
        return this;
    }

    public CharacterBuilder buildCurrentCareer(CareerGenerator service, List<Career> previousCareers) {
        Map<String, String> newProps = FirstCareerPropertiesMapper.map(character, properties);
        putAllProperties(newProps);
        for(Career career : previousCareers){
            putAllProperties(mapJsonStringToMap(career.getProperties()));
        }
        newProps = service.buildCurrentCareer(previousCareers, randomService, properties, character);
        putAllProperties(newProps);
        character.setPreviousCareers(previousCareers);
        return this;
    }

    public CharacterBuilder buildCareers(CareerGenerator service) {
        Map<String, String> newProps = FirstCareerPropertiesMapper.map(character, properties);
        putAllProperties(newProps);
        newProps = service.buildFirstCareer(character,randomService, properties);
        putAllProperties(newProps);
        List<Map<String, String>> newPropsList = service.buildNextCareers(character,randomService, properties);
        for(Map<String, String> props : newPropsList){
            this.putAllProperties(props);
        }
        return this;
    }

    public CharacterBuilder buildCareers(CareerGenerator service, List<Career> career) {
        Map<String, String> newProps = FirstCareerPropertiesMapper.map(character, properties);
        putAllProperties(newProps);
        Map<String, String> newPropsList = service.getProperties(career);
        putAllProperties(newPropsList);
        character.setPreviousCareers(career.subList(0, career.size()-1));
        character.setCurrentCareer(career.get(career.size()-1));
        return this;
    }

    public CharacterBuilder buildCareerStatistics(CareerStatisticsGenerator service) {
        Map<String, String> newProps = service.generateCareerStatisticsGenerator(character, randomService, properties);
        putAllProperties(newProps);
        return this;
    }

    public CharacterBuilder buildCareerStatistics(CareerStatisticsGenerator service, Character c) {
        character.setEndWeaponSkills(c.getEndWeaponSkills());
        character.setEndBallisticSkills(c.getEndBallisticSkills());
        character.setEndAgility(c.getEndAgility());
        character.setEndFellowship(c.getEndFellowship());
        character.setEndAgility(c.getEndAgility());
        character.setEndIntelligence(c.getEndIntelligence());
        character.setEndMovement(c.getEndMovement());
        character.setEndStrength(c.getEndStrength());
        character.setEndToughness(c.getEndToughness());
        character.setEndWillPower(c.getEndWillPower());
        character.setEndWounds(c.getEndWounds());
        character.setEndAttacks(c.getEndAttacks());
        character.setEndMagic(c.getEndMagic());
        return this;
    }

    public CharacterBuilder buildLivePlace(LivePlaceGenerator service) {
        Map<String, String> newProps = service.generateLivePlace(character,randomService, properties);
        putAllProperties(newProps);
        return this;
    }

    public CharacterBuilder buildLivePlace(LivePlaceGenerator service, Place place) {
        Map<String, String> newProps = service.getProperties(service, place);
        putAllProperties(newProps);
        character.setLivePlace(place);
        return this;
    }

    public CharacterBuilder buildApperances(ApperanceGenerator service) {
        Map<String, String> newProps = service.generateApperances(character, randomService, properties);
        putAllProperties(newProps);
        return this;
    }

    public CharacterBuilder buildApperances(ApperanceGenerator service, List<Apperance> apperance) {
        Map<String, String> newProps = service.getProperties(service, apperance);
        putAllProperties(newProps);
        character.setApperance(apperance);
        return this;
    }

    public CharacterBuilder buildPersonalities(PersonalityGenerator service) {
        Map<String, String> newProps = service.generatePersonalities(character, randomService, properties);
        putAllProperties(newProps);
        return this;
    }

    public CharacterBuilder buildPersonalities(PersonalityGenerator service, List<Personality> personalities) {
        Map<String, String> newProps = service.getProperties(character, personalities );
        putAllProperties(newProps);
        character.setPersonality(personalities);
        return this;
    }

    public CharacterBuilder buildTalents(TalentGenerator service) {
        Map<String, String> newProps = service.generateTalents(character, randomService, properties);
        putAllProperties(newProps);
        return this;
    }

    public CharacterBuilder buildTalents(TalentGenerator service, List<Talent> talents) {
        Map<String, String> newProps = service.getProperties(character, talents);
        putAllProperties(newProps);
        character.setTalents(talents);
        return this;
    }

    public CharacterBuilder buildSkills(SkillGenerator service) {
        Map<String, String> newProps = service.generateSkills(character, randomService, properties);
        putAllProperties(newProps);
        return this;
    }

    public CharacterBuilder buildSkills(SkillGenerator service, List<Skill> skills) {
        Map<String, String> newProps = service.getProperties(character, skills);
        putAllProperties(newProps);
        character.setSkills(skills);
        return this;
    }

    public CharacterBuilder buildReligion(ReligionGenerator service) {
        Map<String, String> newProps = service.prepareProps(character, properties);
        putAllProperties(newProps);
        newProps = service.generateReligion(character, randomService, properties);
        putAllProperties(newProps);
        return this;
    }

    public CharacterBuilder buildReligion(ReligionGenerator service, Religion religion) {
        Map<String, String> newProps = service.prepareProps(character, properties);
        putAllProperties(newProps);
        newProps = service.getProperties(religion);
        character.setReligion(religion);
        putAllProperties(newProps);
        return this;
    }

    private void putAllProperties(Map<String, String> newProps){
        for(String key: newProps.keySet()){
            if(!this.properties.containsKey(key)){
                this.properties.put(key, newProps.get(key));
            }
            else{
                if(this.properties.get(key).equals("0") || newProps.get(key).equals("0")){
                    this.properties.put(key, "0");
                    continue;
                }
                try{
                    Double oldPropValue = Double.parseDouble(properties.get(key));
                    Double newPropValue = Double.parseDouble(newProps.get(key));
                    Double sum = oldPropValue+newPropValue;
                    if(sum==0) sum=0.000001;
                    this.properties.put(key, String.valueOf(oldPropValue+newPropValue));
                }
                catch(NumberFormatException e){
                    System.err.println(key);
                    System.err.println(properties.get(key));
                    System.err.println(newProps.get(key));
                    System.err.println("Błąd przy łączeniu propertiesów!");
                }
            }
        }
    }

}
