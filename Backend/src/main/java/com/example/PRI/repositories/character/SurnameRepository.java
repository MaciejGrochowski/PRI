package com.example.PRI.repositories.character;

import com.example.PRI.entities.character.Surname;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurnameRepository extends CrudRepository<Surname, Long> {

}
