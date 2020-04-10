package com.example.PRI.entities.character;

import com.example.PRI.entities.GeneralEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity(name ="emotions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Emotion extends GeneralEntity {
    String name;
}
