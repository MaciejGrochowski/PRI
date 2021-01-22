package com.example.PRI.repositories.character;

import com.example.PRI.entities.character.Personality;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonalityRepository extends CrudRepository<Personality, Long> {

    List<Personality> findByNameIn(List<String> personalityListString);

    List<Personality> findAll();

    Personality findByName(String name);

    List<Personality> findByFemaleNameIn(List<String> personalityListString);
}
