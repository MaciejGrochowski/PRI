package com.example.PRI.dtos.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOfAppInputDto {
    String mail;
    String password;
    String username;
    String confirmPassword;
    String facebook;
    String discord;
    String description;
    //{"mail":"Chomik","password":"Chomik","username":"Chomik","confirmPassword":"Chomik","facebook":"Chomik","discord":"Chomik","description":"Chomik"}
}
