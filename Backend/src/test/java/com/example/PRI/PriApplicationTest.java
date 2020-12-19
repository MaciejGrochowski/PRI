package com.example.PRI;

import com.example.PRI.entities.character.Character;
import com.example.PRI.entities.character.Statistics;
import com.example.PRI.enums.Race;
import com.example.PRI.enums.Sex;
import com.example.PRI.services.character.generator.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PriApplicationTest {

        @Test
        public void contextLoads() {
    }

        @Test
        public void raceSeedCheck(){
            CharacterBuilder characterBuilder = new CharacterBuilder();
            characterBuilder.initialize(1234L);
            RaceGenerator raceGenerator = new RaceGenerator();
            raceGenerator.generateRace(characterBuilder.getCharacter(),characterBuilder.getRandomService(),characterBuilder.getProperties());
            characterBuilder.buildRace(raceGenerator);

            assertEquals(Race.DWARF,characterBuilder.getCharacter().getRace());

        }

    @Test
    public void sexSeedCheck(){
        CharacterBuilder characterBuilder = new CharacterBuilder();
        characterBuilder.initialize(1234L);
        SexGenerator sexGenerator = new SexGenerator();
        sexGenerator.generateSex(characterBuilder.getCharacter(),characterBuilder.getRandomService(),characterBuilder.getProperties());
        characterBuilder.buildSex(sexGenerator);

        assertEquals(Sex.FEMALE,characterBuilder.getCharacter().getSex());

    }

    //TODO dla 143L (race = HALFLING) wywala się nullpointer || 1234L (race = HUMAN) działa
    @Test
    public void surnameSeedCheck(){
        CharacterBuilder characterBuilder = new CharacterBuilder();
        characterBuilder.initialize(1234L);
        RaceGenerator raceGenerator = new RaceGenerator();
        SurnameGenerator surnameGenerator = new SurnameGenerator();
        raceGenerator.generateRace(characterBuilder.getCharacter(),characterBuilder.getRandomService(),characterBuilder.getProperties());
        surnameGenerator.generateSurname(characterBuilder.getCharacter(),characterBuilder.getRandomService(),characterBuilder.getProperties());
        characterBuilder.buildRace(raceGenerator);
        System.out.println(characterBuilder.getCharacter().getRace());
        characterBuilder.buildSurname(surnameGenerator);

        assertNull(characterBuilder.getCharacter().getSurname());
    }


    @Test
    public void baseStatsSeedCheck(){
        Statistics testStats = new Statistics();
        testStats.setWeaponSkill(31);
        testStats.setBallisticSkill(33);
        testStats.setStrength(38);
        testStats.setToughness(36);
        testStats.setAgility(37);
        testStats.setIntelligence(27);
        testStats.setWillPower(36);
        testStats.setWillPower(36);
        testStats.setFellowship(30);
        testStats.setAttacks(1);
        testStats.setWounds(12);
        testStats.setMagic(0);
        testStats.setMovement(4);

        CharacterBuilder characterBuilder = new CharacterBuilder();
        characterBuilder.initialize(1234L);
        StatisticsGenerator statisticsGenerator = new StatisticsGenerator();
        statisticsGenerator.generateBaseStats(characterBuilder.getCharacter(),characterBuilder.getRandomService(),characterBuilder.getProperties());
        characterBuilder.buildBaseStats(statisticsGenerator);

        assertEquals(testStats,characterBuilder.getCharacter().getBaseStats());
    }
}