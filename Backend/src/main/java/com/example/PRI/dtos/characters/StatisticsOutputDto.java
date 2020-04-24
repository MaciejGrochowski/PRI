package com.example.PRI.dtos.characters;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticsOutputDto {

    private int weaponSkill; //walka wręcz
    private int ballisticSkill; //umiejętności strzeleckie
    private int strength; // krzepa
    private int toughness; // odporność
    private int agility; // zręczność
    private int intelligence; //inteligencja
    private int willPower; // siła woli
    private int fellowship; // ogłada
    private int attacks; // ataki
    private int wounds; // żywotność
    private int magic; // magia
    private int movement; // szybkość

}
