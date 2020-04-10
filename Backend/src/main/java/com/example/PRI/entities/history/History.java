package com.example.PRI.entities.history;

import com.example.PRI.entities.GeneralEntity;
import com.example.PRI.entities.ImperialDate;
import com.example.PRI.entities.User;
import com.example.PRI.entities.character.Character;
import com.example.PRI.entities.Place;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;

@Entity(name ="histories")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class History extends GeneralEntity {

    @ManyToOne
    ImperialDate date;

    @ManyToOne
    User createdBy; //Potem to bedzie uzytkownik

    Date createdDate;

    @ManyToMany
    List<Character> mainCharacters;

    @ManyToMany
    List<Character> additionalCharacters;

    String description;

    @ManyToOne
    Place place;

}
