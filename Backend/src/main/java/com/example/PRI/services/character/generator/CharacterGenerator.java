package com.example.PRI.services.character.generator;

import com.example.PRI.dtos.characters.CharacterInputDto;
import com.example.PRI.entities.character.*;
import com.example.PRI.entities.character.Character;
import com.example.PRI.enums.Sex;
import com.example.PRI.services.GeneralService;
import com.example.PRI.services.character.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.stream.events.Namespace;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class CharacterGenerator extends GeneralService {

    @Autowired
    private CharacterBirthPlaceGenerator characterBirthPlaceGenerator;

    @Autowired
    private SurnameGenerator surnameGenerator;

    @Autowired
    SurnameService surnameService;

    @Autowired
    NameService nameService;

    @Autowired
    PredictionService predictionService;

    @Autowired
    CareerService careerService;

    @Autowired
    CharacterService characterService;

    public Character generateFullCharacter(){

        CharacterBuilder characterBuilder = new CharacterBuilder();
        characterBuilder.initialize()
                .buildBirthPlace(characterBirthPlaceGenerator)
                .buildRace(new RaceGenerator())
                .buildSex(new SexGenerator())
                .buildSurname(surnameGenerator)
                .buildBaseStats(new StatisticsGenerator())
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
        currentCareerConvert(characterInputDto.getCurrentCareer(),character);
        //characterService.save(character);


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

    public Optional<Name> nameConvert(String inputName, Character character){
        Optional<Name> nameOptional = nameService.findByName(inputName);
        nameOptional.ifPresent(character::setName);
        if (!nameOptional.isPresent() && inputName != null){
            Name nameNew = new Name();
            nameNew.setName(inputName);
            nameService.save(nameNew);
            character.setName(nameNew);
        }
        else {
            throw new IllegalArgumentException();
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
}
