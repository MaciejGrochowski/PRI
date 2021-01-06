package com.example.PRI.services.character.generator;

import com.example.PRI.entities.character.Apperance;
import com.example.PRI.entities.character.Character;
import com.example.PRI.entities.character.Emotion;
import com.example.PRI.exceptions.CharacterGenerationException;
import com.example.PRI.services.GeneralService;
import com.example.PRI.services.RandomService;
import com.example.PRI.services.character.EmotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmotionGenerator extends GeneralService {

    @Autowired
    EmotionService emotionService;

    RandomService randomService;

    public Map<String, String> generateEmotions(Character character, RandomService randomService, HashMap<String, String> properties) {
        this.randomService = randomService;
        Double emotionTypeRand = randomService.nextDouble();
        List<Emotion> emotions = emotionService.findAll();
        List<Emotion> characterEmotions = new ArrayList<>();

        for (Emotion emotion : emotions) {
            if (properties.containsKey(emotion.getName())) {
                double chance = Double.parseDouble(properties.get(emotion.getName()));
                if (emotionTypeRand < chance) {
                    if (characterEmotions.stream().noneMatch(d -> d.getType().equals(emotion.getType())))
                        characterEmotions.add(emotion);
                }
            }
        }
        int emotionCount = randomEmotionCount(emotionTypeRand);
        while (characterEmotions.size() < emotionCount) {
            Emotion newEmotion = getRandomEmotion(emotions);
            boolean noMatch = characterEmotions.stream().map(Emotion::getType).noneMatch(e -> e.equals(newEmotion.getType()));
            if (noMatch) characterEmotions.add(newEmotion);
        }
        if(characterEmotions.size() > emotionCount){
            Collections.shuffle(characterEmotions, randomService.getRandom());
            characterEmotions = characterEmotions.subList(0, emotionCount);
        }
        character.setDominatingEmotions(characterEmotions);
        return new HashMap<>();
    }

    private Emotion getRandomEmotion(List<Emotion> emotions) {
        Double emotionTypeRand = randomService.nextDouble();
        double sum = emotions.stream().mapToDouble(Emotion::getProbability).sum();
        double randomRoll = emotionTypeRand * sum;

        for(Emotion emotion: emotions){
            randomRoll -= emotion.getProbability();
            if(randomRoll<=0) return emotion;
        }
        throw new CharacterGenerationException("Stworzono null emocję!", new NullPointerException());
}

    private int randomEmotionCount(double randomRoll) {
        if (randomRoll < 0.01) return 0;
        if (randomRoll < 0.2) return 1;
        if (randomRoll < 0.5) return 2;
        if (randomRoll < 0.9) return 3;
        return 4;


    }

    public Map<String, String> getProperties(List<Emotion> emotion) {
        return new HashMap<>();
    }
}
