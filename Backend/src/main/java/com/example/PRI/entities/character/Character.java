package com.example.PRI.entities.character;

import com.example.PRI.entities.GeneralEntity;
import com.example.PRI.entities.ImperialDate;
import com.example.PRI.entities.Place;
import com.example.PRI.entities.UserOfApp;
import com.example.PRI.enums.Race;
import com.example.PRI.enums.Religion;
import com.example.PRI.enums.Sex;
import com.example.PRI.enums.StarSign;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name ="characters")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Character extends GeneralEntity {

    @ManyToOne
    @JoinColumn(name="birth_place")
    Place birthPlace;

    Race race;

    @ManyToOne
    @JoinColumn(name="eye_color")
    EyeColor eyeColor;

    @ManyToOne
    @JoinColumn(name="hair_color")
    HairColor hairColor;

    @ManyToOne(cascade = {CascadeType.MERGE},fetch = FetchType.LAZY)
    @JoinColumn(name="birth_date")
    ImperialDate birthDate;

    StarSign starSign;

    @OneToOne(cascade = {CascadeType.MERGE},fetch = FetchType.LAZY)
    Statistics baseStats;

    @ManyToMany
    List<Emotion> dominatingEmotions;

    Sex sex;

    Religion religion;

    Integer weight;
    Integer height;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="surname")
    Surname surname;

    @ManyToOne
    @JoinColumn(name="name")
    Name name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="prediction")
    Prediction prediction;

    @ManyToMany
    List<Career> previousCareers;

    @ManyToOne(cascade = {CascadeType.MERGE},fetch = FetchType.LAZY)
    @JoinColumn(name = "currentCareer")
    Career currentCareer;

    @ManyToMany
    List<Skill> skills;

    @ManyToMany
    List<Talent> talents;

    Integer endWeaponSkills; //Todo class for that, we should use Statistics
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

//    @OneToOne
//    Statistics endStats; //Statystyki z rozwoju można odczytać z listy profesji

    @ManyToMany
    List<Personality> personality;

    @ManyToMany
    List<Apperance> apperance;

    @ManyToOne
    @JoinColumn(name="live_place")
    Place livePlace;
    //ToDiscuss - na miejsce pobytu wpływa miejsce urodzenia, profesja końcowa i statystyki - jak to zbudować?

    @ManyToOne
    @JoinColumn(name="created_by") //ToDO here can be a problem - use referencedColumnName="username"
    UserOfApp createdBy; //User tworzacy
    Date createdDate;

}
