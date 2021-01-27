package com.example.PRI.dtos.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOfAppInputDto {

    @Pattern(regexp = "^(([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?))&(^.{2,200}$)$", message ="NOT_EMAIL_REGEX" )
    @Size(max=10000)
    String mail;

    @Pattern(regexp = "^(.{3,64})&(([a-zA-Z]*[\\|!@#$%^&*()\\-_\\\\\\/><.,=+~`'\"{}\\[\\]:;\\^]*)*)&(([a-zA-Z]*\\d*)*)&(([a-z]*[\\|!@#$%^&*()\\-_\\\\\\/><.,=+~`'\"{}\\[\\]:;\\^]*\\d*)*)$", message = "WRONG_PASSWORD_FORM")
    String password;

    @Pattern(regexp = "^.{3,20}$", message = "WRONG_USERNAME_LENGTH")
    String username;

    @Pattern(regexp = "^(.{3,64})&(([a-zA-Z]*[\\|!@#$%^&*()\\-_\\\\\\/><.,=+~`'\"{}\\[\\]:;\\^]*)*)&(([a-zA-Z]*\\d*)*)&(([a-z]*[\\|!@#$%^&*()\\-_\\\\\\/><.,=+~`'\"{}\\[\\]:;\\^]*\\d*)*)$", message = "WRONG_PASSWORD_FORM")
    String confirmPassword;

    @Pattern(regexp = "^(https:\\/\\/www.facebook.com\\/.*)$|^$", message = "WRONG_FACEBOOK_LINK")
    @Size(max=10000)
    String facebook;

    @Pattern(regexp = "^.[^#@:`']{1,31}#\\d\\d\\d\\d$",message = "WRONG_DISCORD_NAME")
    @Size(max=10000)
    String discord;

    @Pattern(regexp = "^.{0,1000}$", message = "WRONG_DESCRYPTION_LENGTH")
    String description;
    //{"mail":"Chomik","password":"Chomik","username":"Chomik","confirmPassword":"Chomik","facebook":"Chomik","discord":"Chomik","description":"Chomik"}
}
