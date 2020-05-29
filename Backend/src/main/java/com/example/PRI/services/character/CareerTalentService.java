package com.example.PRI.services.character;

import com.example.PRI.entities.character.Career;
import com.example.PRI.entities.character.CareerTalent;
import com.example.PRI.repositories.character.CareerTalentRepository;
import com.example.PRI.services.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CareerTalentService extends GeneralService {

    @Autowired
    CareerTalentRepository careerTalentRepository;

    public void save(CareerTalent careerTalent){
        careerTalentRepository.save(careerTalent);
    }

    public List<CareerTalent> findByCareer(Career career){
        return careerTalentRepository.findByCareer(career);
    }
}
