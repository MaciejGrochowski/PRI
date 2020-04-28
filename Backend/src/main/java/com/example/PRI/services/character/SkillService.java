package com.example.PRI.services.character;

import com.example.PRI.entities.character.Skill;
import com.example.PRI.repositories.character.SkillRepository;
import com.example.PRI.services.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService extends GeneralService {

    @Autowired
    SkillRepository skillRepository;

    public List<Skill> findByNameIn(List<String> skillsListString) {
        return skillRepository.findByNameIn(skillsListString);
    }
}
