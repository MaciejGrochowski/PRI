package com.example.PRI.services.character;

import com.example.PRI.entities.character.Surname;
import com.example.PRI.repositories.character.SurnameRepository;
import com.example.PRI.services.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SurnameService extends GeneralService {

    @Autowired
    SurnameRepository surnameRepository;

    public void save(Surname surname){
        Surname sur = surnameRepository.findBySurname(surname.getSurname());
        if(sur.getId() > 0) surname.setId(sur.getId());
        surnameRepository.save(surname);
    }

}
