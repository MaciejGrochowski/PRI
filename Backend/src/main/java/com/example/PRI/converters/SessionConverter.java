package com.example.PRI.converters;

import com.example.PRI.dtos.sessions.SessionOutputDto;
import com.example.PRI.entities.session.Session;

public class SessionConverter {

    public static SessionOutputDto convert(Session s){
        SessionOutputDto sessionOutputDto = new SessionOutputDto();
        sessionOutputDto.setDescription(s.getDescription());
        sessionOutputDto.setName(s.getName());
        sessionOutputDto.setId(String.valueOf(s.getId()));
        sessionOutputDto.setCreatedDate(s.getCreatedDate());
        sessionOutputDto.setLastModifiedDate(s.getLastModifiedDate());
        if(s.getCreatedUserOfApp()!=null) sessionOutputDto.setCreatedUserBy(s.getCreatedUserOfApp().getUsername());
        return sessionOutputDto;

    }
}
