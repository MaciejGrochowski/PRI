package com.example.PRI.services.character;

import com.example.PRI.entities.character.Prediction;
import com.example.PRI.repositories.character.PredictionRepository;
import com.example.PRI.services.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PredictionService extends GeneralService {

    @Autowired
    PredictionRepository predictionRepository;

    public Optional<Prediction> findByText(String prediction) {
        return Optional.ofNullable(predictionRepository.findByText(prediction));
    }
}
