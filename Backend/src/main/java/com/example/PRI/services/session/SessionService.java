package com.example.PRI.services.session;


import com.example.PRI.converters.CharacterConverter;
import com.example.PRI.converters.SessionConverter;
import com.example.PRI.dtos.sessions.SessionInputDto;
import com.example.PRI.dtos.sessions.SessionOutputDto;
import com.example.PRI.entities.UserOfApp;
import com.example.PRI.entities.session.Session;
import com.example.PRI.repositories.session.SessionRepository;
import com.example.PRI.services.GeneralService;
import com.example.PRI.services.UserOfAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SessionService extends GeneralService {

    @Autowired
    UserOfAppService userOfAppService;

    @Autowired
    SessionRepository sessionRepository;


    public Long createSession(SessionInputDto sessionInputDto, Authentication auth) {

        UserOfApp user = userOfAppService.findByUsername(userOfAppService.getUsernameFromAuthentication(auth));

        if(user==null) return -1L;

        Session session = new Session();

        session.setCreatedDate(new Date());
        session.setCreatedUserOfApp(user);
        session.setDescription(sessionInputDto.getDescription());
        session.setName(sessionInputDto.getName());
        session.setLastModifiedDate(new Date());
        sessionRepository.save(session);
        return session.getId();
    }

    public List<SessionOutputDto> getSessionsByUser(String username) {
        List<SessionOutputDto> sessionListOutputDto = new ArrayList<>();
        UserOfApp user= userOfAppService.findByUsername(username);
        List<Session> sessions = sessionRepository.findByCreatedUserOfApp(user);
        sessions.forEach(c -> sessionListOutputDto.add(SessionConverter.convert(c)));
        return sessionListOutputDto;
    }
}
