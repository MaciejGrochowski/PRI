package com.example.PRI;

import com.example.PRI.entities.character.Character;
import com.example.PRI.entities.character.Statistics;
import com.example.PRI.enums.Race;
import com.example.PRI.enums.Sex;
import com.example.PRI.services.character.generator.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PriApplicationTest {

    @Autowired
    CharacterBirthPlaceGenerator characterBirthPlaceGenerator;

    @Autowired
    EmotionGenerator emotionGenerator;

    @Autowired
    PredictionGenerator predictionGenerator;

    @Autowired
    CareerGenerator careerGenerator;

    @Autowired
    HairColorGenerator hairColorGenerator;


    public CharacterBuilder characterBuilder = new CharacterBuilder();

        @Test
        public void contextLoads() {
    }

    @Before
    public void initBuilderSeed(){
        characterBuilder.initialize(1234L);
    }

    @Test
    public void birthPlaceSeedCheck(){
        Character c1 = characterBuilder.buildBirthPlace(characterBirthPlaceGenerator).getCharacter();
        Character c2 = characterBuilder.buildBirthPlace(characterBirthPlaceGenerator).getCharacter();

        assertEquals(c1,c2);
    }



        @Test
        public void raceSeedCheck(){
            RaceGenerator raceGenerator = new RaceGenerator();
            raceGenerator.generateRace(characterBuilder.getCharacter(),characterBuilder.getRandomService(),characterBuilder.getProperties());
            characterBuilder.buildRace(raceGenerator);

            assertEquals(Race.DWARF,characterBuilder.getCharacter().getRace());

        }

    @Test
    public void sexSeedCheck(){
        SexGenerator sexGenerator = new SexGenerator();
        sexGenerator.generateSex(characterBuilder.getCharacter(),characterBuilder.getRandomService(),characterBuilder.getProperties());
        characterBuilder.buildSex(sexGenerator);

        assertEquals(Sex.FEMALE,characterBuilder.getCharacter().getSex());

    }

    //TODO Tu nie działa
    @Disabled
    public void surnameSeedCheck(){
        RaceGenerator raceGenerator = new RaceGenerator();
        raceGenerator.generateRace(characterBuilder.getCharacter(),characterBuilder.getRandomService(),characterBuilder.getProperties());
        characterBuilder.buildRace(raceGenerator);

        SexGenerator sexGenerator = new SexGenerator();
        sexGenerator.generateSex(characterBuilder.getCharacter(),characterBuilder.getRandomService(),characterBuilder.getProperties());
        characterBuilder.buildSex(sexGenerator);

        SurnameGenerator surnameGenerator = new SurnameGenerator();
        surnameGenerator.generateSurname(characterBuilder.getCharacter(),characterBuilder.getRandomService(),characterBuilder.getProperties());
        characterBuilder.buildSurname(surnameGenerator);

        System.out.println(characterBuilder.getCharacter().getRace());

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

        StatisticsGenerator statisticsGenerator = new StatisticsGenerator();
        statisticsGenerator.generateBaseStats(characterBuilder.getCharacter(),characterBuilder.getRandomService(),characterBuilder.getProperties());
        characterBuilder.buildBaseStats(statisticsGenerator);

        assertEquals(testStats,characterBuilder.getCharacter().getBaseStats());
    }

    @Test
    public void weightSeedCheck(){
        RaceGenerator raceGenerator = new RaceGenerator();
        raceGenerator.generateRace(characterBuilder.getCharacter(),characterBuilder.getRandomService(),characterBuilder.getProperties());
        characterBuilder.buildRace(raceGenerator);

        SexGenerator sexGenerator = new SexGenerator();
        sexGenerator.generateSex(characterBuilder.getCharacter(),characterBuilder.getRandomService(),characterBuilder.getProperties());
        characterBuilder.buildSex(sexGenerator);

        WeightGenerator weightGenerator = new WeightGenerator();
        weightGenerator.generateWeight(characterBuilder.getCharacter(),characterBuilder.getRandomService(),characterBuilder.getProperties());
        characterBuilder.buildWeight(weightGenerator);

        assertEquals(59,characterBuilder.getCharacter().getWeight().intValue());
    }


    @Test
    public void heightSeedCheck(){
        RaceGenerator raceGenerator = new RaceGenerator();
        raceGenerator.generateRace(characterBuilder.getCharacter(),characterBuilder.getRandomService(),characterBuilder.getProperties());
        characterBuilder.buildRace(raceGenerator);

        SexGenerator sexGenerator = new SexGenerator();
        sexGenerator.generateSex(characterBuilder.getCharacter(),characterBuilder.getRandomService(),characterBuilder.getProperties());
        characterBuilder.buildSex(sexGenerator);

        HeightGenerator heightGenerator = new HeightGenerator();
        heightGenerator.generateHeight(characterBuilder.getCharacter(),characterBuilder.getRandomService(),characterBuilder.getProperties());
        characterBuilder.buildHeight(heightGenerator);

        assertEquals(142,characterBuilder.getCharacter().getHeight().intValue());
    }

    //TODO findall daje null pointer
    @Disabled
    public void eyeColorSeedCheck(){
        RaceGenerator raceGenerator = new RaceGenerator();
        raceGenerator.generateRace(characterBuilder.getCharacter(),characterBuilder.getRandomService(),characterBuilder.getProperties());
        characterBuilder.buildRace(raceGenerator);

        EyeColorGenerator eyeColorGenerator = new EyeColorGenerator();
        eyeColorGenerator.generateEyeColor(characterBuilder.getCharacter(),characterBuilder.getRandomService(),characterBuilder.getProperties());
        characterBuilder.buildEyeColor(eyeColorGenerator);

        System.out.println(characterBuilder.getCharacter().getEyeColor());
    }

    //TODO to samo co wyżej
    @Disabled
    public void hairColorSeedCheck(){
        RaceGenerator raceGenerator = new RaceGenerator();
        Character c1 = characterBuilder.buildRace(raceGenerator).buildHairColor(hairColorGenerator).getCharacter();
        Character c2 = characterBuilder.buildRace(raceGenerator).buildHairColor(hairColorGenerator).getCharacter();
    }

    @Test
    public void birthDateSeedCheck(){
        BirthDateGenerator birthDateGenerator = new BirthDateGenerator();
        RaceGenerator raceGenerator = new RaceGenerator();
        Character c1 = characterBuilder.buildRace(raceGenerator).buildBirthDate(birthDateGenerator).getCharacter();
        Character c2 = characterBuilder.buildRace(raceGenerator).buildBirthDate(birthDateGenerator).getCharacter();

        assertEquals(c1,c2);
    }


    //TODO to samo co wyżej
    @Test
    public void emotionSeedCheck(){
        Character c1 = characterBuilder.buildEmotions(emotionGenerator).getCharacter();
        Character c2 = characterBuilder.buildEmotions(emotionGenerator).getCharacter();

        assertEquals(c1,c2);
    }

    @Test
    public void predictionSeedCheck(){
        Character c1 = characterBuilder.buildPrediction(predictionGenerator).getCharacter();
        Character c2 = characterBuilder.buildPrediction(predictionGenerator).getCharacter();

        assertEquals(c1,c2);
    }


    @Test
    public void currentCareerSeedCheck(){
        StatisticsGenerator statisticsGenerator = new StatisticsGenerator();

        Character c1 = characterBuilder.buildBaseStats(statisticsGenerator).buildCareers(careerGenerator).getCharacter();
        Character c2 = characterBuilder.buildBaseStats(statisticsGenerator).buildCareers(careerGenerator).getCharacter();

        assertEquals(c1,c2);
    }

}