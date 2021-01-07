package com.example.PRI.dtos.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOfAppCredentialsInputDto {

    @NotBlank
    String username;

    @NotBlank
    String oldPassword;

    @NotBlank
    String newPassword;
}
