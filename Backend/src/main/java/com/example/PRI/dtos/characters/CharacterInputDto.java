package com.example.PRI.dtos.characters;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CharacterInputDto implements Serializable {

    String birthPlace;
    String race;
    String eyeColor;
    String hairColor;
    String dayOfBirth;
    String monthOfBirth;
    String yearOfBirth;
    String dominatingEmotions;
    String sex;
    String religion;
    String weight;
    String height;
    String surname;
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
