package com.example.PRI.repositories.character;

import com.example.PRI.entities.character.Career;
import com.example.PRI.entities.character.CareerTalent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CareerTalentRepository extends CrudRepository<CareerTalent, Long> {
    List<CareerTalent> findByCareer(Career career);
}
