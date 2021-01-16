package com.example.PRI.controllers;

import com.example.PRI.services.EmailService;
import com.example.PRI.controllers.annotations.Get;
import com.example.PRI.controllers.annotations.Put;
import com.example.PRI.dtos.users.UserOfAppCredentialsInputDto;
import com.example.PRI.dtos.users.UserOfAppDetailsOutputDto;
import com.example.PRI.dtos.users.UserOfAppDetailsInputDto;
import com.example.PRI.exceptions.notUniqueArgumentException;
import com.example.PRI.services.UserOfAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/app/users")
public class UserOfAppController {

    @Autowired
    UserOfAppService userOfAppService;


        @Get("/user/{username}")
        public ResponseEntity<?> getDetailsByUsername(@PathVariable String username){
            UserOfAppDetailsOutputDto output = userOfAppService.getDetailsByUsername(username);
            if(output==null){
                return ResponseEntity.badRequest().body("USER_DOESNT_EXIST");
            }
            return ResponseEntity.ok(output);
        }

        @Put
        public void updateUserDetails(@Valid @RequestBody UserOfAppDetailsInputDto user, Authentication auth){
            userOfAppService.updateUser(user, auth);
        }

        @Put("/credentials")
        public void updateUserDetails(@Valid @RequestBody UserOfAppCredentialsInputDto user, Authentication auth) throws notUniqueArgumentException {
            userOfAppService.updateUserCredentials(user, auth);
        }



}