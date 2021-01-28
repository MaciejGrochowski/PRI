package com.example.PRI.dtos.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOfAppCredentialsInputDto {

    @NotBlank
    @Pattern(regexp = "^.{3,20}$", message = "WRONG_USERNAME_LENGTH")
    String username;

    @Size(max=1000)
    String oldPassword;

    @NotBlank
    @Size(max=1000)
    String newPassword;
}
