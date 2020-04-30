package com.example.PRI.dtos.characters;

import com.example.PRI.entities.ImperialDate;
import com.example.PRI.entities.character.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CharacterDefaultAttributesOutputDto {

    String birthPlace;

    String race;

    String eyeColor;

    String currentCareer;

    String hairColor;

    ImperialDate birthDate;

    String starSign;

    StatisticsOutputDto baseStats;

    List<String> dominatingEmotions;

    String sex;

    String religion;

    Integer weight;
    Integer height;

    String surname;

    String name;

    String prediction;

    List<String> careers;

    List<SkillOutputDto> skills;

    List<String> talents;

    List<String> personality;

    List<String> apperance;

    String livePlace;

    String createdBy; //User tworzacy


}
