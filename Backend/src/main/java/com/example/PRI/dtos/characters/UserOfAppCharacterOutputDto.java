package com.example.PRI.dtos.characters;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOfAppCharacterOutputDto {

    String name;
    String surname;
    String race;
    String sex;
    String career;
    String livePlace;
}
