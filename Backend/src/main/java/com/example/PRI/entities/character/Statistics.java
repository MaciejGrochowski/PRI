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
    Integer weaponSkill; //walka wręcz
    Integer ballisticSkill; //umiejętności strzeleckie
    Integer strength; // krzepa
    Integer toughness; // odporność
    Integer agility; // zręczność
    Integer intelligence; //inteligencja
    Integer willPower; // siła woli
    Integer fellowship; // ogłada
    Integer attacks; // ataki
    Integer wounds; // żywotność
    Integer magic; // magia
    Integer movement; // szybkość

    Integer getStrengthBonus(){ return strength/10; } // siła
    Integer getToughnessBonus(){ return toughness/10; } // wytrzymałość


}
