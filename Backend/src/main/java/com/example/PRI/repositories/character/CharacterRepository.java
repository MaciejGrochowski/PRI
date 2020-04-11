package com.example.PRI.repositories.character;

import com.example.PRI.entities.character.Character;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends CrudRepository<Character, Long> {

}
