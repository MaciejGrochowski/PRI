package com.example.PRI.enums;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public enum StarSign {

    BEBNIARZ("Bębniarz", "Lupios", "Znak Zabawy i Radości","8 Nachgeheim","27 Nachgeheim"),
    DUDY("Dudy", "Sangist", "Znak Oszustwa", "28 Nachgeheim", "15 Erntezeit"),
    VOBIST_ULOTNY("Vobist Ulotny","Vobist","Znak Ciemności i Niepewności","16 Erntezeit","1 Brauzeit"),
    ROZBITY_WOZ("Rozbity Wóz","Kharnos","Znak Dumy","2 Brauzeit","21 Brauzeit"),
    TLUSTY_KOZIOL("Tłusty Kozioł","Talios","Znak Ukrytej Namiętności","22 Brauzeit","8 Kaldezeit"),
    KOCIOL_RHYI("Kocioł Rhyi","Rionyes","Znak Łaski, Śmierci i Tworzenia","9 Kaldezeit","28 Kaldezeit"),
    ZLOTY_KOGUT("Złoty Kogut","Kakeros","Znak Kupców i Bogactwa","29 Kaldezeit","15 Ulriczeit"),
    LANCET("Lancet","Alyoi","Znak Nauki i Talentu","16 Ulriczeit","1 Vorhexen"),
    GWIAZDA_UROKU("Gwiazda Uroku", "Solkios" , "Znak Magii", "2 Vorhexen", "21 Vorhexen"),
    WYMUND_PUSTELNIK("Wymund Pustelnik","Wymenos","Znak Wytrzymałości","22 Vorhexen","7 Nachexen"),
    WIELKI_KRZYZ("Wielki Krzyż","Azurins","Znak Czystości","8 Nachexen","27 Nachexen"),
    SZNUR_LIMNERA("Sznur Limnera","Verros","Znak Dokładności","28 Nachexen","15 Jahrdrung"),
    WOL_GNUTHUS("Wół Gnuthus","Nuthios","Znak Wiernej Służby","16 Jahrdrung","1 Pflugzeit"),
    SMOK_DRAGOMAS("Smok Dragomas","Drakonos","Znak Odwagi","2 Pflugzeit","21 Pflugzeit"),
    GWIAZDA_WIECZORNA("Gwiazda Wieczorna","Tarotes","Znak Tajemnicy i Iluzji","22 Pflugzeit","8 Sigmarzeit"),
    PAS_GRUNGNIEGO("Pas Grungniego", "Gileon", "Znak Sprawności Wojennej","9 Sigmarzeit","28 Sigmarzeit"),
    MEDRZEC_MARMIT("Mędrzec Marmit", "Mammius","Znak Mądrości","29 Sigmarzeit","15 Sommerzeit"),
    GLUPIEC_MUMMIT("Głupiec Mummit", "Mammios", "Znak Instynktu", "16 Sommerzeit", "1 Vorgeheim"),
    DWA_BYKI("Dwa byki", "Hashoor", "Znak Płodności i Rzemiosła", "2 Vorgeheim","21 Vorgeheim"),
    TANCERKA("Tancerka","Adamnos","Znak Miłości i Pożądania","22 Vorgeheim","7 Nachgeheim");


    private String name;
    private String shortName;
    private String description;
    private String startDate;
    private String endDate;

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }

    public String getDescription() {
        return description;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public static StarSign findByName(String s) {
        for (StarSign sign : StarSign.values()){
            if (sign.name.equals(s) || sign.shortName.equals(s))
                return sign;
        }
        return null;
    }
}
