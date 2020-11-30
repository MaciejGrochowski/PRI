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

    @NotBlank(message = "NO_BIRTH_PLACE")
    String birthPlace;

    @NotBlank (message = "NO_RACE")
    @Pattern(regexp ="Elf|Krasnolud|Człowiek|Niziołek", message = "RACE_NOT_REGEXP")
    String race;

    @NotBlank(message = "NO_EYE_COLOR")
    String eyeColor;

    @NotBlank(message = "NO_HAIR_COLOR")
    String hairColor;

    @NotBlank (message = "NO_DAY_OF_BIRTH")
    @Pattern(regexp = "\\d\\d?", message = "DAY_OF_BIRTH_NOT_REGEXP")
    String dayOfBirth;

    @NotBlank (message = "NO_MONTH_OF_BIRTH")
    @Pattern(regexp ="Nachexen|Jahrdrung|Pflugzeit|Sigmarzeit|Sommerzeit|Vorgeheim|Nachgeheim|Erntezeit|Brauzeit|Kaldezeit|Ulriczeit|Vorhexen",
            message = "MONTH_OF_BIRTH_NOT_REGEXP")
    String monthOfBirth;

    @NotBlank (message = "NO_YEAR_OF_BIRTH")
    @Pattern(regexp = "\\d\\d?\\d?\\d?", message = "YEAR_OF_BIRTH_NOT_REGEXP")
    String yearOfBirth;


    String dominatingEmotions;

    @NotBlank (message = "NO_SEX")
    @Pattern(regexp ="Kobieta||Mężczyzna", message = "SEX_NOT_REGEXP")
    String sex;

    @NotBlank(message = "NO_RELIGION")
    String religion;

    @NotBlank (message = "NO_WEIGHT")
    @Pattern(regexp = "\\d\\d\\d?", message = "WEIGHT_NOT_REGEXP")
    String weight;

    @NotBlank (message = "NO_HEIGHT")
    @Pattern(regexp = "\\d\\d\\d?", message = "HEIGHT_NOT_REGEXP")
    String height;

    String surname;

    @NotBlank (message = "NO_NAME")
    @Pattern(regexp = "[a-zA-Z]*", message = "NAME_NOT_REGEXP")
    String name;

    String prediction;
    String previousCareers;

    @NotBlank(message = "NO_CURRENT_CAREER")
    String currentCareer;

    @NotBlank (message = "NO_SKILLS")
    String skills;

    @NotBlank (message = "NO_TALENTS")
    String talents;

    @NotBlank (message = "NO_WEAPON_SKILLS")
    @Pattern(regexp = "[1-9][0-9]?", message = "WEAPON_SKILLS_NOT_REGEXP")
    String endWeaponSkills;

    @NotBlank (message = "NO_BALLISTIC_SKILLS")
    @Pattern(regexp = "[1-9][0-9]?", message = "BALLISTIC_SKILLS_NOT_REGEXP")
    String endBallisticSkills;

    @NotBlank (message = "NO_STRENGTH")
    @Pattern(regexp = "[1-9][0-9]?", message = "STRENGTH_NOT_REGEXP")
    String endStrength;

    @NotBlank (message = "NO_TOUGHNESS")
    @Pattern(regexp = "[1-9][0-9]?", message = "TOUGHNESS_NOT_REGEXP")
    String endToughness;

    @NotBlank (message = "NO_AGILITY")
    @Pattern(regexp = "[1-9][0-9]?", message = "AGILITY_NOT_REGEXP")
    String endAgility;

    @NotBlank (message = "NO_INTELLIGENCE")
    @Pattern(regexp = "[1-9][0-9]??", message = "INTELLIGENCE_NOT_REGEXP")
    String endIntelligence;

    @NotBlank (message = "NO_WILLPOWER")
    @Pattern(regexp = "[1-9][0-9]?", message = "WILLPOWER_NOT_REGEXP")
    String endWillPower;

    @NotBlank (message = "NO_FELLOWSHIP")
    @Pattern(regexp = "[1-9][0-9]?", message = "FELLOWSHIP_NOT_REGEXP")
    String endFellowship;

    @NotBlank (message = "NO_ATTACKS")
    @Pattern(regexp = "[1-9][0-9]?", message = "ATTACKS_NOT_REGEXP")
    String endAttacks;

    @NotBlank (message = "NO_WOUNDS")
    @Pattern(regexp = "[1-9][0-9]?", message = "WOUNDS_NOT_REGEXP")
    String endWounds; // żywotność

    @NotBlank (message = "NO_MAGIC")
    @Pattern(regexp = "0|[1-9][0-9]?", message = "MAGIC_NOT_REGEXP")
    String endMagic; // magia

    @NotBlank (message = "NO_MOVEMENT")
    @Pattern(regexp = "[1-9][0-9]?", message = "MOVEMENT_NOT_REGEXP")
    String endMovement; // szybkość


    @NotBlank (message = "NO_WEAPON_SKILLS")
    @Pattern(regexp = "[1-9][0-9]?", message = "WEAPON_SKILLS_NOT_REGEXP")
    String baseWeaponSkills;

    @NotBlank (message = "NO_BALLISTIC_SKILLS")
    @Pattern(regexp = "[1-9][0-9]?", message = "BALLISTIC_SKILLS_NOT_REGEXP")
    String baseBallisticSkills;

    @NotBlank (message = "NO_STRENGTH")
    @Pattern(regexp = "[1-9][0-9]?", message = "STRENGTH_NOT_REGEXP")
    String baseStrength;

    @NotBlank (message = "NO_TOUGHNESS")
    @Pattern(regexp = "[1-9][0-9]?", message = "TOUGHNESS_NOT_REGEXP")
    String baseToughness;

    @NotBlank (message = "NO_AGILITY")
    @Pattern(regexp = "[1-9][0-9]?", message = "AGILITY_NOT_REGEXP")
    String baseAgility;

    @NotBlank (message = "NO_INTELLIGENCE")
    @Pattern(regexp = "[1-9][0-9]??", message = "INTELLIGENCE_NOT_REGEXP")
    String baseIntelligence;

    @NotBlank (message = "NO_WILLPOWER")
    @Pattern(regexp = "[1-9][0-9]?", message = "WILLPOWER_NOT_REGEXP")
    String baseWillPower;

    @NotBlank (message = "NO_FELLOWSHIP")
    @Pattern(regexp = "[1-9][0-9]?", message = "FELLOWSHIP_NOT_REGEXP")
    String baseFellowship;

    @NotBlank (message = "NO_ATTACKS")
    @Pattern(regexp = "[1-9][0-9]?", message = "ATTACKS_NOT_REGEXP")
    String baseAttacks;

    @NotBlank (message = "NO_WOUNDS")
    @Pattern(regexp = "[1-9][0-9]?", message = "WOUNDS_NOT_REGEXP")
    String baseWounds; // żywotność

    @NotBlank (message = "NO_MAGIC")
    @Pattern(regexp = "0|[1-9][0-9]?", message = "MAGIC_NOT_REGEXP")
    String baseMagic; // magia

    @NotBlank (message = "NO_MOVEMENT")
    @Pattern(regexp = "[1-9][0-9]?", message = "MOVEMENT_NOT_REGEXP")
    String baseMovement; // szybkość


    String personality;
    String apperance;


    @NotBlank(message = "NO_LIVE_PLACE")
    String livePlace;

//    @ManyToOne
//    @JoinColumn(name="created_by", referencedColumnName="username")
//    User createdBy; //User tworzacy
//    Date createdDate;
//
}
