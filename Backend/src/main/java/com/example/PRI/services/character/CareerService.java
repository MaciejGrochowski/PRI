package com.example.PRI.services.character;

import com.example.PRI.entities.character.Career;
import com.example.PRI.repositories.character.CareerRepository;
import com.example.PRI.services.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CareerService extends GeneralService {

    @Autowired
    CareerRepository careerRepository;

    public void save(Career career){
        careerRepository.save(career);
    }


    public List<String> getAllNames() {
        List<String> output;
        return careerRepository.findAll().stream().map(Career::getName).distinct().collect(Collectors.toList());
    }

    public List<Career> findByNameIn(List<String> currentCareersListString) {
        return careerRepository.findByNameIn(currentCareersListString);
    }
}
