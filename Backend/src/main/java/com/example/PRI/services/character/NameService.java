package com.example.PRI.services.character;

import com.example.PRI.entities.character.Name;
import com.example.PRI.entities.character.Surname;
import com.example.PRI.repositories.character.NameRepository;
import com.example.PRI.services.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NameService extends GeneralService {

    @Autowired
    private NameRepository nameRepository;


    //ToDo implementation saveOrUpdate
    public void save(Name name){
        Name nam = nameRepository.findByName(name.getName());
        if(nam != null) name.setId(nam.getId());
        nameRepository.save(name);

//        nameRepository.save(name);
    }

    public Optional<Name> findByName(String name){
        return Optional.ofNullable(nameRepository.findByName(name));
    }


}
