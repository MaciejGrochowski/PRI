package com.example.PRI.controllers;

import com.example.PRI.controllers.annotations.Get;
import com.example.PRI.controllers.annotations.Put;
import com.example.PRI.dtos.users.JwtRequest;
import com.example.PRI.dtos.users.UserOfAppInputDto;
import com.example.PRI.dtos.users.UserOfAppDetailsOutputDto;
import com.example.PRI.exceptions.notUniqueArgumentException;
import com.example.PRI.services.UserOfAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/app/users")
public class UserOfAppController {

    @Autowired
    UserOfAppService userOfAppService;



        @Get("/user/{username}")
        public UserOfAppDetailsOutputDto getDetailsByUsername(@PathVariable String username){
            UserOfAppDetailsOutputDto output = userOfAppService.getDetailsByUsername(username);
            return output;
        }

        @Put
        public void updateUserDetails(@Valid @RequestBody UserOfAppInputDto user, Authentication auth){
            userOfAppService.updateUser(user, auth);
        }

        @RequestMapping(value = "/update/credentials", method = RequestMethod.PUT)
        public void updateUserDetails(@Valid @RequestBody JwtRequest user, Authentication auth) throws notUniqueArgumentException {
            userOfAppService.updateUserCredentials(user, auth);
        }


}