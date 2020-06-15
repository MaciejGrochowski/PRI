package com.example.PRI.dtos.characters;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AutocompleteFilterCharactersOutputDto {
    List<String> placeNames;
    List<String> careerNames;
    List<String> apperanceNames;
    List<String> personalityNames;
    List<String> talentNames;
    List<String> skillNames;
    List<String> emotionNames;
    List<String> eyeColors;
    List<String> hairColors;
    List<String> religionNames;
    List<String> starSignNames;
}