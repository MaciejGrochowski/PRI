package com.example.PRI.repositories.character;

import com.example.PRI.entities.character.Career;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CareerRepository extends CrudRepository<Career, Long> {

}
