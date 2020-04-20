package com.example.PRI.controllers;

import com.example.PRI.controllers.annotations.Get;
import com.example.PRI.dtos.characters.CharacterDefaultAttributesOutputDto;
import com.example.PRI.services.character.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/app/characters")
public class CharacterController {

    @Autowired
    CharacterService characterService;

    @Get("/count")
    public Long getCountCharacters() {
        return characterService.getCountCharacters();
    }

    @Get
    public List<CharacterDefaultAttributesOutputDto> getAllCharacters() {
        return characterService.getAllCharacters();
    }

    @Get("/produce_test_characters")
    public void produceTestCharacters() {
        characterService.saveExampleCharacters();
    }

    @Get("/{count}/{page}")
    public List<CharacterDefaultAttributesOutputDto> getSomeCharactersPaged(@PathVariable Integer count, @PathVariable Integer page) {
        return characterService.getSomeCharactersPaged(count, page);
    }

    @Get("/example")
    public List<CharacterDefaultAttributesOutputDto> getSomeCharactersByHeight() {
        return characterService.getByHeight();
    }
}