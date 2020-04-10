package com.example.PRI.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Sex {
    MALE(0.5, 0.5, 0.9, 0.5),
    FEMALE(0.5, 0.5, 0.1, 0.5);

    double ifHumanChance;
    double ifHalflingChance;
    double ifDwarfChance;
    double ifElfChance;

}
