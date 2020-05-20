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

    public Statistics save(Statistics stats) {
        Statistics statsNew = statisticsRepository.findByWeaponSkillAndBallisticSkillAndStrengthAndToughnessAndAgilityAndIntelligenceAndWillPowerAndFellowshipAndAttacksAndWoundsAndMagicAndMovement
                (stats.getWeaponSkill(),stats.getBallisticSkill(),stats.getStrength(),stats.getToughness(), stats.getAgility(),stats.getIntelligence()
                        ,stats.getWillPower(),stats.getFellowship(),stats.getAttacks(),stats.getWounds(),stats.getMagic(),stats.getMovement());
        if (statsNew != null) stats.setId(statsNew.getId());
        statisticsRepository.save(stats);
        return stats;
    }

}
