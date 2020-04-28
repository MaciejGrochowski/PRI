package com.example.PRI.repositories.character;

import com.example.PRI.entities.character.Skill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends CrudRepository<Skill, Long> {

    List<Skill> findByNameIn(List<String> skillsListString);
}
