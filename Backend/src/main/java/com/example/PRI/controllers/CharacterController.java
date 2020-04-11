package com.example.PRI.controllers;

import com.example.PRI.controllers.annotations.Get;
import com.example.PRI.dtos.characters.CharacterDefaultAttributesOutputDto;
import com.example.PRI.entities.Place;
import com.example.PRI.entities.character.Career;
import com.example.PRI.entities.character.Character;
import com.example.PRI.entities.character.Name;
import com.example.PRI.enums.Sex;
import com.example.PRI.services.character.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/app/characters")
public class CharacterController {

    @Autowired
    CharacterService characterService;


    @Get
    public List<CharacterDefaultAttributesOutputDto> getAllCharacters(){
        return characterService.getAllCharacters();
    }

    @Get("/produce_test_characters")
    public void produceTestCharacters(){
        Character character = new Character();
        Name name = new Name();
        name.setDwarf(false);
        name.setElf(false);
        name.setHalfling(false);
        name.setHuman(true);
        name.setMale(true);
        name.setFemale(false);
        name.setProbabilityGentry(0.01);
        name.setProbabilityNotGentry(0.1);
        name.setUsedByGenerator(true);
        name.setName("Johann");

        Name name2 = new Name();
        name2.setDwarf(false);
        name2.setElf(false);
        name2.setHalfling(false);
        name2.setHuman(true);
        name2.setMale(false);
        name2.setFemale(true);
        name2.setProbabilityGentry(0.02);
        name2.setProbabilityNotGentry(0.05);
        name2.setUsedByGenerator(true);
        name2.setName("Gertruda");

        Career career = new Career();
        career.setName("Chłop");
        career.setExitChance(0.2);
        career.setBaseProfession(true);

        Career career2 = new Career();
        career2.setName("Rybak");
        career2.setExitChance(0.1);
        career2.setBaseProfession(true);

        Place place = new Place();
        place.setName("Altdorf");

        character.setName(name);
        character.setSurname(null);
        character.setCareers(Collections.singletonList(career));
        character.setSex(Sex.MALE);
        character.setLivePlace(place);
        character.setHeight(180);
        character.setWeight(80);

        characterService.save(character);

        Character character2 = new Character();
        character2.setName(name2);
        character2.setSurname(null);
        character2.setCareers(Collections.singletonList(career2));
        character2.setSex(Sex.FEMALE);
        character2.setLivePlace(place);
        character.setHeight(160);
        character.setWeight(55);

        characterService.save(character2);


    }




}
