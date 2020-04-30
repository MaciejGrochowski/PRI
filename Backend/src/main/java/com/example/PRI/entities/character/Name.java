package com.example.PRI.entities.character;

import com.example.PRI.entities.GeneralEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity(name ="names")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Name extends GeneralEntity {

    @Column(unique = true)
    String name;
    boolean isMale;
    boolean isFemale;
    boolean isElf;
    boolean isHuman;
    boolean isDwarf;
    boolean isHalfling;
    boolean usedByGenerator;
    double probabilityGentry; //Wśród szlachty Hans mniej popularny niż Hieronymous
    double probabilityNotGentry; //Wśród prostych ludzi Hans popularniejszy niż Hieronymous


}
