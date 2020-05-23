package com.example.PRI.services.character;

import com.example.PRI.entities.character.Career;
import com.example.PRI.entities.character.Personality;
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
        Career caree = careerRepository.findByName(career.getName());
        if(caree != null) career.setId(caree.getId());
        careerRepository.save(career);
    }


    public List<String> getAllNames() {
        return careerRepository.findAll().stream().map(Career::getName).distinct().collect(Collectors.toList());
    }

    public List<Career> findByNameIn(List<String> currentCareersListString) {
        return careerRepository.findByNameIn(currentCareersListString);
    }

    public Career findByName(String name) {
        return careerRepository.findByName(name);
    }
}
