package com.example.PRI.controllers;

import com.example.PRI.controllers.annotations.Get;
import com.example.PRI.controllers.annotations.Post;
import com.example.PRI.dtos.characters.CharacterTagOutputDto;
import com.example.PRI.dtos.histories.*;
import com.example.PRI.services.history.HistoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/app/histories")
public class HistoryController {

    @Autowired
    HistoryService historyService;

    @Get("/count")
    public Long getCountCharacters() {
        return 1L;
    }

//    @Get
//    public List<HistoryOutputDto> getAllCharacters() {
//        return historyService.getAllHistories();
//    }

    @Get("/autocomplete/filters")
    public AutocompleteFiltersHistoriesOutputDto getAutocomplete(){
        return historyService.getAutoCompletes();
    }

    @Get("/characterstags")
    public List<CharacterTagOutputDto> getTags(){
        return historyService.getCharactersTags();
    }

    @Post
    public long save(@Valid @RequestBody HistoryInputDto historyInputDto, Authentication auth){

        return historyService.save(historyInputDto, auth);
    }

    @Get("/paged")
    public HistoryListOutputDto getSomeHistoriesPaged(HistoryListFilterInputStringDto historyListInput) {
        Map<String, String> map = this.getMapFromString(historyListInput.getFilters());
        //ToDo konwersja ta powinna być niejawna, zapewniona przez mechanizmy springa
        HistoryListFilterInputDto historyListInputDto = new HistoryListFilterInputDto(historyListInput.getSortedBy(), historyListInput.getIsAscending(),
                map, historyListInput.getCurrentPage(), historyListInput.getRowsPerPage());
        return historyService.getSomeHistoriesPaged(historyListInputDto);
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
//    }
//
//    @Get("/example")
//    public List<CharacterDefaultAttributesOutputDto> getSomeCharactersByHeight() {
//        return characterService.getByHeight();
//    }
//


    @Get("/{id}")
    public HistoryDetailsOutputDto getDetailsById(@PathVariable Long id){
        HistoryDetailsOutputDto output = historyService.getDetailsById(id);
        return output;
    }

    @Get("/character/{character}/{id}")
    public List<HistoryListCharacterDetailsOutputDto> getFirstHistoriesOfCharacter(@PathVariable String character, @PathVariable Long id){
        character = character.replace("-", " ");
        character += "#" + id.toString();
        return historyService.getFirstHistoriesOfCharacter(character);
    }

}