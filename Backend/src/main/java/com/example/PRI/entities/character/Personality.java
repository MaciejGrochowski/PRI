package com.example.PRI.entities.character;

import com.example.PRI.entities.GeneralEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name ="personalities")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Personality extends GeneralEntity {

    @Column(unique = true)
    String name;
    String type;
    Double probability;

    //ToDiscuss - Na cechy wpływa profesja i statystyki. Jak to zbudować architektonicznie?
}
