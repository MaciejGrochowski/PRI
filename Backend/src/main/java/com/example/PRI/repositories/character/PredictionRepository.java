package com.example.PRI.repositories.character;

import com.example.PRI.entities.character.Prediction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PredictionRepository extends CrudRepository<Prediction, Long> {

}
