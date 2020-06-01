package com.example.PRI.services.character.generator;

import com.example.PRI.entities.Place;
import com.example.PRI.entities.character.Surname;
import com.example.PRI.enums.Race;
import com.example.PRI.enums.Sex;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import com.example.PRI.entities.character.Character;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import static com.example.PRI.services.character.generator.MapperJsonStringToMap.mapJsonStringToMap;

@NoArgsConstructor
@AllArgsConstructor
public class CharacterBuilder {

    private Character character;
    private HashMap<String, String> properties;

    public CharacterBuilder initialize(){
        character = new Character();
        properties = new HashMap<>();
        return this;
    }

    public Character getCharacter(){
        return character;
    }

    public HashMap<String, String> getProperties(){
        return properties;
    }


    public CharacterBuilder buildBirthPlace(CharacterBirthPlaceGenerator service){
        HashMap<String, String> newProps = service.generateBirthPlace(character);
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
        Map<String, String> newProps = service.generateRace(character, properties);
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
        Map<String, String> newProps = service.generateSex(character, properties);
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
        Map<String, String> newProps = service.generateSurname(character, properties);
        this.putAllProperties(newProps);
        return this;
    }
//todo ???
    public CharacterBuilder buildSurname(SurnameGenerator service, Surname surname) {
        Map<String, String> newProp = service.getProperties(surname);
        character.setSurname(surname);
        putAllProperties(newProp);
    return this;
    }

    public CharacterBuilder buildBaseStats(StatisticsGenerator service) {
        Map<String, String> newProps = service.generateBaseStats(character, properties);
        putAllProperties(newProps);
        return this;
    }

    public CharacterBuilder buildName(NameGenerator service) {
        Map<String, String> newProps = service.generateName(character, properties);
        putAllProperties(newProps);
        return this;
    }

    public CharacterBuilder buildWeight(WeightGenerator service) {
        Map<String, String> newProps = service.generateWeight(character, properties);
        putAllProperties(newProps);
        return this;
    }

//    public CharacterBuilder buildWeight(WeightGenerator service, Integer weight) {
//        Map<String,String> newProps = service.getProperties(weight, character.getRace());
//        character.setWeight(weight);
//        putAllProperties(newProps);
//        return this;
//    }

    public CharacterBuilder buildHeight(HeightGenerator service) {
        Map<String, String> newProps = service.generateHeight(character, properties);
        putAllProperties(newProps);
        return this;
    }

    public CharacterBuilder buildEyeColor(EyeColorGenerator service) {
        Map<String, String> newProps = service.generateEyeColor(character, properties);
        putAllProperties(newProps);
        return this;
    }

    public CharacterBuilder buildHairColor(HairColorGenerator service) {
        Map<String, String> newProps = service.generateHairColor(character, properties);
        putAllProperties(newProps);
        return this;
    }

    public CharacterBuilder buildBirthDate(BirthDateGenerator service) {
        Map<String, String> newProps = service.generateBirthDate(character, properties);
        putAllProperties(newProps);
        return this;
    }

    public CharacterBuilder buildEmotions(EmotionGenerator service) {
        Map<String, String> newProps = service.generateEmotions(character, properties);
        putAllProperties(newProps);
        return this;
    }


    public CharacterBuilder buildPrediction(PredictionGenerator predictionGenerator) {
        Map<String, String> newProps = predictionGenerator.generatePrediction(character);
        putAllProperties(newProps);
        return this;
    }


    public CharacterBuilder buildCareers(CareerGenerator service) {
        Map<String, String> newProps = FirstCareerPropertiesMapper.map(character, properties);
        putAllProperties(newProps);
        newProps = service.buildFirstCareer(character, properties);
        putAllProperties(newProps);
        List<Map<String, String>> newPropsList = service.buildNextCareers(character, properties);
        for(Map<String, String> props : newPropsList){
            this.putAllProperties(props);
        }
        return this;
    }

    public CharacterBuilder buildCareerStatistics(CareerStatisticsGenerator service) {
        Map<String, String> newProps = service.generateCareerStatisticsGenerator(character, properties);
        putAllProperties(newProps);
        return this;
    }

    public CharacterBuilder buildLivePlace(LivePlaceGenerator service) {
        Map<String, String> newProps = service.generateLivePlace(character, properties);
        putAllProperties(newProps);
        return this;
    }

    public CharacterBuilder buildApperances(ApperanceGenerator service) {
        Map<String, String> newProps = service.generateApperances(character, properties);
        putAllProperties(newProps);
        return this;
    }

    public CharacterBuilder buildPersonalities(PersonalityGenerator service) {
        Map<String, String> newProps = service.generatePersonalities(character, properties);
        putAllProperties(newProps);
        return this;
    }

    public CharacterBuilder buildTalents(TalentGenerator service) {
        Map<String, String> newProps = service.generateTalents(character, properties);
        putAllProperties(newProps);
        return this;
    }

    public CharacterBuilder buildSkills(SkillGenerator service) {
        Map<String, String> newProps = service.generateSkills(character, properties);
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

    public CharacterBuilder buildReligion(ReligionGenerator service) {
        Map<String, String> newProps = service.prepareProps(character, properties);
        putAllProperties(newProps);
        newProps = service.generateReligion(character, properties);
        putAllProperties(newProps);
        return this;
    }
}
