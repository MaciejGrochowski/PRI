package com.example.PRI.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Race {
    HUMAN("Człowiek",
            "Skills:{}," +
            "Talents:{}," +
            "averageWeight:60," +
            "averageHeight:165"
            ),
    ELF("Elf",
            "{Agility:10," +
                    "BallisticSkill:10," +
                    "Wounds:-1," +
                    "Skills:{}," +
                    "Talents:{}," +
                    "averageWeight:60,"+
                    "averageHeight:175"),
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
                    "averageHeight:145"),
    HALFLING("Niziołek",
            "Agility:10," +
                    "BallisticSkill:10," +
                    "Strength:-10," +
                    "Toughness:-10," +
                    "Fellowship:10," +
                    "Wounds:-1," +
                    "Skills:{}," +
                    "Talents:{},"+
                    "averageWeight:50,"+
                    "averageHeight:115");

    String name;
    String properties;

    public String getProperties() {
        return properties;
    }

    public String getName() {
        return name;
    }
}
