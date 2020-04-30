package com.example.PRI.entities.character;

import com.example.PRI.entities.GeneralEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name ="careers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Career extends GeneralEntity {

    String name;
    boolean isBaseProfession; //Profesje początkowe - od nich można zacząć
    double exitChance; //Szansa na zakończenie profesji i przejście na następną

    @ManyToOne
    Statistics statistics;

    @ManyToMany
    List<Skill> skills;

    @ManyToMany
    List<Talent> talents;

    @ManyToMany
    List<Career> careerExits; //Gdzie można dalej pójść

    //ToDiscuss - Jak to opisać?
    //Profesja wpływa na cechy wyglądu, np. blizny są częstsze u wojowników.
    //Profesja wpływa na cechy charakteru, np. kapłan będzie częściej religijny, złodziej częściej chciwy
    String properties;
}
