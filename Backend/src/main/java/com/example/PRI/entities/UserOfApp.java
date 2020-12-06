package com.example.PRI.entities;

import com.example.PRI.entities.character.Character;
import com.example.PRI.entities.history.History;
import com.example.PRI.entities.session.Session;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name ="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOfApp extends GeneralEntity {

    @Column(unique = true)
    String username;

    String password;
    String description;

    @Column(unique = true)
    String mail;
    String discord;
    String facebook;
    String token;


    @OneToMany(mappedBy="createdBy")
    List<Character> characters;

    @OneToMany(mappedBy="createdBy")
    List<History> histories;

    @OneToMany(mappedBy="createdUserOfApp")
    List<Session> sessions;


}
