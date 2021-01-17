package com.example.PRI.services.session;


import com.example.PRI.converters.CharacterConverter;
import com.example.PRI.converters.SessionConverter;
import com.example.PRI.dtos.characters.CharacterSessionOutputDto;
import com.example.PRI.dtos.sessions.*;
import com.example.PRI.dtos.users.JwtResponse;
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
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import com.example.PRI.entities.character.Character;


import java.util.*;
import java.util.stream.Collectors;

import static com.example.PRI.converters.SessionConverter.convertAttributesVisibilityInput;

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

        UserOfApp user = userOfAppService.getUserByAuthentication(auth);

        if (user == null) return -1L;

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
        UserOfApp user = userOfAppService.findByUsername(username);
        List<Session> sessions = sessionRepository.findByCreatedUserOfApp(user);
        sessions.forEach(c -> sessionListOutputDto.add(SessionConverter.convert(c, this.isHashcodeVisible(username, auth))));


        return sessionListOutputDto;
    }

    private Boolean isHashcodeVisible(String username, Authentication auth) {

        if (auth == null) return false;
        UserOfApp user = userOfAppService.getUserByAuthentication(auth);
        return username.equals(user.getUsername());
    }

    public Long addCharactersToSession(CharactersSessionInputDto charactersSessionInputDto, Authentication auth) {

        UserOfApp user = userOfAppService.getUserByAuthentication(auth);

        Optional<Session> optionalSession = sessionRepository.findById(charactersSessionInputDto.getSelectedSessionId());

        List<Character> characters = characterService.getCharactersByIdIn(charactersSessionInputDto.getCharacterIds());

        if (optionalSession.isPresent() && characters.size() > 0 && user.equals(optionalSession.get().getCreatedUserOfApp())) {

            Session session = optionalSession.get();
            List<SessionCharacter> sessionCharacterList = new ArrayList<>();
            sessionCharacterList.addAll(session.getSessionCharacterList());


            for (Character c : characters) {
                Optional<SessionCharacter> existingCharacter = session.getSessionCharacterList().stream().filter(s -> s.getCharacter().getId()==c.getId()).findFirst();
                if(existingCharacter.isPresent()) {
//                    sessionCharacterList.add(existingCharacter.get());
                    continue;
                }
                SessionCharacter sessionCharacter = new SessionCharacter();
                sessionCharacter.setCharacter(c);
                AttributesVisibility atrVis = new AttributesVisibility();
                sessionCharacter.setAttributesVisibility(atrVis);
                attributesVisibilityrRepository.save(atrVis);
                sessionCharacterList.add(sessionCharacter);
                sessionCharacterRepository.save(sessionCharacter);
            }


            session.setSessionCharacterList(sessionCharacterList);
            session.setLastModifiedDate(new Date());
            sessionRepository.save(session);
            return session.getId();
        } else return -1L;
    }


    public SessionDetailsOutputDto getSessionDetailsByHashString(String hashcode, Authentication auth) {
        Optional<Session> optionalSession = sessionRepository.findByRandomIdSession(hashcode);

        UserOfApp user = userOfAppService.getUserByAuthentication(auth);
        SessionDetailsOutputDto output = new SessionDetailsOutputDto();

        if (optionalSession.isPresent()) {
            Session session = optionalSession.get();
            boolean isMG = false;
            if (session.getCreatedUserOfApp().equals(user)) isMG = true;

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

        for (SessionCharacter character : session.getSessionCharacterList()) {
            CharacterSessionOutputDto characterOutput = CharacterConverter.convertToSessionCharacter(character);
            if (isMG) {
                characterOutput.setId(character.getCharacter().getId());
            }
            output.add(characterOutput);
        }
        return output;
    }


    public ResponseEntity<?> editSession(SessionEditInputDto sessionEditInputDto, Authentication auth) {


        UserOfApp user = userOfAppService.getUserByAuthentication(auth);

        if (user == null) {
            return ResponseEntity.badRequest().body("USER_DOESNT_EXIST"); //ToDo error-outputs to enum
        }

        Optional<Session> optionalSession = sessionRepository.findByRandomIdSession(sessionEditInputDto.getHashcode());

        if (optionalSession.isEmpty()) {
            return ResponseEntity.badRequest().body("SESSION_DOESNT_EXIST");
        }
        Session session = optionalSession.get();

        if (!session.getCreatedUserOfApp().equals(user)) {
            return ResponseEntity.status(401).body("UNAUTHORIZED");
        }

        session.setName(sessionEditInputDto.getName());
        session.setDescription(sessionEditInputDto.getDescription());
        sessionRepository.save(session);
        return ResponseEntity.ok(String.valueOf(session.getId()));

    }

    public ResponseEntity<?> deleteCharacterFromSession(String hashcode, Long characterId, Authentication auth) {
        Optional<Session> optionalSession = sessionRepository.findByRandomIdSession(hashcode);
        UserOfApp user = userOfAppService.getUserByAuthentication(auth);

        if(user == null){
            return ResponseEntity.badRequest().body("USER_DOESNT_EXIST");
        }
        if (optionalSession.isEmpty()) {
            return ResponseEntity.badRequest().body("SESSION_DOESNT_EXIST");
        }
        Session session = optionalSession.get();
        if (!session.getCreatedUserOfApp().equals(user)) {
            return ResponseEntity.status(401).body("UNAUTHORIZED");
        }
        Optional<SessionCharacter> sessionCharacter = session.getSessionCharacterList().stream().filter(s -> s.getCharacter().getId() == characterId).findFirst();

        session.setSessionCharacterList(session.getSessionCharacterList().stream().filter(s -> s.getCharacter().getId()!=characterId).collect(Collectors.toList()));
        sessionRepository.save(session);

        if(sessionCharacter.isPresent()){
            session.getSessionCharacterList().stream().filter(s -> s.getCharacter().getId()==characterId).findFirst();
            sessionCharacterRepository.delete(sessionCharacter.get());
            attributesVisibilityrRepository.delete(sessionCharacter.get().getAttributesVisibility());
        }

        return ResponseEntity.ok(String.valueOf(session.getId()));
    }

    public ResponseEntity<?> changeGlobalVisibilityCharactersDataOfSession(String hashcode, AttributesVisibilityInputDto attributesVisibilityInputDto, Authentication auth) {
        Optional<Session> optionalSession = sessionRepository.findByRandomIdSession(hashcode);
        UserOfApp user = userOfAppService.getUserByAuthentication(auth);

        if(user == null){
            return ResponseEntity.badRequest().body("USER_DOESNT_EXIST");
        }
        if (optionalSession.isEmpty()) {
            return ResponseEntity.badRequest().body("SESSION_DOESNT_EXIST");
        }
        Session session = optionalSession.get();
        if (!session.getCreatedUserOfApp().equals(user)) {
            return ResponseEntity.status(401).body("UNAUTHORIZED");
        }
        for(SessionCharacter sessionCharacter : session.getSessionCharacterList()){
            this.setAttributesVariablesForCharacterSession(sessionCharacter, attributesVisibilityInputDto);
        }
        return ResponseEntity.ok(String.valueOf(session.getId()));
    }

    public ResponseEntity<?> changeCharacterVisibilityInSession(String hashcode, Long characterId, AttributesVisibilityInputDto attributesVisibilityInputDto, Authentication auth) {
        Optional<Session> optionalSession = sessionRepository.findByRandomIdSession(hashcode); //ToDo try to Dont Repeat Yourself
        UserOfApp user = userOfAppService.getUserByAuthentication(auth);

        if(user == null){
            return ResponseEntity.badRequest().body("USER_DOESNT_EXIST");
        }
        if (optionalSession.isEmpty()) {
            return ResponseEntity.badRequest().body("SESSION_DOESNT_EXIST");
        }
        Session session = optionalSession.get();
        if (!session.getCreatedUserOfApp().equals(user)) {
            return ResponseEntity.status(401).body("UNAUTHORIZED");
        }

        Optional<SessionCharacter> characterSession = session.getSessionCharacterList().stream().filter(ch -> characterId.equals(ch.getCharacter().getId())).findFirst();
        characterSession.ifPresent(sessionCharacter -> this.setAttributesVariablesForCharacterSession(sessionCharacter, attributesVisibilityInputDto));

        return ResponseEntity.ok(String.valueOf(session.getId()));
    }

    private void setAttributesVariablesForCharacterSession(SessionCharacter sessionCharacter, AttributesVisibilityInputDto attributesVisibilityInputDto){
        long attributesVisibilityId = sessionCharacter.getAttributesVisibility().getId();
        AttributesVisibility newAttributesVisibility = convertAttributesVisibilityInput(attributesVisibilityInputDto);
        newAttributesVisibility.setId(attributesVisibilityId);
        attributesVisibilityrRepository.save(newAttributesVisibility);
    }
}
