package com.example.PRI.entities.character;

import com.example.PRI.entities.GeneralEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity(name ="hair_colors")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HairColor extends GeneralEntity {
    String color;
    double chanceIfElf;
    double chanceIfDwarf;
    double chanceIfHuman;
    double changeIfHalfling;
}
