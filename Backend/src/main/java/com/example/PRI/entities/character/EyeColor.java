package com.example.PRI.entities.character;

import com.example.PRI.entities.GeneralEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity(name ="eye_colors")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EyeColor extends GeneralEntity {

    String color;
    double chanceIfElf;
    double chanceIfDwarf;
    double chanceIfHuman;
    double changeIfHalfling;

}
