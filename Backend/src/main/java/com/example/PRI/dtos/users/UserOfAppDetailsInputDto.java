package com.example.PRI.dtos.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOfAppDetailsInputDto implements Serializable {

    String description;

//    @NotBlank(message = "NO_EMAIL")
//    String mail;
    String discord;
    String facebook;

}
