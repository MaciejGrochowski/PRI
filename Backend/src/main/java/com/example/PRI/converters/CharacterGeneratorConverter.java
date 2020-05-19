package com.example.PRI.converters;

import com.example.PRI.dtos.characters.CharacterInputDto;

import com.example.PRI.entities.character.Character;
import com.example.PRI.services.character.SurnameService;

public class CharacterGeneratorConverter {

    public static Character convert(CharacterInputDto inputDto, SurnameService surnameService){

        Character output = new Character();
        return output; //ToDo zadanie dla Zosi - konwersja
    }


}
