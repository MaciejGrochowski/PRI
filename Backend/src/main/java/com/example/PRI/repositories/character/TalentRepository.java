package com.example.PRI.repositories.character;

import com.example.PRI.entities.character.Talent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TalentRepository extends CrudRepository<Talent, Long> {

}
