package com.example.PRI.controllers;

import com.example.PRI.controllers.annotations.Get;
import com.example.PRI.dtos.users.UserOfAppDetailsOutputDto;
import com.example.PRI.services.UserOfAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

//
//    @RequestMapping(value = "/change-mail", method = RequestMethod.POST)
//    private void changeMail(Authentication auth) {
//        userService.changeMailOfUser(auth);
//        return;
//    }
}