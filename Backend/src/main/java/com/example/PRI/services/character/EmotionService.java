package com.example.PRI.services.character;

import com.example.PRI.entities.character.Emotion;
import com.example.PRI.repositories.character.EmotionRepository;
import com.example.PRI.services.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmotionService extends GeneralService {

    @Autowired
    EmotionRepository emotionRepository;

    public void save(Emotion e){ emotionRepository.save(e);}

    public List<Emotion> findByNameIn(List<String> dominatingEmotionsListString) {
        return emotionRepository.findByNameIn(dominatingEmotionsListString);
    }
}
