package com.example.PRI.dtos.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOfAppCredentialsInputDto {

    @NotBlank
    @Pattern(regexp = "^.{3,20}$", message = "WRONG_USERNAME_LENGTH")
    String username;

    @NotBlank
    @Pattern(regexp = "^(.{3,64})&(([a-zA-Z]*[\\|!@#$%^&*()\\-_\\\\\\/><.,=+~`'\"{}\\[\\]:;\\^]*)*)&(([a-zA-Z]*\\d*)*)&(([a-z]*[\\|!@#$%^&*()\\-_\\\\\\/><.,=+~`'\"{}\\[\\]:;\\^]*\\d*)*)$", message = "WRONG_PASSWORD_FORM")
    String oldPassword;

    @NotBlank
    @Pattern(regexp = "^(.{3,64})&(([a-zA-Z]*[\\|!@#$%^&*()\\-_\\\\\\/><.,=+~`'\"{}\\[\\]:;\\^]*)*)&(([a-zA-Z]*\\d*)*)&(([a-z]*[\\|!@#$%^&*()\\-_\\\\\\/><.,=+~`'\"{}\\[\\]:;\\^]*\\d*)*)$", message = "WRONG_PASSWORD_FORM")
    String newPassword;
}
