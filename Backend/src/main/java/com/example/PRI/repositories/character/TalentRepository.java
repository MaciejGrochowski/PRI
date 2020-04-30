package com.example.PRI.repositories.character;

import com.example.PRI.entities.character.Talent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TalentRepository extends CrudRepository<Talent, Long> {

    Talent findByName(String name);

    List<Talent> findByNameIn(List<String> talentsListString);

    List<Talent> findAll();
}
