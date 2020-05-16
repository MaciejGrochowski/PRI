package com.example.PRI.controllers;


import com.example.PRI.controllers.annotations.Get;
import com.example.PRI.services.character.generator.CharacterGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.PRI.entities.character.Character;


@RestController
@RequestMapping(value = "/app/generator")
public class CharacterGeneratorController {

    @Autowired
    CharacterGenerator characterGenerator;

    @Get
    public Character generateFullCharacter() {
        return characterGenerator.generateFullCharacter();
    }



}