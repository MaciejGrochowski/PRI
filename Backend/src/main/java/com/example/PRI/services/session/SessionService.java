package com.example.PRI.services.session;


import com.example.PRI.converters.CharacterConverter;
import com.example.PRI.converters.SessionConverter;
import com.example.PRI.dtos.characters.CharacterSessionOutputDto;
import com.example.PRI.dtos.sessions.CharactersSessionInputDto;
import com.example.PRI.dtos.sessions.SessionDetailsOutputDto;
import com.example.PRI.dtos.sessions.SessionInputDto;
import com.example.PRI.dtos.sessions.SessionOutputDto;
import com.example.PRI.entities.UserOfApp;
import com.example.PRI.entities.session.AttributesVisibility;
import com.example.PRI.entities.session.Session;
import com.example.PRI.entities.session.SessionCharacter;
import com.example.PRI.repositories.session.AttributesVisibilityrRepository;
import com.example.PRI.repositories.session.SessionCharacterRepository;
import com.example.PRI.repositories.session.SessionRepository;
import com.example.PRI.services.GeneralService;
import com.example.PRI.services.UserOfAppService;
import com.example.PRI.services.character.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import com.example.PRI.entities.character.Character;


import java.util.*;

@Service
public class SessionService extends GeneralService {

    @Autowired
    UserOfAppService userOfAppService;

    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    CharacterService characterService;

    @Autowired
    SessionCharacterRepository sessionCharacterRepository;

    @Autowired
    AttributesVisibilityrRepository attributesVisibilityrRepository;


    public Long createSession(SessionInputDto sessionInputDto, Authentication auth) {

        UserOfApp user = userOfAppService.findByUsername(userOfAppService.getUsernameFromAuthentication(auth));

        if(user==null) return -1L;

        Session session = new Session();

        session.setCreatedDate(new Date());
        session.setCreatedUserOfApp(user);
        session.setDescription(sessionInputDto.getDescription());
        session.setName(sessionInputDto.getName());
        session.setLastModifiedDate(new Date());
        session.setRandomIdSession(UUID.randomUUID().toString()); //ToDo can cause problem if not unique
        sessionRepository.save(session);
        return session.getId();
    }

    public List<SessionOutputDto> getSessionsByUser(String username, Authentication auth) {

        List<SessionOutputDto> sessionListOutputDto = new ArrayList<>();
        UserOfApp user= userOfAppService.findByUsername(username);
        List<Session> sessions = sessionRepository.findByCreatedUserOfApp(user);
        sessions.forEach(c -> sessionListOutputDto.add(SessionConverter.convert(c, this.isHashcodeVisible(username, auth))));




        return sessionListOutputDto;
    }

    private Boolean isHashcodeVisible(String username, Authentication auth) {

        if(auth==null) return false;
        UserOfApp user = userOfAppService.findByUsername(userOfAppService.getUsernameFromAuthentication(auth));
        return username.equals(user.getUsername());
    }

    public Long addCharactersToSession(CharactersSessionInputDto charactersSessionInputDto, Authentication auth) {

        UserOfApp user = userOfAppService.findByUsername(userOfAppService.getUsernameFromAuthentication(auth));

        Optional<Session> optionalSession = sessionRepository.findById(charactersSessionInputDto.getSelectedSessionId());

        List<Character> characters = characterService.getCharactersByIdIn(charactersSessionInputDto.getCharacterIds());

        if(optionalSession.isPresent() && characters.size() > 0 && user.equals(optionalSession.get().getCreatedUserOfApp())){

            List<SessionCharacter> sessionCharacterList = new ArrayList<>();


            for(Character c : characters){
                SessionCharacter sessionCharacter = new SessionCharacter();
                sessionCharacter.setCharacter(c);
                AttributesVisibility atrVis = new AttributesVisibility();
                sessionCharacter.setAttributesVisibility(atrVis);
                attributesVisibilityrRepository.save(atrVis);
                sessionCharacterList.add(sessionCharacter);
                sessionCharacterRepository.save(sessionCharacter);
            }

            Session session = optionalSession.get();
            session.setSessionCharacterList(sessionCharacterList);
            session.setLastModifiedDate(new Date());
            sessionRepository.save(session);
            return session.getId();
        }
        else return -1L;
    }


    public SessionDetailsOutputDto getSessionDetailsByHashString(String hashcode, Authentication auth){
        Optional<Session> optionalSession = sessionRepository.findByRandomIdSession(hashcode);

        UserOfApp user = userOfAppService.findByUsername(userOfAppService.getUsernameFromAuthentication(auth));
        SessionDetailsOutputDto output = new SessionDetailsOutputDto();

        if(optionalSession.isPresent()){
            Session session = optionalSession.get();
            boolean isMG = false;
            if(session.getCreatedUserOfApp().equals(user)) isMG = true;

            output.setName(session.getName());
            output.setDescription(session.getDescription());
            output.setCreatedBy(session.getCreatedUserOfApp().getUsername());
            output.setCreatedDate(session.getCreatedDate());

            List<CharacterSessionOutputDto> characterDetails = this.getCharacterSessionDetails(session, isMG);
            output.setCharacters(characterDetails);
            return output;
            }

        return null;









        }

    private List<CharacterSessionOutputDto> getCharacterSessionDetails(Session session, boolean isMG) {
        List<CharacterSessionOutputDto> output = new ArrayList<>();

        for(SessionCharacter character: session.getSessionCharacterList()){
            CharacterSessionOutputDto characterOutput = CharacterConverter.convertToSessionCharacter(character);
            if(isMG){
                characterOutput.setId(character.getCharacter().getId());
            }
            output.add(characterOutput);
    }
        return output;
}


}
