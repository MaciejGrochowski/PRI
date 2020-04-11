package com.example.PRI.repositories.character;

import com.example.PRI.entities.character.Personality;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalityRepository extends CrudRepository<Personality, Long> {

}
