package com.example.PRI.dtos.characters;

import com.example.PRI.entities.character.*;
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

    String race;

    String eyeColor;

    String hairColor;

    String dayOfBirth;
    String monthOfBirth;
    String yearOfBird;

    String starSign;

    String dominatingEmotions;

    String sex;

    String religion;

    Integer weight;
    Integer height;

    String surname;

    String name;

    String prediction;

    String previousCareers;

    String currentCareer;

    @ManyToMany
    List<Skill> skills; //ToDo use SkillOutputDto

    @ManyToMany
    List<Talent> talents; //ToDo use TalentOutputDto

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