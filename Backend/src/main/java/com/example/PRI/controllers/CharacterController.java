package com.example.PRI.controllers;

import com.example.PRI.controllers.annotations.Get;
import com.example.PRI.dtos.characters.*;
import com.example.PRI.services.character.CharacterService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    @Get("/paged")
    public CharacterListOutputDto getSomeCharactersPaged(CharacterListFilterInputStringDto characterListInput) {
        Map<String, String> map = this.getMapFromString(characterListInput.getFilters());
        //ToDo konwersja ta powinna być niejawna, zapewniona przez mechanizmy springa
        CharacterListFilterInputDto characterListInputDto = new CharacterListFilterInputDto(characterListInput.getSortedBy(), characterListInput.getIsAscending(),
                map, characterListInput.getCurrentPage(), characterListInput.getRowsPerPage());
        return characterService.getSomeCharactersPaged(characterListInputDto);
    }

    private Map<String, String> getMapFromString(String filters) {
        ObjectMapper mapper = new ObjectMapper();
        try {

            // convert JSON string to Map
            Map<String, String> map = mapper.readValue(filters, Map.class);

            return map;

        } catch (NullPointerException e) {
            return new HashMap<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new HashMap<>();
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