package com.example.PRI.services.character.generator;

import com.example.PRI.entities.character.Character;
import com.example.PRI.entities.character.Prediction;
import com.example.PRI.services.GeneralService;
import com.example.PRI.services.character.PredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Random;

@Service
public class PredictionGenerator extends GeneralService {


    @Autowired
    PredictionService predictionService;


    public Map<String, String> generatePrediction(Character character) {

        if(new Random().nextDouble() < 0.8){
            List<Prediction> predictionList = predictionService.findAll();
            character.setPrediction(predictionList.get(new Random().nextInt(predictionList.size())));
        }
        return new HashMap<>();
    }
}
