package com.example.PRI.entities;

import com.example.PRI.entities.character.Character;
import com.example.PRI.entities.history.History;
import com.example.PRI.entities.session.Session;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity(name ="users")
@Data
@Builder
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

    Boolean isActive;
    String UUIDActivation;


    @OneToMany(mappedBy="createdBy")
    List<Character> characters;

    @OneToMany(mappedBy="createdBy")
    List<History> histories;

    @OneToMany(mappedBy="createdUserOfApp")
    List<Session> sessions;

}
