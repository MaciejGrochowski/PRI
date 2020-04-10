package com.example.PRI.entities.character;

import com.example.PRI.entities.GeneralEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity(name ="predictions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prediction extends GeneralEntity {
    String text;
    double probability;

}
