package com.example.PRI.dtos.characters;

import com.example.PRI.entities.GeneralEntity;
import com.example.PRI.entities.ImperialDate;
import com.example.PRI.entities.Place;
import com.example.PRI.entities.User;
import com.example.PRI.entities.character.*;
import com.example.PRI.enums.Race;
import com.example.PRI.enums.Religion;
import com.example.PRI.enums.Sex;
import com.example.PRI.enums.StarSign;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToMany;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CharacterDetailsOutputDto {

    String birthPlace;

    Race race;

    String eyeColor;

    String hairColor;

    String dayOfBirth;
    String monthOfBirth;
    String yearOfBird;

    String starSign;

    String dominatingEmotions;

    Sex sex;

    Religion religion;

    int weight;
    int height;

    String surname;

    String name;

    String prediction;

    String previousCareers;

    String currentCareer;

    @ManyToMany
    List<Skill> skills;

    @ManyToMany
    List<Talent> talents;

    Integer endWeaponSkills;
    Integer endBallisticSkills;
    Integer endStrength;
    Integer endToughness;
    Integer endAgility;
    Integer endIntelligence;
    Integer endWillPower;
    Integer endFellowship;
    Integer endAttacks;
    Integer endWounds; // żywotność
    Integer endMagic; // magia
    Integer endMovement; // szybkość


    Integer baseWeaponSkills;
    Integer baseBallisticSkills;
    Integer baseStrength;
    Integer baseToughness;
    Integer baseAgility;
    Integer baseIntelligence;
    Integer baseWillPower;
    Integer baseFellowship;
    Integer baseAttacks;
    Integer baseWounds; // żywotność
    Integer baseMagic; // magia
    Integer baseMovement; // szybkość

    Integer careerWeaponSkills;
    Integer careerBallisticSkills;
    Integer careerStrength;
    Integer careerToughness;
    Integer careerAgility;
    Integer careerIntelligence;
    Integer careerWillPower;
    Integer careerFellowship;
    Integer careerAttacks;
    Integer careerWounds; // żywotność
    Integer careerMagic; // magia
    Integer careerMovement; // szybkość

    String personality;

    String apperance;

    String livePlace;

}