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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        public ResponseEntity<?> updateUserDetails(@RequestBody UserOfAppCredentialsInputDto user, Authentication auth) throws notUniqueArgumentException {
            if(user.getUsername() == null || user.getUsername().equals("") && user.getUsername().length() < 4 ||
                    user.getUsername().length() > 20) return ResponseEntity.badRequest().body("BAD_USERNAME");

            Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(user.getNewPassword());
            boolean b = m.find();

            if(user.getNewPassword() == null || user.getNewPassword().length() < 6 || user.getNewPassword().length() > 64 ||
                    !user.getNewPassword().matches(".*\\d.*") || !b){
                return ResponseEntity.badRequest().body("WRONG_PASSWORD_FORM");
            }

            return userOfAppService.updateUserCredentials(user, auth);
        }



}