package com.example.PRI.entities;

import com.example.PRI.entities.character.Character;
import com.example.PRI.entities.history.History;
import com.example.PRI.entities.session.Session;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity(name ="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends GeneralEntity {

    @Column(unique = true)
    String username;

    String password;
    String description;

    @Column(unique = true)
    String mail;
    String discord;
    String facebook;
    String token;
    @OneToMany(cascade= CascadeType.ALL)
    List<Character> characters;

    @OneToMany
    List<History> histories;

    @OneToMany
    List<Session> sessions;


}
