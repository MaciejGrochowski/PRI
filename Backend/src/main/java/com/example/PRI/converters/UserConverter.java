package com.example.PRI.converters;

import com.example.PRI.dtos.characters.CharacterDefaultAttributesOutputDto;
import com.example.PRI.dtos.characters.CharacterInputDto;
import com.example.PRI.dtos.characters.CharacterListOutputDto;
import com.example.PRI.dtos.characters.UserCharacterOutputDto;
import com.example.PRI.dtos.histories.HistoryListCharacterDetailsOutputDto;
import com.example.PRI.dtos.users.UserDetailsOutputDto;
import com.example.PRI.entities.User;
import com.example.PRI.entities.character.Character;
import com.example.PRI.entities.history.History;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static com.example.PRI.converters.HistoryConverter.convertForCharacterDetails;

public class UserConverter {


    public static UserDetailsOutputDto convertDetails(User user) {
        UserDetailsOutputDto udod = new UserDetailsOutputDto();
        udod.setUsername(user.getUsername());
        udod.setDescription(user.getDescription());
        udod.setDiscord(user.getDiscord());
        udod.setFacebook(user.getFacebook());
        udod.setMail(user.getMail());
        udod.setCharacters(user.getCharacters().stream()
                .map(CharacterConverter::convertToUserProfileCharacter)
                .collect(Collectors.toCollection(ArrayList::new)));
        udod.setHistories(user.getHistories().stream()
                .map(HistoryConverter::convertForCharacterDetails)
                .collect(Collectors.toCollection(ArrayList::new)));
        udod.setSessions(user.getSessions());

        return udod;
    }
}
