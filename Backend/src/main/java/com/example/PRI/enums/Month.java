package com.example.PRI.enums;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.Optional;

@AllArgsConstructor
public enum Month {
    NACHEXEN("Nachexen"), // marzec
    JAHRDRUNG("Jahrdrung"), // kwiecień
    PFLUGZEIT("Pflugzeit"), // maj
    SIGMARZEIT("Sigmarzeit"), // czerwiec
    SOMMERZEIT("Sommerzeit"), // lipiec
    VORGEHEIM("Vorgeheim"), // sierpień
    NACHGEHEIM("Nachgeheim"), // wrzesień
    ERNTEZEIT("Erntezeit"), // październik
    BRAUZEIT("Brauzeit"), // listopad
    KALDEZEIT("Kaldezeit"), // grudzień
    ULRICZEIT("Ulriczeit"), // styczeń
    VORHEXEN ("Vorhexen");//luty

    String monthName;

    public static Month findByMonthName(String monthName){
        Optional<Month> output = Arrays.asList(Month.values()).stream().filter(r -> r.monthName.equals(monthName)).findFirst();
        return output.orElse(null);
    }

    public String getMonthName(){
        return this.monthName;
    }
    }

