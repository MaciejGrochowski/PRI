package com.example.PRI.services.character;

import com.example.PRI.entities.character.Apperance;
import com.example.PRI.entities.character.Career;
import com.example.PRI.repositories.character.ApperanceRepository;
import com.example.PRI.services.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.channels.AcceptPendingException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApperanceService extends GeneralService {

    @Autowired
    ApperanceRepository apperanceRepository;

    public void save(Apperance app) {
        apperanceRepository.save(app);
    }

    public List<String> getAllNames() {
        return apperanceRepository.findAll().stream().map(Apperance::getName).distinct().collect(Collectors.toList());
    }


    public List<Apperance> findByNameIn(List<String> apperanceListString) {
        return apperanceRepository.findByNameIn(apperanceListString);
    }
}
