package com.example.PRI.services.character;

import com.example.PRI.entities.character.Career;
import com.example.PRI.entities.character.CareerSkill;
import com.example.PRI.repositories.character.CareerSkillRepository;
import com.example.PRI.services.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CareerSkillService extends GeneralService {

    @Autowired
    CareerSkillRepository careerSkillRepository;

    public void save(CareerSkill careerSkill){
        careerSkillRepository.save(careerSkill);
    }

    public List<CareerSkill> findByCareer(Career career) {
        return careerSkillRepository.findByCareer(career);
    }
}
