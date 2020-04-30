package com.example.PRI.entities.character;

import com.example.PRI.entities.GeneralEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name ="surnames")
@DiscriminatorColumn(name = GeneralEntity.DISCRIMINATOR_COLUMN, discriminatorType = DiscriminatorType.STRING, length = 255)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Surname extends GeneralEntity {

    @Column(unique = true)
    String surname;
    boolean isMale;
    boolean isFemale;
    boolean isElf;
    boolean isHuman;
    boolean isDwarf;
    boolean isHalfling;
    boolean isGentry;
    boolean usedByGenerator;
    double probability; //Schmidt popularniejsze niż Hohenzollern

}
