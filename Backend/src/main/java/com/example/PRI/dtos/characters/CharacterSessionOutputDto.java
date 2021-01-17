package com.example.PRI.dtos.characters;

import com.example.PRI.entities.character.Skill;
import com.example.PRI.entities.character.Talent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToMany;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CharacterSessionOutputDto {

    Long id;

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

    String skills;

    String talents;

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

    String personality;

    String apperance;

    String livePlace;
}
