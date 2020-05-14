package com.example.PRI.entities;

import com.example.PRI.enums.PlaceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name ="places")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Place extends GeneralEntity {

    //Nazwa miejsca
    String name;

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
    isRiver (Przy wodzie częściej rybacy, przewoźnicy, bosmani, skill pływanie itd)
    isLake (czy przy jeziozie)
    isSea (czy przy morzu)
    isCapital (czy jest stolicą)
    isSwamp (czy jest przy bagnie)
    isHill (czy jest przyy wzgórzu)
    isRoad (czy jest przy drodzę)
    isPopularRoad (czy jest przy głównej drodzę)
    Dodatkowo przy module "Miejsca" można myśleć o poinformowaniu o tym.
    Nie znamy póki co poziomu szczegółowości ani ilości tego, więc lepiej nie kolumny.
    Format zapisu: JSON(?)
     */
    String properties;

}
