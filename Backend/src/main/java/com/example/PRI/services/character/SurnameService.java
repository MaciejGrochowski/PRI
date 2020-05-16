package com.example.PRI.services.character;

import com.example.PRI.entities.character.Surname;
import com.example.PRI.repositories.character.SurnameRepository;
import com.example.PRI.services.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SurnameService extends GeneralService {

    @Autowired
    private SurnameRepository surnameRepository;


    //ToDo implementation saveOrUpdate
    public void save(Surname surname){
        Surname sur = surnameRepository.findBySurname(surname.getSurname());
        if(sur != null && sur.getId() > 0) surname.setId(sur.getId());
        surnameRepository.save(surname);
    }

    public Optional<Surname> findBySurname(String surname){
        return Optional.ofNullable(surnameRepository.findBySurname(surname));
    }

    public List<Surname> findHalflingSurnames() {
        return surnameRepository.findByIsHalfling(true);
    }

    public List<Surname> findElfSurnames() {
        return surnameRepository.findByIsElf(true);
    }

    public List<Surname> findHumanSurnames() {
        return surnameRepository.findByIsHuman(true);
    }
}
