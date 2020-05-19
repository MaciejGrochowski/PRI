package com.example.PRI.repositories.character;

import com.example.PRI.entities.character.EyeColor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EyeColorRepository extends CrudRepository<EyeColor, Long> {

    EyeColor findByColor(String eyeColor);

    List<EyeColor> findAll();
}
