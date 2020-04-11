package com.example.PRI.converters;

import com.example.PRI.dtos.characters.CharacterDefaultAttributesOutputDto;
import com.example.PRI.entities.character.Character;

public class CharacterConverter {

    public static CharacterDefaultAttributesOutputDto convert(Character character){
        CharacterDefaultAttributesOutputDto output = new CharacterDefaultAttributesOutputDto();
        output.setName(character.getName().getName());
        output.setSurname(character.getSurname()!=null ? character.getSurname().getSurname() : "");
        output.setRace(character.getRace());
        output.setSex(character.getSex());
        output.setCareerName(character.getCareers().get(character.getCareers().size()-1).getName());
        output.setLivePlace(character.getLivePlace().getName());
        return output;
    }

}
