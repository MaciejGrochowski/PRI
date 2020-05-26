package com.example.PRI.repositories.character;

import com.example.PRI.entities.character.CareerTalent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CareerTalentRepository extends CrudRepository<CareerTalent, Long> {
}
