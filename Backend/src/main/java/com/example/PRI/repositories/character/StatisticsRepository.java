package com.example.PRI.repositories.character;

import com.example.PRI.entities.ImperialDate;
import com.example.PRI.entities.character.Statistics;
import com.example.PRI.enums.Month;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticsRepository extends CrudRepository<Statistics, Long> {

    Statistics findByWeaponSkillAndBallisticSkillAndStrengthAndToughnessAndAgilityAndIntelligenceAndWillPowerAndFellowshipAndAttacksAndWoundsAndMagicAndMovement
           (Integer  weaponSkill,Integer  ballisticSkill,Integer  strength,Integer  toughness,Integer  agility,Integer  intelligence,
           Integer  willPower,Integer  fellowship,Integer  attacks,Integer  wounds,Integer  magic,Integer  movement);
}
