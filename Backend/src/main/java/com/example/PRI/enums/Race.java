package com.example.PRI.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Race {
    ELF("elf", "{Agility: 10, BallisticSkill: 10, Wounds: -1, Skills{}, Talents:{}, "),
    DWARF("krasnolud", "{Agility: -10, Strength: 10, WeaponSkill: 10, Fellowship: -10, Wounds: 1, Skills{}, Talents:{}, "),
    HALFLING("niziołek", "{Agility: 10, BallisticSkill: 10, Strength: -10, Toughness: -10, Fellowship: 10, , Wounds: -1, Skills{}, Talents:{}, "),
    HUMAN("człowiek", "{Skills{}, Talents:{}, ");

    String name;
    String properties;
}
