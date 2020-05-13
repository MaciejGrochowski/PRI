package com.example.PRI.entities.character;

import com.example.PRI.entities.GeneralEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name ="emotions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Emotion extends GeneralEntity {
    @Column(unique = true)
    String name;
    String type;
}
