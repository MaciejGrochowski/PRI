package com.example.PRI.entities.character;

import com.example.PRI.entities.GeneralEntity;
import com.example.PRI.entities.ImperialDate;
import com.example.PRI.entities.Place;
import com.example.PRI.entities.User;
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
    Place birthPlace;

    Race race;

    @ManyToOne
    EyeColor eyeColor;

    @ManyToOne
    HairColor hairColor;

    @ManyToOne
    ImperialDate birthDate;

    StarSign starSign;

    @OneToOne
    Statistics baseStats;

    @ManyToMany
    List<Emotion> dominatingEmotions;

    Sex sex;

    Religion religion;

    int weight;
    int height;

    @ManyToOne
    Surname surname;

    @ManyToOne
    Name name;

    @ManyToOne
    Prediction prediction;

    @ManyToMany
    List<Career> careers;

    @ManyToMany
    List<Skill> skills;

    @ManyToMany
    List<Talent> talents;

    @OneToOne
    Statistics endStats; //Statystyki z rozwoju można odczytać z listy profesji

    @ManyToMany
    List<Personality> personality;

    @ManyToMany
    List<Apperance> apperance;

    @ManyToOne
    Place livePlace;
    //ToDiscuss - na miejsce pobytu wpływa miejsce urodzenia, profesja końcowa i statystyki - jak to zbudować?

    @ManyToOne
    User createdBy; //User tworzacy
    Date createdDate;

}
