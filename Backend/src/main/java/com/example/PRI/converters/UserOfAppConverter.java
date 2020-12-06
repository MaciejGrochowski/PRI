package com.example.PRI.converters;

import com.example.PRI.dtos.users.UserOfAppDetailsOutputDto;
import com.example.PRI.entities.UserOfApp;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class UserOfAppConverter {


    public static UserOfAppDetailsOutputDto convertDetails(UserOfApp userOFApp) {
        UserOfAppDetailsOutputDto udod = new UserOfAppDetailsOutputDto();
        udod.setUsername(userOFApp.getUsername());
        udod.setDescription(userOFApp.getDescription());
        udod.setDiscord(userOFApp.getDiscord());
        udod.setFacebook(userOFApp.getFacebook());
        udod.setMail(userOFApp.getMail());
        udod.setCharacters(userOFApp.getCharacters().stream()
                .map(CharacterConverter::convertToUserProfileCharacter)
                .collect(Collectors.toCollection(ArrayList::new)));
        udod.setHistories(userOFApp.getHistories().stream()
                .map(HistoryConverter::convertForCharacterDetails)
                .collect(Collectors.toCollection(ArrayList::new)));
        udod.setSessions(userOFApp.getSessions());

        return udod;
    }
}
