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

    @NotBlank (message = "Podaj poprawną rase.")
    @Pattern(regexp ="Elf|Krasnolud|Człowiek|Niziołek", message = "Podaj poprawną rase.")
    String race;

    @NotBlank(message = "Podaj kolor oczu swojej postaci.")
    String eyeColor;

    @NotBlank(message = "Podaj kolor włosów swojej postaci.")
    String hairColor;

    @NotBlank (message = "Podaj poprawny dzień urodzin.")
    @Pattern(regexp = "\\d\\d?", message = "Podaj poprawny dzień urodzin.")
    String dayOfBirth;

    @NotBlank (message = "Podaj poprawny miesiąc urodzin.")
    @Pattern(regexp ="Nachexen|Jahrdrung|Pflugzeit|Sigmarzeit|Sommerzeit|Vorgeheim|Nachgeheim|Erntezeit|Brauzeit|Kaldezeit|Ulriczeit|Vorhexen",
            message = "Podaj poprawny miesiąc.")
    String monthOfBirth;

    @NotBlank (message = "Podaj poprawny rok urodzin.")
    @Pattern(regexp = "\\d\\d?\\d?\\d?", message = "Podaj poprawny rok urodzin.")
    String yearOfBirth;


    String dominatingEmotions;

    @NotBlank (message = "Podaj poprawną płeć.")
    @Pattern(regexp ="Kobieta||Mężczyzna", message = "Podaj poprawną płeć.")
    String sex;

    @NotBlank(message = "Podaj religie swojej postaci.")
    String religion;

    @NotBlank (message = "Podaj poprawną wagę.")
    @Pattern(regexp = "\\d\\d\\d?", message = "Podaj poprawną wagę.")
    String weight;

    @NotBlank (message = "Podaj poprawny wzrost.")
    @Pattern(regexp = "\\d\\d\\d?", message = "Podaj poprawny wzrost.")
    String height;

    String surname;

    @NotBlank (message = "Podaj poprawne imię.")
    @Pattern(regexp = "[a-zA-Z]*", message = "Podaj poprawne imię.")
    String name;

    String prediction;
    String previousCareers;

    @NotBlank(message = "Podaj profesje swojej postaci.")
    String currentCareer;

    @NotBlank (message = "Wybierz umiejętności swojej postaci.")
    String skills;

    @NotBlank (message = "Wybierz zdolności swojej postaci.")
    String talents;

    @NotBlank (message = "Wartość obecnej walki wręcz może wynosić od 1 do 99.")
    @Pattern(regexp = "[1-9][0-9]?", message = "Wartość obecnej walki wręcz może wynosić od 1 do 99.")
    String endWeaponSkills;

    @NotBlank (message = "Wartość obecnych umiejętności strzeleckich może wynosić od 1 do 99.")
    @Pattern(regexp = "[1-9][0-9]?", message = "Wartość obecnych umiejętności strzeleckich może wynosić od 1 do 99.")
    String endBallisticSkills;

    @NotBlank (message = "Wartość obecnej krzepy może wynosić od 1 do 99.")
    @Pattern(regexp = "[1-9][0-9]?", message = "Wartość obecnej krzepy może wynosić od 1 do 99.")
    String endStrength;

    @NotBlank (message = "Wartość obecnej odporności może wynosić od 1 do 99.")
    @Pattern(regexp = "[1-9][0-9]?", message = "Wartość obecnej odporności może wynosić od 1 do 99.")
    String endToughness;

    @NotBlank (message = "Wartość obecnej zręczności może wynosić od 1 do 99.")
    @Pattern(regexp = "[1-9][0-9]?", message = "Wartość obecnej zręczności może wynosić od 1 do 99.")
    String endAgility;

    @NotBlank (message = "Wartość obecnej inteligencji może wynosić od 1 do 99.")
    @Pattern(regexp = "[1-9][0-9]??", message = "Wartość obecnej inteligencji może wynosić od 1 do 99.")
    String endIntelligence;

    @NotBlank (message = "Wartość obecnej siły woli może wynosić od 1 do 99.")
    @Pattern(regexp = "[1-9][0-9]?", message = "Wartość obecnej siły woli może wynosić od 1 do 99.")
    String endWillPower;

    @NotBlank (message = "Wartość obecnej ogłady może wynosić od 1 do 99.")
    @Pattern(regexp = "[1-9][0-9]?", message = "Wartość obecnej ogłady może wynosić od 1 do 99.")
    String endFellowship;

    @NotBlank (message = "Wartość obecnego ataku może wynosić od 1 do 99.")
    @Pattern(regexp = "[1-9][0-9]?", message = "Wartość obecnego ataku może wynosić od 1 do 99.")
    String endAttacks;

    @NotBlank (message = "Wartość obecnej żywotności może wynosić od 1 do 99.")
    @Pattern(regexp = "[1-9][0-9]?", message = "Wartość obecnej żywotności może wynosić od 1 do 99.")
    String endWounds; // żywotność

    @NotBlank (message = "Wartość obecnej magii może wynosić od 0 do 99.")
    @Pattern(regexp = "0|[1-9][0-9]?", message = "Wartość obecnej magii może wynosić od 0 do 99.")
    String endMagic; // magia

    @NotBlank (message = "Wartość obecnej szybkości może wynosić od 1 do 99.")
    @Pattern(regexp = "[1-9][0-9]?", message = "Wartość obecnej szybkości może wynosić od 1 do 99.")
    String endMovement; // szybkość


    @NotBlank (message = "Wartość bazowej walki wręcz może wynosić od 1 do 99.")
    @Pattern(regexp = "[1-9][0-9]?", message = "Wartość bazowej walki wręcz może wynosić od 1 do 99.")
    String baseWeaponSkills;

    @NotBlank (message = "Wartość bazowych umiejętności strzeleckich może wynosić od 1 do 99.")
    @Pattern(regexp = "[1-9][0-9]?", message = "Wartość bazowych umiejętności strzeleckich może wynosić od 1 do 99.")
    String baseBallisticSkills;

    @NotBlank (message = "Wartość bazowej krzepy może wynosić od 1 do 99.")
    @Pattern(regexp = "[1-9][0-9]?", message = "Wartość bazowej krzepy może wynosić od 1 do 99.")
    String baseStrength;

    @NotBlank (message = "Wartość bazowej odporności może wynosić od 1 do 99.")
    @Pattern(regexp = "[1-9][0-9]?", message = "Wartość bazowej odporności może wynosić od 1 do 99.")
    String baseToughness;

    @NotBlank (message = "Wartość bazowej zręczności może wynosić od 1 do 99.")
    @Pattern(regexp = "[1-9][0-9]?", message = "Wartość bazowej zręczności może wynosić od 1 do 99.")
    String baseAgility;

    @NotBlank (message = "Wartość bazowej inteligencji może wynosić od 1 do 99.")
    @Pattern(regexp = "[1-9][0-9]??", message = "Wartość bazowej inteligencji może wynosić od 1 do 99.")
    String baseIntelligence;

    @NotBlank (message = "Wartość bazowej siły woli może wynosić od 1 do 99.")
    @Pattern(regexp = "[1-9][0-9]?", message = "Wartość bazowej siły woli może wynosić od 1 do 99.")
    String baseWillPower;

    @NotBlank (message = "Wartość bazowej ogłady może wynosić od 1 do 99.")
    @Pattern(regexp = "[1-9][0-9]?", message = "Wartość bazowej ogłady może wynosić od 1 do 99.")
    String baseFellowship;

    @NotBlank (message = "Wartość bazowego ataku może wynosić od 1 do 99.")
    @Pattern(regexp = "[1-9][0-9]?", message = "Wartość bazowego ataku może wynosić od 1 do 99.")
    String baseAttacks;

    @NotBlank (message = "Wartość bazowej żywotności może wynosić od 1 do 99.")
    @Pattern(regexp = "[1-9][0-9]?", message = "Wartość bazowej żywotności może wynosić od 1 do 99.")
    String baseWounds; // żywotność

    @NotBlank (message = "Wartość bazowej magii może wynosić od 0 do 99.")
    @Pattern(regexp = "0|[1-9][0-9]?", message = "Wartość bazowej magii może wynosić od 0 do 99.")
    String baseMagic; // magia

    @NotBlank (message = "Wartość bazowej szybkości może wynosić od 1 do 99.")
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
