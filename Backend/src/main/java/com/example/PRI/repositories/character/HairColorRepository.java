package com.example.PRI.repositories.character;

import com.example.PRI.entities.character.HairColor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HairColorRepository extends CrudRepository<HairColor, Long> {

}
