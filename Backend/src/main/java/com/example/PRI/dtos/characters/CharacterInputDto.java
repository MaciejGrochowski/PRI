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

    @NotBlank(message = "Podaj rase swojej postaci.")
    @Pattern(regexp ="Elf|Krasnolud|Człowiek|Niziołek", message = "Podaj poprawną rase.")
    String race;

    @NotBlank(message = "Podaj kolor oczu swojej postaci.")
    String eyeColor;

    @NotBlank(message = "Podaj kolor włosów swojej postaci.")
    String hairColor;

    @NotBlank(message = "Podaj dzień urodzin swojej postaci.")
    @Pattern(regexp = "\\d\\d?", message = "Dzień urodzin może składać się tylko z liczb.")
    @Min(value = 1,message = "Podana wartość dnia urodzin jest zbyt mała. Minimium to: 1")
    @Max(value = 34, message = "Podana wartość dnia urodzin jest zbyt duża. Maksimum to: 34")
    String dayOfBirth;

    @NotBlank(message = "Podaj miesiąc urodzin swojej postaci.")
    @Pattern(regexp ="Nachexen|Jahrdrung|Pflugzeit|Sigmarzeit|Sommerzeit|Vorgeheim|Nachgeheim|Erntezeit|Brauzeit|Kaldezeit|Ulriczeit|Vorhexen",
            message = "Podaj poprawny miesiąc.")
    String monthOfBirth;

    @NotBlank(message = "Podaj rok urodzin swojej postaci.")
    @Pattern(regexp = "\\d\\d?\\d?\\d?", message = "Rok urodzenia może składać się tylko z liczb.")
    @Min(value = 0,message = "Podana wartość roku urodzenia jest zbyt mała. Minimium to: 0")
    @Max(value = 3000, message = "Podana wartość roku urodzenia jest zbyt duża. Maksimum to: 3000")
    String yearOfBirth;
    String dominatingEmotions;

    @NotNull(message = "Podaj płeć swojej postaci.")
    @Pattern(regexp ="Kobieta||Mężczyzna", message = "Podaj poprawną płeć.")
    String sex;

    String religion;
    String weight;
    String height;
    String surname;

    @NotBlank(message = "Podaj imię swojej postaci.")
    String name;
    String prediction;
    String previousCareers;
    String currentCareer;
    String skills;
    String talents;

    String endWeaponSkills;
    String endBallisticSkills;
    String endStrength;
    String endToughness;
    String endAgility;
    String endIntelligence;
    String endWillPower;
    String endFellowship;
    String endAttacks;
    String endWounds; // żywotność
    String endMagic; // magia
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

    String livePlace;

//    @ManyToOne
//    @JoinColumn(name="created_by", referencedColumnName="username")
//    User createdBy; //User tworzacy
//    Date createdDate;
//
}
