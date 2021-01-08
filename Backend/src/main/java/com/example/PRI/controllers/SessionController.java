package com.example.PRI.controllers;


import com.example.PRI.controllers.annotations.Get;
import com.example.PRI.controllers.annotations.Post;
import com.example.PRI.dtos.sessions.SessionInputDto;
import com.example.PRI.dtos.sessions.SessionOutputDto;
import com.example.PRI.dtos.users.JwtResponse;
import com.example.PRI.services.session.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/app/sessions")
public class SessionController {

    @Autowired
    SessionService sessionService;


    @Post
    public ResponseEntity<?> createSession(@RequestBody SessionInputDto sessionInputDto, Authentication auth) {

        Long id = sessionService.createSession(sessionInputDto, auth);

        if(id.equals(-1L)) return ResponseEntity.badRequest().body("USER_DOESNT_EXIST");

        return ResponseEntity.ok(new JwtResponse(id.toString()));
    }

    @Get("/{username}")
    public List<SessionOutputDto> getSessionsByUser(@PathVariable String username){
        List<SessionOutputDto> sessionListOutputDto = sessionService.getSessionsByUser(username);
        return sessionListOutputDto;
    }

}
