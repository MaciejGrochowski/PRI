package com.example.PRI.services.character.generator;

import com.example.PRI.converters.CharacterGeneratorConverter;
import com.example.PRI.dtos.characters.CharacterInputDto;
import com.example.PRI.entities.Place;
import com.example.PRI.entities.character.*;
import com.example.PRI.entities.character.Character;
import com.example.PRI.enums.Race;
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

    public Optional<Place> bornPlaceConverter(String bornPlaceColor, Character character) {
        Optional<Place> bornPlace = placeService.findByName(bornPlaceColor);
        bornPlace.ifPresent(character::setBirthPlace);
        if (!bornPlace.isPresent() && bornPlaceColor != null){
            Place bornPlaceNew = new Place();
            bornPlaceNew.setName(bornPlaceColor);
            character.setBirthPlace(bornPlaceNew);
        }
        if(bornPlace.get() == null) {
            throw new IllegalArgumentException();
        }
        return bornPlace;
    }
}
