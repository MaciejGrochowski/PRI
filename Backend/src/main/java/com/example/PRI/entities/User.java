package com.example.PRI.entities;

import com.example.PRI.entities.history.History;
import com.example.PRI.entities.session.Session;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.PRI.entities.character.Character;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Entity(name ="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends GeneralEntity {

    String username;
    String password;
    String description;
    String mail;
    String discord;
    String facebook;
    String token;
    @OneToMany
    List<Character> characters;

    @OneToMany
    List<History> histories;

    @OneToMany
    List<Session> sessions;


}
