package com.example.PRI.repositories.character;

import com.example.PRI.entities.character.Statistics;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticsRepository extends CrudRepository<Statistics, Long> {

}
