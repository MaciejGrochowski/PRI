package com.example.PRI.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Race {
    HUMAN("Człowiek",
            "Skills:{}," +
                    "Talents:{}," +
                    "Movement:0," +
                    "averageWeight:60," +
                    "averageHeight:165," +
                    "adultAge:16," +
                    "elderAge:40," +
                    "oldAge:65," +
                    "maxAge:110," +
                    "Akolita:0.01," +
                    "Banita:0.01," +
                    "Goniec:0.0," +
                    "Guślarz:0.005," +
                    "Rzecznik rodu:0," +
                    "Strażnik pól:0," +
                    "Tarczownik:0," +
                    "Uczeń czarodzieja: 0.003,"+
                    "Zabójca trolli:0"
    ),
    ELF("Elf",
            "Agility:10," +
                    "BallisticSkill:10," +
                    "Wounds:-1," +
                    "Movement:1," +
                    "Skills:{}," +
                    "Talents:{}," +
                    "averageWeight:60," +
                    "averageHeight:175," +
                    "adultAge:25," +
                    "elderAge:140," +
                    "oldAge:225," +
                    "maxAge:400," +
                    "Wolność:0.03," +
                    "Chłód:0.04," +
                    "Irytacja:0.03," +
                    "Harmonia:0.05," +
                    "Uspokojenie:0.04," +
                    "Pogarda:0.04," +
                    "Refleksja:0.04," +
                    "Tęsknota:0.03," +
                    "Banita:0.02," +
                    "Chłop:0," +
                    "Ciura obozowa:0," +
                    "Goniec:0," +
                    "Leśnik:0," +
                    "Łowca:0.05," +
                    "Ochotnik:0," +
                    "Paź:0," +
                    "Rzecznik rodu:0.05," +
                    "Sługa:0," +
                    "Strażnik pól:0," +
                    "Szlachcic:0.005," +
                    "Strażnik więzienny:0,"+
                    "Tarczownik:0,"+
                    "Zabójca trolli:0,"+
                    "Żołnierz:0"
    ),
    DWARF("Krasnolud",
            "Mężczyzna:0.8," +
                    "Agility:-10," +
                    "Toughness:10," +
                    "WeaponSkill:10," +
                    "Fellowship:-10," +
                    "Wounds:1," +
                    "Movement:-1," +
                    "Skills:{}," +
                    "Talents:{}," +
                    "averageWeight:65," +
                    "averageHeight:145," +
                    "adultAge:20," +
                    "elderAge:120," +
                    "oldAge:190," +
                    "maxAge:320," +
                    "Żal:0.03," +
                    "Nienawiść:0.03," +
                    "Niechęć:0.03," +
                    "Gniew:0.03," +
                    "Zdenerwowanie:0.03," +
                    "Wrogość:0.03," +
                    "Tęsknota:0.03," +
                    "Zniewaga:0.03," +
                    "Uraza:0.03," +
                    "Złość:0.03," +
                    "Chłop:0," +
                    "Ciura obozowa:0," +
                    "Goniec:0.08," +
                    "Górnik:0.1," +
                    "Guślarz:0," +
                    "Paź:0," +
                    "Rzecznik rodu:0," +
                    "Sługa:0," +
                    "Strażnik pól:0,"+
                    "Szlachcic:0.005,"+
                    "Tarczownik:0.03,"+
                    "Uczeń czarodzieja:0,"+
                    "Zabójca trolli:0.05"
    ),
    HALFLING("Niziołek",
            "Agility:10," +
                    "BallisticSkill:10," +
                    "WeaponSkill:-10," +
                    "Strength:-10," +
                    "Toughness:-10," +
                    "Fellowship:10," +
                    "Movement:0," +
                    "Wounds:-2," +
                    "Skills:{}," +
                    "Talents:{}," +
                    "averageWeight:50," +
                    "averageHeight:115," +
                    "adultAge:20," +
                    "elderAge:60," +
                    "oldAge:95," +
                    "maxAge:130," +
                    "Entuzjazm:0.05," +
                    "Ekscytacja:0.05," +
                    "Dobroć:0.05," +
                    "Rozluźnienie:0.05," +
                    "Zapał:0.05," +
                    "Goniec:0," +
                    "Guślarz:0," +
                    "Rzecznik rodu:0," +
                    "Strażnik pól:0.07,"+
                    "Tarczownik:0,"+
                    "Uczeń czarodzieja:0,"+
                    "Zabójca trolli:0"
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
