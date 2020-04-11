package com.example.PRI.services.character;

import com.example.PRI.entities.character.Career;
import com.example.PRI.repositories.character.CareerRepository;
import com.example.PRI.services.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CareerService extends GeneralService {

    @Autowired
    CareerRepository careerRepository;

    public void save(Career career){
        careerRepository.save(career);
    }


}
