package com.example.PRI.repositories.character;

import com.example.PRI.entities.character.Surname;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurnameRepository extends CrudRepository<Surname, Long> {

    Surname findBySurname(String surname);

    List<Surname> findByIsHalfling(Boolean isTrue);

    List<Surname> findByIsHalflingAndIsFemale(Boolean halfling, Boolean female);

    List<Surname> findByIsElf(Boolean isTrue);

    List<Surname> findByIsFemaleAndIsElf(Boolean b, Boolean b1);

    List<Surname> findByIsHuman(Boolean isTrue);

    List<Surname> findByIsFemaleAndIsHuman(Boolean b, Boolean b1);
    
    List<Surname> findByIsDwarf(Boolean isTrue);

    List<Surname> findByIsFemaleAndIsDwarf(Boolean b, Boolean b1);


    List<Surname> findByIsElfAndIsFemale(Boolean isElf, Boolean isFemale);

    List<Surname> findByIsHumanAndIsFemale(Boolean isHuman, Boolean isFemale);

    List<Surname> findByIsDwarfAndIsFemale(Boolean isDwarf, Boolean isFemale);
}
