package com.example.PRI.entities.character;


import com.example.PRI.entities.GeneralEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name ="apperance")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Apperance extends GeneralEntity {

    @Column(unique = true)
    String name;
    String type;
    double probability;

    //ToDiscuss - Na cechy wyglądu wpływa wiara, płeć, rasa i profesje - jak to zbudować?

}
