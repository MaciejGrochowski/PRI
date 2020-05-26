package com.example.PRI.repositories.character;

import com.example.PRI.entities.character.CareerSkill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CareerSkillRepository extends CrudRepository<CareerSkill, Long> {
}
