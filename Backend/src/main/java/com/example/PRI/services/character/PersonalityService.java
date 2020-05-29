package com.example.PRI.services.character;

import com.example.PRI.entities.character.Apperance;
import com.example.PRI.entities.character.EyeColor;
import com.example.PRI.entities.character.Personality;
import com.example.PRI.repositories.character.PersonalityRepository;
import com.example.PRI.services.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonalityService extends GeneralService {

    @Autowired
    PersonalityRepository personalityRepository;

    public void save(Personality per){
        Personality perse = personalityRepository.findByName(per.getName());
        if(perse != null) per.setId(perse.getId());
        personalityRepository.save(per);
    }

    public List<Personality> findByNameIn(List<String> personalityListString) {
        return personalityRepository.findByNameIn(personalityListString);
    }

    public List<String> getAllNames() {
        return personalityRepository.findAll().stream().map(Personality::getName).distinct().collect(Collectors.toList());
    }

    public List<Personality> findAll() {
        return personalityRepository.findAll();
    }
}
