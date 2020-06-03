package com.example.PRI.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Sex {

    FEMALE("Kobieta", "averageWeight:-5," +
            "averageHeight:-5," +
            "Tarczownik:0," +
            "Zabójca trolli:0,"+
            "Bokobrody:0," +
            "Broda:0," +
            "Broda po pas:0," +
            "Kilkudniowy zarost:0," +
            "Krótka broda:0," +
            "Obfite bokobrody:0," +
            "Pejsy:0,"+
            "Skołtuniona broda:0,"+
            "Krótka bródka:0," +
            "Długie paznokcie:0.02,"+
            "Mówi bardzo szybko:0.01,"+
            "Piskliwy głos:0.01,"+
            "Ponętna figura:0.01,"+
            "Dziewiczy wąs:0,"+
            "Zwykłe wąsy:0,"+
            "Sumiaste wąsiska:0,"+
            "Wąsy zaplecione w warkocze:0,"+
            "Makijaż:0.001,"+
            "Przystojny:0"
            ),
    MALE("Mężczyzna", "averageWeight:5," +
            "averageHeight:5," +
            "Akolita:0.001,"+
            "Łysy:0.01,"+
            "Łysina na czubku głowy:0.01");

    String name;
    String properties;

    public String getProperties() {
        return properties;
    }

    public String getName() {
        return name;
    }
}
