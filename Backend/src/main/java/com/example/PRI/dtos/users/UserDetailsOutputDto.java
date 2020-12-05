package com.example.PRI.dtos.users;


import com.example.PRI.dtos.characters.CharacterListOutputDto;
import com.example.PRI.dtos.characters.UserCharacterOutputDto;
import com.example.PRI.dtos.histories.HistoryListCharacterDetailsOutputDto;
import com.example.PRI.entities.character.Character;
import com.example.PRI.entities.history.History;
import com.example.PRI.entities.session.Session;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.OneToMany;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsOutputDto {

    String username;
//    String password;
    String description;
    String mail;
    String discord;
    String facebook;

    List<UserCharacterOutputDto> characters;

    List<HistoryListCharacterDetailsOutputDto> histories;

    @OneToMany
    List<Session> sessions;
}
