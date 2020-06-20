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

    @Pattern(regexp = "[a-zA-z]*", message = "Podaj tylko jedno imię składające się tylko z liter.")
    String name;

    String prediction;
    String previousCareers;

    @NotBlank(message = "Podaj profesje swojej postaci.")
    String currentCareer;

    String skills;
    String talents;

    @Pattern(regexp = "[1-9][0-9]?", message = "Wartość obecnej walki wręcz może wynosić od 1 do 99.")
    String endWeaponSkills;

    @NotBlank(message = "Podaj wartość obecnych umiejętności strzeleckich.")
    @Pattern(regexp = "[0-9]*", message = "Wartość obecnych umiejętności strzeleckich urodzenia może składać się tylko z liczb.")
    String endBallisticSkills;

    @NotBlank(message = "Podaj wartość obecnej krzepy.")
    @Pattern(regexp = "[0-9]*", message = "Wartość obecnej krzepy może składać się tylko z liczb.")
    String endStrength;

    @NotBlank(message = "Podaj wartość obecnej odporności.")
    @Pattern(regexp = "[0-9]*", message = "Wartość obecnej odporności może składać się tylko z liczb.")
    String endToughness;

    @NotBlank(message = "Podaj wartość obecnej zręczności.")
    @Pattern(regexp = "[0-9]*", message = "Wartość obecnej zręczności może składać się tylko z liczb.")
    String endAgility;

    @NotBlank(message = "Podaj wartość obecnej inteligencji.")
    @Pattern(regexp = "[0-9]*?", message = "Wartość obecnej inteligencji może składać się tylko z liczb.")
    String endIntelligence;

    @NotBlank(message = "Podaj wartość obecnej siły woli.")
    @Pattern(regexp = "[0-9]*", message = "Wartość obecnej siły woli może składać się tylko z liczb.")
    String endWillPower;

    @NotBlank(message = "Podaj wartość obecnej ogłady.")
    @Pattern(regexp = "[0-9]*", message = "Wartość obecnej ogłady może składać się tylko z liczb.")
    String endFellowship;

    @NotBlank(message = "Podaj wartość obecnego ataku.")
    @Pattern(regexp = "[0-9]*", message = "Wartość obecnego ataku może składać się tylko z liczb.")
    String endAttacks;

    @NotBlank(message = "Podaj wartość obecnej żywotności.")
    @Pattern(regexp = "[0-9]*", message = "Wartość obecnej żywotności może składać się tylko z liczb.")
    String endWounds; // żywotność

    @NotBlank(message = "Podaj wartość obecnej magii.")
    @Pattern(regexp = "[0-9]*", message = "Wartość obecnej magii może składać się tylko z liczb.")
    String endMagic; // magia

    @NotBlank(message = "Podaj wartość obecnej szybkości.")
    @Pattern(regexp = "[0-9]*", message = "Wartość obecnej szybkości może składać się tylko z liczb.")
    String endMovement; // szybkość

    String baseWeaponSkills;
    String baseBallisticSkills;
    String baseStrength;
    String baseToughness;
    String baseAgility;
    String baseIntelligence;
    String baseWillPower;
    String baseFellowship;
    String baseAttacks;
    String baseWounds; // żywotność
    String baseMagic; // magia
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
