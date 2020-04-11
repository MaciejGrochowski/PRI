package com.example.PRI.entities;

import com.example.PRI.enums.PlaceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity(name ="places")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Place extends GeneralEntity {

    //Nazwa miejsca
    String name;

    //Dzieci miejsca - co się w miejscu znajduje.
    @OneToMany
    List<Place> childs;

    /*
    Rodzic miejsca - np rodzicem miasta jest 'województwo', rodzicem województwa jest państwo.
    Ważne - ktoś może mieć miejsce urodzenia zdefiniowane tylko do np. województwa
    np. urodzony w przydrożnej karczmie przez matkę wędrowną.
    W przypadku miejsca pobytu świadczy to, że osoba jest w podróży po województwie lub kraj
    */
    @ManyToOne
    Place parent;

    //Wioska, Małe miasto, Miasto, Metropolia
    PlaceType placeType;

    /*
    Miejsce może mieć różne cechy wpływające na generowanie, np.
    isForest (Przy lesie częściej są elfy, myśliwi i łowcy)
    isMountains (W górach częściej krasnoludy i górnicy)
    isWater (Przy wodzie częściej rybacy, przewoźnicy, bosmani, skill pływanie itd)
    Dodatkowo przy module "Miejsca" można myśleć o poinformowaniu o tym.
    Nie znamy póki co poziomu szczegółowości ani ilości tego, więc lepiej nie kolumny.
    Format zapisu: JSON(?)
     */
    String properties;

}
