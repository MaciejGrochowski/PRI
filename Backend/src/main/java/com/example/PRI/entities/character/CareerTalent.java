package com.example.PRI.entities.character;


import com.example.PRI.entities.GeneralEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name ="career_skill")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CareerTalent extends GeneralEntity {

    @ManyToOne
    @JoinColumn(name = "career", referencedColumnName ="id")
    Career career;

    @ManyToOne
    @JoinColumn(name = "talent", referencedColumnName="id")
    Talent talent;

    Integer type;
    Double probability;
}
