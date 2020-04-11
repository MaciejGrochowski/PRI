package com.example.PRI.repositories.character;

import com.example.PRI.entities.character.Apperance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApperanceRepository extends CrudRepository<Apperance, Long> {

}
