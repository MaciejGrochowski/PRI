package com.example.PRI.dtos.users;


import com.example.PRI.dtos.characters.UserOfAppCharacterOutputDto;
import com.example.PRI.dtos.histories.HistoryListCharacterDetailsOutputDto;
import com.example.PRI.entities.session.Session;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOfAppDetailsOutputDto {

    String username;
//    String password;
    String description;
    String mail;
    String discord;
    String facebook;

    List<UserOfAppCharacterOutputDto> characters;

    List<HistoryListCharacterDetailsOutputDto> histories;

    List<Session> sessions;
}
