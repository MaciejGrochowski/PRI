package com.example.PRI.services.character;

import com.example.PRI.entities.character.Personality;
import com.example.PRI.repositories.character.PersonalityRepository;
import com.example.PRI.services.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalityService extends GeneralService {

    @Autowired
    PersonalityRepository personalityRepository;

    public List<Personality> findByNameIn(List<String> personalityListString) {
        return personalityRepository.findByNameIn(personalityListString);
    }
}
