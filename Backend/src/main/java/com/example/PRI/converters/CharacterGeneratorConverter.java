package com.example.PRI.converters;

import com.example.PRI.dtos.characters.CharacterInputDto;

import com.example.PRI.entities.character.Character;

public class CharacterGeneratorConverter {

    public static Character convert(CharacterInputDto inputDto){

        Character output = new Character();
//        output.setSurname(new Surname(inputDto.getSurname()));


        return output; //ToDo zadanie dla Zosi - konwersja
    }


}
