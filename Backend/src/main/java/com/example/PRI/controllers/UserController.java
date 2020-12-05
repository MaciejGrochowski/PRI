package com.example.PRI.controllers;

import com.example.PRI.controllers.annotations.Get;
import com.example.PRI.dtos.characters.CharacterDetailsOutputDto;
import com.example.PRI.dtos.users.UserDetailsOutputDto;
import com.example.PRI.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/app/users")
public class UserController {

    @Autowired
    UserService userService;



        @Get("/user/{username}")
        public UserDetailsOutputDto getDetailsByUsername(@PathVariable String username){
            UserDetailsOutputDto output = userService.getDetailsByUsername(username);
            return output;
        }

//
//    @RequestMapping(value = "/change-mail", method = RequestMethod.POST)
//    private void changeMail(Authentication auth) {
//        userService.changeMailOfUser(auth);
//        return;
//    }
}