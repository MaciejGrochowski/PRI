package com.example.PRI.dtos.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordInputDto {

    @Pattern(regexp = "^.{3,20}$", message = "WRONG_USERNAME_LENGTH")
    String username;

    //Pattern(regexp = "",message = "")
    @Size(max=10000)
    String hashcode;

    @Pattern(regexp = "^.{2,21}$", message = "WRONG_PASSWORD_FORM")
    String newPassword;
}
