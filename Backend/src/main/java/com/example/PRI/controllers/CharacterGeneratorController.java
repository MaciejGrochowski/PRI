package com.example.PRI.controllers;


import com.example.PRI.controllers.annotations.Get;
import com.example.PRI.controllers.annotations.Post;
import com.example.PRI.dtos.characters.CharacterInputDto;
import com.example.PRI.services.character.generator.CharacterGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.PRI.entities.character.Character;

import java.util.Date;


@RestController
@RequestMapping(value = "/app/generator")
public class CharacterGeneratorController {

    @Autowired
    CharacterGenerator characterGenerator;

    @Get
    public Character generateFullCharacter() {
//        Date start = new Date();
//        for (int i=0; i<1000;i++) {
//            if(i%100 == 0) System.out.println(i);
//            characterGenerator.generateFullCharacter();
//        }
//        Date end = new Date();
//        System.err.println(end.getTime() - start.getTime()); ToDo Time testing

        return characterGenerator.generateFullCharacter();
    }

    @Post("/save")
    public Integer save(@RequestBody CharacterInputDto character){

        characterGenerator.save(character);

        return 0;
    }



}
