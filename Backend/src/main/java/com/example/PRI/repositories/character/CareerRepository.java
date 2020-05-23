package com.example.PRI.repositories.character;

import com.example.PRI.entities.character.Career;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CareerRepository extends CrudRepository<Career, Long> {

    List<Career> findAll();

    List<Career> findByNameIn(List<String> currentCareersListString);

    Career findByName(String name);

}
