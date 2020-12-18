package com.example.PRI.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CharacterAttribute {

    NAME("name"),
    SURNAME("surname"),
    BIRTHPLACE("birthPlace"),
    RACE("race"),
    EYECOLOR("eyeColor"),
    HAIRCOLOR("hairColor"),
    STARSIGN("starSign"),
    BIRTHDATE("birthDate"),
    BASESTATS("baseStats"),
    DOMINATINGEMOTIONS("dominatingEmotions"),
    SEX("sex"),
    RELIGION("religion"),
    WEIGHT("weight"),
    HEIGHT("height"),
    PREDICTION("prediction"),
    CURRENTCAREER("currentCareer"),
    SKILLS("skills"),
    TALENTS("talents"),
    PERSONALITY("personality"),
    APPERANCE("apperance"),
    LIVEPLACE("livePlace"),
    CREATEDBY("createdBy"),
    ENDSTATS("endStats");

    private final String name;

    public String getName() {
        return name;
    }
}
