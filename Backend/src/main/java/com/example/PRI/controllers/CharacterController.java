package com.example.PRI.controllers;

import com.example.PRI.controllers.annotations.Get;
import com.example.PRI.controllers.annotations.Post;
import com.example.PRI.dtos.characters.*;
import com.example.PRI.services.character.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Post("/paged") //ToDo to powinno być getem, ale jeśli będzie getem to nie może mieć RequestBody :/
    public CharacterListOutputDto getSomeCharactersPaged(@RequestBody CharacterListFilterInputDto characterListInput) {
        return characterService.getSomeCharactersPaged(characterListInput);
    }

    @Get("/example")
    public List<CharacterDefaultAttributesOutputDto> getSomeCharactersByHeight() {
        return characterService.getByHeight();
    }

    @Get("/autocomplete/filters")
    public AutocompleteFilterCharactersOutputDto getAutocomplete(){
        return characterService.getAutoCompletes();
    }

    @Get("/character/{id}")
    public CharacterDetailsOutputDto getDetailsById(@PathVariable Long id){
        CharacterDetailsOutputDto output = characterService.getDetailsById(id);
        return output;
    }
}