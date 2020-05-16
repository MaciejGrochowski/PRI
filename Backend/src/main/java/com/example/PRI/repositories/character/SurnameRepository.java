package com.example.PRI.repositories.character;

import com.example.PRI.entities.character.Surname;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurnameRepository extends CrudRepository<Surname, Long> {

    Surname findBySurname(String surname);

    List<Surname> findByIsHalfling(Boolean isTrue);

    List<Surname> findByIsElf(Boolean isTrue);

    List<Surname> findByIsHuman(Boolean isTrue);
}
