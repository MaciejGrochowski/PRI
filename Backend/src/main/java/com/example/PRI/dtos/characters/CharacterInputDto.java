package com.example.PRI.dtos.characters;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CharacterInputDto implements Serializable {

    @NotBlank(message = "Podaj miejsce urodzenia swojej postaci.")
    String birthPlace;

    @Pattern(regexp ="Elf|Krasnolud|Człowiek|Niziołek", message = "Podaj poprawną rase.")
    String race;

    @NotBlank(message = "Podaj kolor oczu swojej postaci.")
    String eyeColor;

    @NotBlank(message = "Podaj kolor włosów swojej postaci.")
    String hairColor;

    @Pattern(regexp = "\\d\\d?", message = "Podaj poprawny dzień urodzin.")
    String dayOfBirth;

    @Pattern(regexp ="Nachexen|Jahrdrung|Pflugzeit|Sigmarzeit|Sommerzeit|Vorgeheim|Nachgeheim|Erntezeit|Brauzeit|Kaldezeit|Ulriczeit|Vorhexen",
            message = "Podaj poprawny miesiąc.")
    String monthOfBirth;

    @Pattern(regexp = "\\d\\d?\\d?\\d?", message = "Podaj poprawny rok.")
    String yearOfBirth;


    String dominatingEmotions;

    @Pattern(regexp ="Kobieta||Mężczyzna", message = "Podaj poprawną płeć.")
    String sex;

    @NotBlank(message = "Podaj religie swojej postaci.")
    String religion;

    @Pattern(regexp = "\\d\\d\\d?", message = "Podaj poprawną wagę.")
    String weight;

    @Pattern(regexp = "\\d\\d\\d?", message = "Podaj poprawny wzrost.")
    String height;


    String surname;

    @Pattern(regexp = "[a-zA-Z]*", message = "Podaj poprawne imię.")
    String name;

    String prediction;
    String previousCareers;

    @NotBlank(message = "Podaj profesje swojej postaci.")
    String currentCareer;

    String skills;
    String talents;

    @Pattern(regexp = "[1-9][0-9]?", message = "Wartość obecnej walki wręcz może wynosić od 1 do 99.")
    String endWeaponSkills;

    @Pattern(regexp = "[1-9][0-9]?", message = "Wartość obecnych umiejętności strzeleckich może wynosić od 1 do 99.")
    String endBallisticSkills;

    @Pattern(regexp = "[1-9][0-9]?", message = "Wartość obecnej krzepy wręcz może wynosić od 1 do 99.")
    String endStrength;

    @Pattern(regexp = "[1-9][0-9]?", message = "Wartość obecnej odporności może wynosić od 1 do 99.")
    String endToughness;

    @Pattern(regexp = "[1-9][0-9]?", message = "Wartość obecnej zręczności może wynosić od 1 do 99.")
    String endAgility;

    @Pattern(regexp = "[1-9][0-9]??", message = "Wartość obecnej inteligencji może wynosić od 1 do 99.")
    String endIntelligence;

    @Pattern(regexp = "[1-9][0-9]?", message = "Wartość obecnej siły woli może wynosić od 1 do 99.")
    String endWillPower;

    @Pattern(regexp = "[1-9][0-9]?", message = "Wartość obecnej ogłady może wynosić od 1 do 99.")
    String endFellowship;

    @Pattern(regexp = "[1-9][0-9]?", message = "Wartość obecnego ataku może wynosić od 1 do 99.")
    String endAttacks;

    @Pattern(regexp = "[1-9][0-9]?", message = "Wartość obecnej żywotności może wynosić od 1 do 99.")
    String endWounds; // żywotność

    @Pattern(regexp = "0|[1-9][0-9]?", message = "Wartość obecnej magii może wynosić od 0 do 99.")
    String endMagic; // magia

    @Pattern(regexp = "[1-9][0-9]?", message = "Wartość obecnej szybkości może wynosić od 1 do 99.")
    String endMovement; // szybkość



    @Pattern(regexp = "[1-9][0-9]?", message = "Wartość bazowej walki wręcz może wynosić od 1 do 99.")
    String baseWeaponSkills;

    @Pattern(regexp = "[1-9][0-9]?", message = "Wartość bazowych umiejętności strzeleckich może wynosić od 1 do 99.")
    String baseBallisticSkills;

    @Pattern(regexp = "[1-9][0-9]?", message = "Wartość bazowej krzepy wręcz może wynosić od 1 do 99.")
    String baseStrength;

    @Pattern(regexp = "[1-9][0-9]?", message = "Wartość bazowej odporności może wynosić od 1 do 99.")
    String baseToughness;

    @Pattern(regexp = "[1-9][0-9]?", message = "Wartość bazowej zręczności może wynosić od 1 do 99.")
    String baseAgility;

    @Pattern(regexp = "[1-9][0-9]??", message = "Wartość bazowej inteligencji może wynosić od 1 do 99.")
    String baseIntelligence;

    @Pattern(regexp = "[1-9][0-9]?", message = "Wartość bazowej siły woli może wynosić od 1 do 99.")
    String baseWillPower;

    @Pattern(regexp = "[1-9][0-9]?", message = "Wartość bazowej ogłady może wynosić od 1 do 99.")
    String baseFellowship;

    @Pattern(regexp = "[1-9][0-9]?", message = "Wartość bazowego ataku może wynosić od 1 do 99.")
    String baseAttacks;

    @Pattern(regexp = "[1-9][0-9]?", message = "Wartość bazowej żywotności może wynosić od 1 do 99.")
    String baseWounds; // żywotność

    @Pattern(regexp = "0|[1-9][0-9]?", message = "Wartość bazowej magii może wynosić od 0 do 99.")
    String baseMagic; // magia

    @Pattern(regexp = "[1-9][0-9]?", message = "Wartość bazowej szybkości może wynosić od 1 do 99.")
    String baseMovement; // szybkość


    String personality;
    String apperance;


    @NotBlank(message = "Podaj miejsce pobytu swojej postaci.")
    String livePlace;

//    @ManyToOne
//    @JoinColumn(name="created_by", referencedColumnName="username")
//    User createdBy; //User tworzacy
//    Date createdDate;
//
}
