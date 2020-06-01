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
        return Optional.ofNullable(surnameRepository.findBySurname(surname)); //ToDo surname??
    }

    public List<Surname> findHalflingSurnames() {
        return surnameRepository.findByIsHalfling(true);
    }

    public List<Surname> findElfSurnames() {
        return surnameRepository.findByIsElf(true);
    }

    public List<Surname> findHumanSurnames() {return surnameRepository.findByIsHuman(true); }

    public List<Surname> findDwarfSurnames(){return surnameRepository.findByIsDwarf(true);}

    public List<Surname> findHalflingFemaleSurnames(Boolean isHalfling, Boolean isFemale) { return surnameRepository.findByIsHalflingAndIsFemale(isHalfling, isFemale);}

    public List<Surname> findElfFemaleSurnames(Boolean isElf, Boolean isFemale) { return surnameRepository.findByIsElfAndIsFemale(isElf, isFemale);}
    public List<Surname> findHumanFemaleSurnames(Boolean isHuman, Boolean isFemale) { return surnameRepository.findByIsHumanAndIsFemale(isHuman, isFemale);}
    public List<Surname> findDwarfFemaleSurnames(Boolean isDwarf, Boolean isFemale) { return surnameRepository.findByIsDwarfAndIsFemale(isDwarf, isFemale);}
}
