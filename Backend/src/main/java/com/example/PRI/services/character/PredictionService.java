package com.example.PRI.services.character;

import com.example.PRI.entities.character.Prediction;
import com.example.PRI.repositories.character.PredictionRepository;
import com.example.PRI.services.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class PredictionService extends GeneralService {

    @Autowired
    PredictionRepository predictionRepository;

    public void save(Prediction pre) {
        Prediction predict = predictionRepository.findByText(pre.getText());
        if(predict != null) pre.setId(predict.getId());
        predictionRepository.save(pre);}

    public Optional<Prediction> findByText(String prediction) {
        return Optional.ofNullable(predictionRepository.findByText(prediction));
    }

    public List<Prediction> findAll() {
        return predictionRepository.findAll();
    }
}
