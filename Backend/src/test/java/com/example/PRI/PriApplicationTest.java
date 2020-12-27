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

    @Autowired
    EyeColorGenerator eyeColorGenerator;

    @Autowired
    SurnameGenerator surnameGenerator;

    RaceGenerator raceGenerator = new RaceGenerator();
    CharacterBuilder characterBuilder = new CharacterBuilder();

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

    @Test
    public void surnameSeedCheck(){
        Character c1 = characterBuilder.buildRace(raceGenerator).buildSurname(surnameGenerator).getCharacter();
        Character c2 = characterBuilder.buildRace(raceGenerator).buildSurname(surnameGenerator).getCharacter();

        assertEquals(c1,c2);
    }


    @Test
    public void baseStatsSeedCheck(){
        StatisticsGenerator statisticsGenerator = new StatisticsGenerator();
        Character c1 = characterBuilder.buildBaseStats(statisticsGenerator).getCharacter();
        Character c2 = characterBuilder.buildBaseStats(statisticsGenerator).getCharacter();

        assertEquals(c1,c2);
    }

    @Test
    public void weightSeedCheck(){
        WeightGenerator weightGenerator = new WeightGenerator();
        Character c1 = characterBuilder.buildRace(raceGenerator).buildWeight(weightGenerator).getCharacter();
        Character c2 = characterBuilder.buildRace(raceGenerator).buildWeight(weightGenerator).getCharacter();

        assertEquals(c1,c2);
    }


    @Test
    public void heightSeedCheck(){
        HeightGenerator heightGenerator = new HeightGenerator();
        Character c1 = characterBuilder.buildRace(raceGenerator).buildHeight(heightGenerator).getCharacter();
        Character c2 = characterBuilder.buildRace(raceGenerator).buildHeight(heightGenerator).getCharacter();

        assertEquals(c1,c2);
    }


    @Test
    public void eyeColorSeedCheck(){
        Character c1 = characterBuilder.buildRace(raceGenerator).buildEyeColor(eyeColorGenerator).getCharacter();
        Character c2 = characterBuilder.buildRace(raceGenerator).buildEyeColor(eyeColorGenerator).getCharacter();

        assertEquals(c1,c2);
    }


    @Test
    public void hairColorSeedCheck(){
        RaceGenerator raceGenerator = new RaceGenerator();
        Character c1 = characterBuilder.buildRace(raceGenerator).buildHairColor(hairColorGenerator).getCharacter();
        Character c2 = characterBuilder.buildRace(raceGenerator).buildHairColor(hairColorGenerator).getCharacter();

        assertEquals(c1,c2);
    }

    @Test
    public void birthDateSeedCheck(){
        BirthDateGenerator birthDateGenerator = new BirthDateGenerator();
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
    public void CareerSeedCheck(){
        StatisticsGenerator statisticsGenerator = new StatisticsGenerator();
        Character c1 = characterBuilder.buildBaseStats(statisticsGenerator).buildCareers(careerGenerator).getCharacter();
        Character c2 = characterBuilder.buildBaseStats(statisticsGenerator).buildCareers(careerGenerator).getCharacter();

        assertEquals(c1,c2);
    }

}