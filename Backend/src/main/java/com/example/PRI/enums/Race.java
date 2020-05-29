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
                    "Uczeń czarodzieja: 0.003," +
                    "Zabójca trolli:0," +
                    "Leśny duch:0,"+
                    "Spracowane dłonie:0.005,"+
                    "Fanatyk:0.01"
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
                    "Strażnik więzienny:0," +
                    "Tarczownik:0," +
                    "Zabójca trolli:0," +
                    "Żołnierz:0," +
                    "Leśny duch:0.05,"+
                    "Zabójca olbrzymów:0,"+
                    "Zabójca demonów:0,"+
                    "Bokobrody:0," +
                    "Broda:0," +
                    "Broda po pas:0," +
                    "Kilkudniowy zarost:0," +
                    "Krótka broda:0," +
                    "Obfite bokobrody:0," +
                    "Pejsy:0,"+
                    "Skołtuniona broda:0,"+
                    "Dziewiczy wąs:0,"+
                    "Zwykłe wąsy:0,"+
                    "Sumiaste wąsiska:0,"+
                    "Wąsy zaplecione w warkocze:0,"+
                    "Delikatne dłonie:0.02,"+
                    "Flegmatyczna mowa:0.04,"+
                    "Melodyjny głos:0.02,"+
                    "Skośne oczy:0.005,"+
                    "Atrakcyjna twarz:0.02,"+
                    "Garbus:0,"+
                    "Smukły:0.08,"+
                    "Włochate ręce:0,"+
                    "Elegancki ubiór:0.04,"+
                    "Urodziwy:0.02,"+
                    "Arogancki:0.02,"+
                    "Cierpliwy:0.02,"+
                    "Czarujący:0.02,"+
                    "Dobrze wychowany:0.01,"+
                    "Dumny:0.01,"+
                    "Niewrażliwy:0.02,"+
                    "Niewzruszony:0.02,"+
                    "Obojętny:0.02,"+
                    "Opanowany:0.02,"+
                    "Tajemniczy:0.02,"+
                    "Władczy:0.01,"+
                    "Zarozumiały:0.02"
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
                    "Strażnik pól:0," +
                    "Szlachcic:0.005," +
                    "Tarczownik:0.03," +
                    "Uczeń czarodzieja:0," +
                    "Zabójca trolli:0.05," +
                    "Leśny duch:0," +
                    "Zabójca olbrzymów:0.05," +
                    "Zabójca demonów:0.05," +
                    "Broda:0.9," +
                    "Broda po pas:0.5,"+
                    "Skołtuniona broda:0.3,"+
                    "Spleciona broda:0.4,"+
                    "Dłonie jak bochny chleba:0.03,"+
                    "Charczący głos:0.02,"+
                    "Mrukliwy głos:0.02,"+
                    "Łysy:0.02,"+
                    "Kwadratowa szczęka:0.01,"+
                    "Kwadratowa twarz:0.01,"+
                    "Dziewiczy wąs:0,"+
                    "Zwykłe wąsy:0.2" +
                    "Wąsy zaplecionw w warkocze:0.2,"+
                    "Sumiaste wąsiska:0.05,"+
                    "Częste bekanie:0.04,"+
                    "Piwny brzuch:0.1,"+
                    "Chamski:0.05,"+
                    "Chciwy:0.05,"+
                    "Drażliwy:0.02,"+
                    "Dumny:0.02,"+
                    "Honorowy:0.02,"+
                    "Małomówny:0.02,"+
                    "Marudny:0.01,"+
                    "Mściwy:0.02,"+
                    "Samotnik:0.02,"+
                    "Sknera:0.02,"+
                    "Uparty:0.02,"+
                    "Wulgarny:0.01,"+
                    "Zawistny:0.01,"+
                    "Wojowniczy:0.01"

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
                    "Strażnik pól:0.07," +
                    "Tarczownik:0," +
                    "Uczeń czarodzieja:0," +
                    "Zabójca trolli:0," +
                    "Leśny duch:0," +
                    "Zabójca olbrzymów:0," +
                    "Zabójca demonów:0,"+
                    "Bokobrody:0," +
                    "Broda:0," +
                    "Broda po pas:0," +
                    "Kilkudniowy zarost:0," +
                    "Krótka broda:0," +
                    "Obfite bokobrody:0," +
                    "Pejsy:0,"+
                    "Skołtuniona broda:0,"+
                    "Dziewiczy wąs:0,"+
                    "Zwykłe wąsy:0,"+
                    "Sumiaste wąsiska:0,"+
                    "Wąsy zaplecione w warkocze:0,"+
                    "Dziwny chichot:0.02,"+
                    "Jąkała:0.01,"+
                    "Mówi bardzo szybko:0.02,"+
                    "Wesołe oczy:0.05,"+
                    "Włosy w nieładzie:0.02,"+
                    "Włochate ręce:0,"+
                    "Cuchnące stopy:0.02,"+
                    "Ciągle wiercenie się:0.02,"+
                    "Dziecinny wygląd:0.15,"+
                    "Nieustanne dłubanie w nosie:0.04,"+
                    "Bezczelny:0.02,"+
                    "Bezinteresowny:0.02,"+
                    "Beztroski:0.02,"+
                    "Bohaterski:0.01,"+
                    "Brawurowy:0.01,"+
                    "Ciekawski:0.02,"+
                    "Dowcipny:0.01,"+
                    "Irytujący:0.02,"+
                    "Lekkoduch:0.02,"+
                    "Niecierpliwy:0.01,"+
                    "Pomysłowy:0.01,"+
                    "Przyjacielski:0.01,"+
                    "Szczęściarz:0.01,"+
                    "Śmieszny:0.02,"+
                    "Wścibski:0.01,"+
                    "Zabawny:0.02,"+
                    "Zuchwały:0.01,"+
                    "Łakomy:0.02"


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
