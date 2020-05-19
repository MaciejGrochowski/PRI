package com.example.PRI.repositories.character;

import com.example.PRI.entities.character.HairColor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HairColorRepository extends CrudRepository<HairColor, Long> {

    HairColor findByColor(String hairColor);

    List<HairColor> findAll();
}
