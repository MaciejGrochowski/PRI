package com.example.PRI.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Sex {

    FEMALE("Kobieta", "averageWeight:-5,averageHeight:-5"),
    MALE("Mężczyzna", "averageWeight:5,averageHeight:5");

    String name;
    String properties;

    public String getProperties() {
        return properties;
    }

    public String getName() {
        return name;
    }
}
