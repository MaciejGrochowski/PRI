package com.example.PRI.services.character.generator;

import com.example.PRI.entities.character.Character;
import com.example.PRI.entities.character.Emotion;
import com.example.PRI.services.GeneralService;
import com.example.PRI.services.character.EmotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmotionGenerator extends GeneralService {

    @Autowired
    EmotionService emotionService;

    public Map<String, String> generateEmotions(Character character, HashMap<String, String> properties) {
        Map<String, String> output = new HashMap<>();
        List<Emotion> emotions = emotionService.findAll();
        Random rand = new Random();
        List<Emotion> characterEmotions = new ArrayList<>();

        for (Emotion emotion : emotions) {
            if (properties.containsKey(emotion.getName())) {
                double chance = Double.parseDouble(properties.get(emotion.getName()));
                if (rand.nextDouble() < chance) {
                    if (characterEmotions.stream().noneMatch(d -> d.getType().equals(emotion.getType())))
                        characterEmotions.add(emotion);
                }
            }
        }
        int emotionCount = randomEmotionCount(rand.nextDouble());
        while (characterEmotions.size() < emotionCount) {
            Emotion newEmotion = emotions.get(rand.nextInt(emotions.size()));
            boolean noMatch = characterEmotions.stream().map(Emotion::getType).noneMatch(e -> e.equals(newEmotion.getType()));
            if (noMatch) characterEmotions.add(newEmotion);
        }
        character.setDominatingEmotions(characterEmotions);
        return output;
    }

    private int randomEmotionCount(double randomRoll) {
        if (randomRoll < 0.01) return 0;
        if (randomRoll < 0.2) return 1;
        if (randomRoll < 0.5) return 2;
        if (randomRoll < 0.9) return 3;
        return 4;


    }
}
