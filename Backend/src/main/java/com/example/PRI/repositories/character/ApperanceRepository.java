package com.example.PRI.repositories.character;

import com.example.PRI.entities.character.Apperance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApperanceRepository extends CrudRepository<Apperance, Long> {

    List<Apperance> findByNameIn(List<String> apperanceListString);

    List<Apperance> findAll();

    Apperance findByName(String name);
}
