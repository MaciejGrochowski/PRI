package com.example.PRI.services.character;

import com.example.PRI.entities.character.Name;
import com.example.PRI.entities.character.Surname;
import com.example.PRI.repositories.character.NameRepository;
import com.example.PRI.services.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NameService extends GeneralService {

    @Autowired
    NameRepository nameRepository;

    public void save(Name name){
        Name nam = nameRepository.findByName(name.getName());
        if(nam.getId() > 0) name.setId(nam.getId());
        nameRepository.save(name);

//        nameRepository.save(name);
    }



}
