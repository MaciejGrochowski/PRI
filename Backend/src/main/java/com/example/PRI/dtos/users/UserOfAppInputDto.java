package com.example.PRI.dtos.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOfAppInputDto {

    @Email (message ="NOT_EMAIL_REGEX")
    @Size(max=10000)
    String mail;

    @Size(max=100)
    String password;

    @Pattern(regexp = "^.{3,20}$", message = "WRONG_USERNAME_LENGTH")
    String username;

    @Size(max=100)
    String confirmPassword;

    @Pattern(regexp = "^(https:\\/\\/www.facebook.com\\/.*)$|^$", message = "WRONG_FACEBOOK_LINK")
    @Size(max=10000)
    String facebook;

    @Pattern(regexp = "^.[^#@:`']{1,31}#\\d\\d\\d\\d$|^$",message = "WRONG_DISCORD_NAME")
    @Size(max=10000)
    String discord;

    @Pattern(regexp = "^.{0,1000}$", message = "WRONG_DESCRYPTION_LENGTH")
    String description;
    //{"mail":"Chomik","password":"Chomik","username":"Chomik","confirmPassword":"Chomik","facebook":"Chomik","discord":"Chomik","description":"Chomik"}
}
