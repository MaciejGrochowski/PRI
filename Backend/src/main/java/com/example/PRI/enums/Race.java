package com.example.PRI.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Race {
    HUMAN("Człowiek",
            "Skills:{}," +
            "Talents:{}," +
            "averageWeight:60," +
            "averageHeight:165," +
            "adultAge:16," +
            "elderAge:40," +
            "oldAge:65," +
            "maxAge:110"
            ),
    ELF("Elf",
            "Agility:10," +
                    "BallisticSkill:10," +
                    "Wounds:-1," +
                    "Skills:{}," +
                    "Talents:{}," +
                    "averageWeight:60,"+
                    "averageHeight:175," +
                    "adultAge:25," +
                    "elderAge:140," +
                    "oldAge:225," +
                    "maxAge:400,"+
                    "Wolność:0.03,"+
                    "Chłód:0.04,"+
                    "Irytacja:0.03,"+
                    "Harmonia:0.05,"+
                    "Uspokojenie:0.04,"+
                    "Pogarda:0.04,"+
                    "Refleksja:0.04,"+
                    "Tęsknota:0.03"
    ),
    DWARF("Krasnolud",
            "Mężczyzna:0.8," +
                    "Agility:-10," +
                    "Strength:10," +
                    "WeaponSkill:10," +
                    "Fellowship:-10," +
                    "Wounds:1," +
                    "Skills:{}," +
                    "Talents:{}," +
                    "averageWeight:65,"+
                    "averageHeight:145," +
                    "adultAge:20," +
                    "elderAge:120," +
                    "oldAge:190," +
                    "maxAge:320,"+
                    "Żal:0.03,"+
                    "Nienawiść:0.03,"+
                    "Niechęć:0.03,"+
                    "Gniew:0.03,"+
                    "Zdenerwowanie:0.03,"+
                    "Wrogość:0.03,"+
                    "Tęsknota:0.03,"+
                    "Zniewaga:0.03,"+
                    "Uraza:0.03,"+
                    "Złość:0.03"),
    HALFLING("Niziołek",
            "Agility:10," +
                    "BallisticSkill:10," +
                    "WeaponSkill:-10," +
                    "Strength:-10," +
                    "Toughness:-10," +
                    "Fellowship:10," +
                    "Wounds:-2," +
                    "Skills:{}," +
                    "Talents:{},"+
                    "averageWeight:50,"+
                    "averageHeight:115," +
                    "adultAge:20," +
                    "elderAge:60," +
                    "oldAge:95," +
                    "maxAge:130,"+
                    "Entuzjazm:0.05,"+
                    "Ekscytacja:0.05,"+
                    "Dobroć:0.05,"+
                    "Rozluźnienie:0.05,"+
                    "Zapał:0.05"
            );

    String name;
    String properties;

    public String getProperties() {
        return properties;
    }

    public String getName() {
        return name;
    }
}
