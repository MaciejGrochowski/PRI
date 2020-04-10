package com.example.PRI.entities.character;

import com.example.PRI.entities.GeneralEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity(name ="talents")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Talent extends GeneralEntity {

    String name;
    String properties; //niektóre talenty dają premię do umiejętności albo do statystyk, które należy nanieść.


}
