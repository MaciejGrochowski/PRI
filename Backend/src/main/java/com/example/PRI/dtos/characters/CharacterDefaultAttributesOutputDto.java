package com.example.PRI.dtos.characters;

import com.example.PRI.enums.Race;
import com.example.PRI.enums.Sex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CharacterDefaultAttributesOutputDto {
    String name;
    String surname;
    Sex sex;
    String careerName;
    Race race;
    String livePlace;

}
