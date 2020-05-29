package com.example.PRI.repositories.character;

import com.example.PRI.entities.character.Prediction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PredictionRepository extends CrudRepository<Prediction, Long> {

    Prediction findByText(String prediction);

    List<Prediction> findAll();
}
