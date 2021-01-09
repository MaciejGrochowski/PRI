package com.example.PRI.entities.session;


import com.example.PRI.entities.GeneralEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity(name ="attributes_visibility")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttributesVisibility extends GeneralEntity {

    boolean name;
    boolean birthPlace;
    boolean surname;
    boolean livePlace;
    boolean currentCareer;
    boolean previousCareers;
    boolean oldcareers;
    boolean race;
    boolean sex;
    boolean birthDate;
    boolean height;
    boolean weight;
    boolean starSign;
    boolean eyeColor;
    boolean hairColor;
    boolean emotion;
    boolean personality;
    boolean apperance;
    boolean prediction;
    boolean religion;
    boolean talents;
    boolean skills;
    boolean weaponSkill;
    boolean ballisticSkill;
    boolean strength;
    boolean toughness;
    boolean agility;
    boolean intelligence;
    boolean willPower;
    boolean fellowship;
    boolean attacks;
    boolean wounds;
    boolean movement;
    boolean magic;

}
