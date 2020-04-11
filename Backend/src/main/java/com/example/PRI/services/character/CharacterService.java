package com.example.PRI.services.character;

import com.example.PRI.converters.CharacterConverter;
import com.example.PRI.dtos.characters.CharacterDefaultAttributesOutputDto;
import com.example.PRI.entities.character.Character;
import com.example.PRI.repositories.character.CharacterRepository;
import com.example.PRI.services.GeneralService;
import com.example.PRI.services.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CharacterService extends GeneralService {

    @Autowired
    CharacterRepository characterRepository;

    @Autowired
    NameService nameService;

    @Autowired
    SurnameService surnameService;

    @Autowired
    CareerService careerService;

    @Autowired
    PlaceService placeService;

    public List<CharacterDefaultAttributesOutputDto> getAllCharacters() {
        Iterable<Character> characters = characterRepository.findAll();
        List<CharacterDefaultAttributesOutputDto> output = new ArrayList<>();

        for(Character character : characters){
            output.add(CharacterConverter.convert(character));
        }
        return output;
    }

    public void save(Character character){
        nameService.save(character.getName());
        careerService.save(character.getCareers().get(character.getCareers().size()-1));
        placeService.save(character.getLivePlace());
        surnameService.save(character.getSurname());
        characterRepository.save(character);
    }


}
