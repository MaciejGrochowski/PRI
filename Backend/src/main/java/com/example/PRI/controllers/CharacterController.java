package com.example.PRI.controllers;

import com.example.PRI.controllers.annotations.Get;
import com.example.PRI.dtos.characters.CharacterDefaultAttributesOutputDto;
import com.example.PRI.services.character.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        characterService.saveExampleCharacters();
    }




}
