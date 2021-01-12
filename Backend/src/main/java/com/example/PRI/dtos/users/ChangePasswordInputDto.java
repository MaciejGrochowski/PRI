package com.example.PRI.dtos.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordInputDto {

    @Pattern(regexp = "^.{3,20}$", message = "WRONG_USERNAME_LENGTH")
    String username;

    //Pattern(regexp = "",message = "")
    String hashcode;

    @Pattern(regexp = "^.{2,21}$", message = "WRONG_PASSWORD_FORM")
    String newPassword;
}
