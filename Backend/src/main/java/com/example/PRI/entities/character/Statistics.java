package com.example.PRI.entities.character;


import com.example.PRI.entities.GeneralEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity(name ="statistics")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Statistics extends GeneralEntity {
    int weaponSkill; //walka wręcz
    int ballisticSkill; //umiejętności strzeleckie
    int strength; // krzepa
    int toughness; // odporność
    int agility; // zręczność
    int intelligence; //inteligencja
    int willPower; // siła woli
    int fellowship; // ogłada
    int attacks; // ataki
    int wounds; // żywotność
    int magic; // magia
    int movement; // szybkość

    int getStrengthBonus(){ return strength/10; } // siła
    int getToughnessBonus(){ return toughness/10; } // wytrzymałość


}
