package com.example.PRI.repositories.character;

import com.example.PRI.entities.character.EyeColor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EyeColorRepository extends CrudRepository<EyeColor, Long> {

}
