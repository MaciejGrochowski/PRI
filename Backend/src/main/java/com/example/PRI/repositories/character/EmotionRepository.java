package com.example.PRI.repositories.character;

import com.example.PRI.entities.character.Emotion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmotionRepository extends CrudRepository<Emotion, Long> {

    List<Emotion> findByNameIn(List<String> dominatingEmotionsListString);

    List<Emotion> findAll();

    Emotion findByName(String name);
}
