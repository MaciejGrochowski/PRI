package com.example.PRI.controllers;


import com.example.PRI.controllers.annotations.Delete;
import com.example.PRI.controllers.annotations.Get;
import com.example.PRI.controllers.annotations.Post;
import com.example.PRI.controllers.annotations.Put;
import com.example.PRI.dtos.sessions.*;
import com.example.PRI.dtos.users.JwtResponse;
import com.example.PRI.entities.session.AttributesVisibility;
import com.example.PRI.services.session.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/app/sessions")
public class SessionController {

    @Autowired
    SessionService sessionService;


    @Post
    public ResponseEntity<?> createSession(@RequestBody @Valid SessionInputDto sessionInputDto, Authentication auth) {

        Long id = sessionService.createSession(sessionInputDto, auth);

        if (id.equals(-1L)) return ResponseEntity.badRequest().body("USER_DOESNT_EXIST");

        return ResponseEntity.ok(new JwtResponse(id.toString()));
    }

    @Get("/{username}")
    public List<SessionOutputDto> getSessionsByUser(@PathVariable String username, Authentication auth) {
        List<SessionOutputDto> sessionListOutputDto = sessionService.getSessionsByUser(username, auth);
        return sessionListOutputDto;
    }


    @Post("/characters")
    public ResponseEntity<?> addCharactersToSession(@RequestBody CharactersSessionInputDto charactersSessionInputDto, Authentication auth) {
        Long id = sessionService.addCharactersToSession(charactersSessionInputDto, auth);

        if (id.equals(-1L)) return ResponseEntity.badRequest().body("BAD_REQUEST");

        return ResponseEntity.ok(new JwtResponse(id.toString()));
    }

    @Get("/details/{hashcode}")
    public ResponseEntity<SessionDetailsOutputDto> getSessionDetailsByHashString(@PathVariable String hashcode, Authentication auth) {
        SessionDetailsOutputDto output = sessionService.getSessionDetailsByHashString(hashcode, auth);

        if (output == null) return ResponseEntity.badRequest().body(null);

        return ResponseEntity.ok(output);
    }


    @Put //ToDo should we get hashcode session from url?
    public ResponseEntity<?> editSession(@RequestBody SessionEditInputDto sessionEditInputDto, Authentication auth) {
        ResponseEntity<?> output = sessionService.editSession(sessionEditInputDto, auth);
        return output;
    }


    @Delete("/{hashcode}/character/{id}")
    public ResponseEntity<?> deleteCharacterFromSession(@PathVariable String hashcode, @PathVariable Long id, Authentication auth){
        return sessionService.deleteCharacterFromSession(hashcode, id, auth);
    }


    @Put("/{hashcode}/visibility")
    public ResponseEntity<?> changeGlobalVisibilityCharactersDataOfSession(@PathVariable String hashcode, @RequestBody AttributesVisibilityInputDto attributesVisibilityInputDto, Authentication auth){
        return sessionService.changeGlobalVisibilityCharactersDataOfSession(hashcode, attributesVisibilityInputDto, auth);
    }

    @Put("/{hashcode}/visibility/{characterId}")
    public ResponseEntity<?> changeCharacterVisibilityInSession(@PathVariable String hashcode, @PathVariable Long characterId,
                                                                @RequestBody AttributesVisibilityInputDto attributesVisibilityInputDto, Authentication auth){
        return sessionService.changeCharacterVisibilityInSession(hashcode, characterId, attributesVisibilityInputDto, auth);
    }

}
