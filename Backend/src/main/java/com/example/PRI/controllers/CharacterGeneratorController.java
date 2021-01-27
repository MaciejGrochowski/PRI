package com.example.PRI.controllers;


import com.example.PRI.controllers.annotations.Get;
import com.example.PRI.controllers.annotations.Post;
import com.example.PRI.dtos.characters.CharacterDetailsOutputDto;
import com.example.PRI.dtos.characters.CharacterInputDto;
import com.example.PRI.entities.character.Character;
import com.example.PRI.services.character.generator.CharacterGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


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

    @Get("/details")
    public CharacterDetailsOutputDto generateDetailedCharacter(){
//        Date start = new Date();
//        for (int i=0; i<1000;i++) {
//            if(i%100 == 0) System.out.println(i);
//            characterGenerator.generateCharacterDetails();
//        }
//        Date end = new Date();
//        System.err.println(end.getTime() - start.getTime());

        return characterGenerator.generateCharacterDetails();
    }

    @Get("/attribute/{attrName}")
    public CharacterDetailsOutputDto getAttribute(@PathVariable String attrName, @Valid CharacterInputDto character) throws Exception {
        character = this.trimCharacter(character);
        return characterGenerator.generateAttribute(attrName, character);
    }

    @Post("/save")
//    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public long save(@Valid @RequestBody CharacterInputDto character, Authentication auth) throws Exception {
        character = this.trimCharacter(character);
        return characterGenerator.save(character, auth);
    }

    private CharacterInputDto trimCharacter(CharacterInputDto character) {
        //Niekiedy dane przychodzą z dwiema spacjami, więc je trimujemy na wszelki wypadek... ToDo ogarnąć to na froncie...
        //ToDo to na pewno nie powinno tutaj być...
        if (character.getPreviousCareers() != null)character.setPreviousCareers(character.getPreviousCareers().replaceAll(",\\s+",","));
        if (character.getApperance() != null)character.setApperance(character.getApperance().replaceAll(",\\s+",","));
        if (character.getPersonality() != null) character.setPersonality(character.getPersonality().replaceAll(",\\s+",","));
        if (character.getTalents() != null)character.setTalents(character.getTalents().replaceAll(",\\s+",","));
        if (character.getSkills() != null)character.setSkills(character.getSkills().replaceAll(",\\s+",","));
        if (character.getDominatingEmotions() != null)character.setDominatingEmotions(character.getDominatingEmotions().replaceAll(",\\s+",","));
        return character;
    }


}
