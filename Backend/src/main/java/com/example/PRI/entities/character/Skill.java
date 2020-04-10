package com.example.PRI.entities.character;

import com.example.PRI.entities.GeneralEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity(name ="skills")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Skill extends GeneralEntity {

    String name;
    Integer level; //0 lub 10 lub 20

}
