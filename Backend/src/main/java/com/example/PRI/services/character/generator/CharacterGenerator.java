package com.example.PRI.services.character.generator;

import com.example.PRI.converters.CharacterGeneratorConverter;
import com.example.PRI.dtos.characters.CharacterInputDto;
import com.example.PRI.entities.ImperialDate;
import com.example.PRI.entities.Place;
import com.example.PRI.entities.character.*;
import com.example.PRI.entities.character.Character;
import com.example.PRI.enums.Race;
import com.example.PRI.enums.Religion;
import com.example.PRI.enums.Sex;
import com.example.PRI.services.GeneralService;
import com.example.PRI.services.PlaceService;
import com.example.PRI.services.character.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CharacterGenerator extends GeneralService {

    @Autowired
    private CharacterBirthPlaceGenerator characterBirthPlaceGenerator;

    @Autowired
    private SurnameGenerator surnameGenerator;

    @Autowired
    private EyeColorGenerator eyeColorGenerator;

    @Autowired
    private CareerService careerService;

    @Autowired
    private NameService nameService;

    @Autowired
    private CharacterService characterService;

    @Autowired
    private SurnameService surnameService;

    @Autowired
    private PredictionService predictionService;

    @Autowired
    private HairColorService hairColorService;

    @Autowired
    private EyeColorService eyeColorService;

    @Autowired
    private PlaceService placeService;


    public Character generateFullCharacter(){

        CharacterBuilder characterBuilder = new CharacterBuilder();
        characterBuilder.initialize()
                .buildBirthPlace(characterBirthPlaceGenerator)
                .buildRace(new RaceGenerator())
                .buildSex(new SexGenerator())
                .buildSurname(surnameGenerator)
                .buildBaseStats(new StatisticsGenerator())
                .buildHeight(new HeightGenerator())
                .buildWeight(new WeightGenerator())
                .buildEyeColor(eyeColorGenerator)
        ;

        Character generated = characterBuilder.getCharacter();

        HashMap<String, String> tmp = characterBuilder.getProperties();

        return characterBuilder.getCharacter();
    }


    public long save(CharacterInputDto characterInputDto) {
        Character character = new Character();
        nameConvert(characterInputDto.getName(),character);
        surnameConvert(characterInputDto.getSurname(),character);
        predictionConvert(characterInputDto.getPrediction(),character);
        //currentCareerConvert(characterInputDto.getCurrentCareer(),character);
        currentCareerConvert("Akolita",character);
        previousCareersConvert(characterInputDto.getPreviousCareers(),character);
        hairColorConverter(characterInputDto.getHairColor(),character);
        eyeColorConverter(characterInputDto.getEyeColor(),character);
        bornPlaceConverter(characterInputDto.getBirthPlace(),character);
        livePlaceConverter(characterInputDto.getLivePlace(),character);
        sexConverter(characterInputDto.getSex(),character);
        raceConverter(characterInputDto.getRace(),character);
        religionConverter(characterInputDto.getReligion(),character);
        System.out.println(birthYearConverter(characterInputDto.getYearOfBirth()));
        characterService.save(character);


        return character.getId();
    }

    public Optional<Surname> surnameConvert(String surNew, Character character){
        Optional<Surname> surname = surnameService.findBySurname(surNew);
        surname.ifPresent(character::setSurname);
        if (!surname.isPresent() && surNew != null){
            Surname surnameNew = new Surname();
            surnameNew.setSurname(surNew);
            surnameService.save(surnameNew);
            character.setSurname(surnameNew);
        }
        return surname;
    }

    public Optional<Name> nameConvert(String inputName, Character character){//Exeption ma problem :CCCC To Do
        Optional<Name> nameOptional = nameService.findByName(inputName);
        nameOptional.ifPresent(character::setName);
        if (!nameOptional.isPresent() && inputName != null){
            Name nameNew = new Name();
            nameNew.setName(inputName);
            nameService.save(nameNew);
            character.setName(nameNew);
        }
        return nameOptional;
    }

    public Optional<Prediction> predictionConvert(String inputPrediction, Character character){
        Optional<Prediction> prediction = predictionService.findByText(inputPrediction);
        prediction.ifPresent(character::setPrediction);
        if (!prediction.isPresent() && inputPrediction != null){
            Prediction predictionNew = new Prediction();
            predictionNew.setText(inputPrediction);
            predictionService.save(predictionNew);
            character.setPrediction(predictionNew);
        }
        return prediction;
    }

    public Career currentCareerConvert(String inputCurrentCareer, Character character){
        List<String> stringList = new ArrayList<>();
        stringList.add(inputCurrentCareer);
        List <Career> careerList = careerService.findByNameIn(stringList);
        if (careerList != null){
            character.setCurrentCareer(careerList.get(0));
            return careerList.get(0);
        }
              else {
                  throw new IllegalArgumentException();
             }
    }

    public List<Career> previousCareersConvert(String inputPreviousCareers, Character character){
        if(inputPreviousCareers == null){
            character.setPreviousCareers(null);
            return null;
        }
        List<String> stringList = Arrays.asList(inputPreviousCareers.split(","));
        List<Career> careerList = careerService.findByNameIn(stringList);
        if (careerList != null){
            character.setPreviousCareers(careerList);
        }
        return careerList;
    }

    public Optional<HairColor> hairColorConverter(String inputHairColor, Character character){
        Optional<HairColor> hairColor = hairColorService.findByName(inputHairColor);
        hairColor.ifPresent(character::setHairColor);
        if (!hairColor.isPresent() && inputHairColor != null){
            HairColor hairColorNew = new HairColor();
            hairColorNew.setColor(inputHairColor);
            character.setHairColor(hairColorNew);
            //return hairColor;
        }
        if(hairColor.get() == null) {
            throw new IllegalArgumentException();
        }
        return hairColor;
    }

    public Optional<EyeColor> eyeColorConverter(String inputeyeColor, Character character){
        Optional<EyeColor> eyeColor = eyeColorService.findByName(inputeyeColor);
        eyeColor.ifPresent(character::setEyeColor);
        if (!eyeColor.isPresent() && inputeyeColor != null){
            EyeColor eyeColorNew = new EyeColor();
            eyeColorNew.setColor(inputeyeColor);
            character.setEyeColor(eyeColorNew);
        }
       if(eyeColor.get() == null) {
            throw new IllegalArgumentException();
        }
        return eyeColor;
    }

    public Optional<Place> bornPlaceConverter(String bornPlaceInput, Character character) {
        Optional<Place> bornPlace = placeService.findByName(bornPlaceInput);
        bornPlace.ifPresent(character::setBirthPlace);
        if (!bornPlace.isPresent() && bornPlaceInput != null){
            Place bornPlaceNew = new Place();
            bornPlaceNew.setName(bornPlaceInput);
            character.setBirthPlace(bornPlaceNew);
        }
        if(bornPlace.get() == null) {
            throw new IllegalArgumentException();
        }
        return bornPlace;
    }

    public Optional<Place> livePlaceConverter(String livePlaceInput, Character character) {
        Optional<Place> livePlace = placeService.findByName(livePlaceInput);
        livePlace.ifPresent(character::setLivePlace);
        if (!livePlace.isPresent() && livePlaceInput != null){
            Place bornPlaceNew = new Place();
            bornPlaceNew.setName(livePlaceInput);
            character.setLivePlace(bornPlaceNew);
        }
        if(livePlace.get() == null) {
            throw new IllegalArgumentException();
        }
        return livePlace;
    }


    public Sex sexConverter(String sexInput, Character character) {
        if(sexInput == null) {
            throw new IllegalArgumentException();
        }
        Sex newSex = Sex.MALE;
        if (sexInput.compareTo("Kobieta") == 0){
            newSex = Sex.FEMALE;
        }
        character.setSex(newSex);
        return newSex;
    }

    public Race raceConverter(String raceInput, Character character) {
        if(raceInput == null) {
            throw new IllegalArgumentException();
        }
        Race newRace = Race.HUMAN;
        if (raceInput.compareTo("Elf") == 0){ newRace = Race.ELF; }
        else if(raceInput.compareTo("Krasnolud") == 0){ newRace = Race.DWARF;}
        else if (raceInput.compareTo("Niziołek") == 0) { newRace = Race.HALFLING;}
        character.setRace(newRace);
        return newRace;
    }

    public Religion religionConverter(String religionInput, Character character) {
        if(religionInput == null) {
            throw new IllegalArgumentException();
        }
        Religion newReligion = Religion.findByGodName(religionInput);
        character.setReligion(newReligion);
        return newReligion;
    }

    public int birthDayConverter(String dayInput) {
        if(dayInput == null) {
            throw new IllegalArgumentException();
        }
        int day = 0;
        if (dayInput.matches("[0-9]+")){
            day = Integer.parseInt(dayInput);
            if (day < 1 || day > 34){
                throw new IllegalArgumentException();
            }
        }
        else {
            throw new IllegalArgumentException();
        }
        return day;
    }

    public int birthYearConverter(String yearInput) {
        if(yearInput == null) {
            throw new IllegalArgumentException();
        }
        short year = 0;
        if (yearInput.matches("[0-9]*")){
            year = Short.parseShort(yearInput);
            if (year < 0 || year > 3000){
                throw new IllegalArgumentException();
            }
        }
        else {
            throw new IllegalArgumentException();
        }
        return year;
    }
}
