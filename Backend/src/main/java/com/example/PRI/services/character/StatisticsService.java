package com.example.PRI.services.character;


import com.example.PRI.entities.character.Statistics;
import com.example.PRI.repositories.character.StatisticsRepository;
import com.example.PRI.services.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticsService extends GeneralService {


    @Autowired
    StatisticsRepository statisticsRepository;

    public void save(Statistics stats) {
        statisticsRepository.save(stats);
    }
}
