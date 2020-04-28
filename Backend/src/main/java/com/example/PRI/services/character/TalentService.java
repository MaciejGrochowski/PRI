package com.example.PRI.services.character;

import com.example.PRI.entities.character.Talent;
import com.example.PRI.repositories.character.TalentRepository;
import com.example.PRI.services.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TalentService extends GeneralService {

    @Autowired
    TalentRepository talentRepository;

    public void save(Talent t){ //ToDo implement save or update, not always save
        talentRepository.save(t);
    }

    public Optional<Talent> findByName(String name){
        return Optional.ofNullable(talentRepository.findByName(name));
    }

    public List<Talent> findByNameIn(List<String> talentsListString) {
        return talentRepository.findByNameIn(talentsListString);
    }
}
