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

    @Autowired
    NameGenerator nameGenerator;

    @Autowired
    LivePlaceGenerator livePlaceGenerator;

    @Autowired
    ApperanceGenerator apperanceGenerator;


    RaceGenerator raceGenerator = new RaceGenerator();
    SexGenerator sexGenerator = new SexGenerator();
    ReligionGenerator religionGenerator = new ReligionGenerator();
    StatisticsGenerator statisticsGenerator = new StatisticsGenerator();
    CharacterBuilder characterBuilder = new CharacterBuilder();
    HeightGenerator heightGenerator = new HeightGenerator();
    WeightGenerator weightGenerator = new WeightGenerator();
    BirthDateGenerator birthDateGenerator = new BirthDateGenerator();


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
        Character c1 = characterBuilder.buildRace(raceGenerator).buildSex(sexGenerator).getCharacter();
        Character c2 = characterBuilder.buildRace(raceGenerator).buildSex(sexGenerator).getCharacter();

        assertEquals(c1,c2);
    }

    @Test
    public void surnameSeedCheck(){
        Character c1 = characterBuilder.buildRace(raceGenerator).buildSurname(surnameGenerator).getCharacter();
        Character c2 = characterBuilder.buildRace(raceGenerator).buildSurname(surnameGenerator).getCharacter();

        assertEquals(c1,c2);
    }

    @Test
    public void nameSeedCheck(){
        Character c1 = characterBuilder.buildRace(raceGenerator).buildSex(sexGenerator).buildName(nameGenerator).getCharacter();
        Character c2 = characterBuilder.buildRace(raceGenerator).buildSex(sexGenerator).buildName(nameGenerator).getCharacter();

        assertEquals(c1,c2);
    }


    @Test
    public void baseStatsSeedCheck(){
        Character c1 = characterBuilder.buildBaseStats(statisticsGenerator).getCharacter();
        Character c2 = characterBuilder.buildBaseStats(statisticsGenerator).getCharacter();

        assertEquals(c1,c2);
    }


    @Test
    public void birthDateSeedCheck(){
        Character c1 = characterBuilder.buildRace(raceGenerator).buildBirthDate(birthDateGenerator).getCharacter();
        Character c2 = characterBuilder.buildRace(raceGenerator).buildBirthDate(birthDateGenerator).getCharacter();

        assertEquals(c1,c2);
    }


    @Test
    public void weightSeedCheck(){
        Character c1 = characterBuilder.buildRace(raceGenerator).buildWeight(weightGenerator).getCharacter();
        Character c2 = characterBuilder.buildRace(raceGenerator).buildWeight(weightGenerator).getCharacter();

        assertEquals(c1,c2);
    }


    @Test
    public void heightSeedCheck(){
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
        Character c1 = characterBuilder.buildRace(raceGenerator).buildHairColor(hairColorGenerator).getCharacter();
        Character c2 = characterBuilder.buildRace(raceGenerator).buildHairColor(hairColorGenerator).getCharacter();

        assertEquals(c1,c2);
    }


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
    public void careerSeedCheck(){
        Character c1 = characterBuilder.buildBaseStats(statisticsGenerator).buildCareers(careerGenerator).getCharacter();
        Character c2 = characterBuilder.buildBaseStats(statisticsGenerator).buildCareers(careerGenerator).getCharacter();

        assertEquals(c1,c2);
    }


    @Test
    public void religionSeedCheck(){
        Character c1 = characterBuilder.buildRace(raceGenerator).buildBirthPlace(characterBirthPlaceGenerator).buildBaseStats(statisticsGenerator).
                buildCareers(careerGenerator).buildSex(sexGenerator).buildReligion(religionGenerator).getCharacter();
        Character c2 = characterBuilder.buildRace(raceGenerator).buildBirthPlace(characterBirthPlaceGenerator).buildBaseStats(statisticsGenerator).
                buildCareers(careerGenerator).buildSex(sexGenerator).buildReligion(religionGenerator).getCharacter();

        assertEquals(c1,c2);
    }

    
    @Test
    public void livePlaceSeedCheck(){
        Character c1 = characterBuilder.buildBirthPlace(characterBirthPlaceGenerator).buildBaseStats(statisticsGenerator).buildCareers(careerGenerator).
                buildLivePlace(livePlaceGenerator).buildCareers(careerGenerator).getCharacter();
        Character c2 = characterBuilder.buildBirthPlace(characterBirthPlaceGenerator).buildBaseStats(statisticsGenerator).buildCareers(careerGenerator).
                buildLivePlace(livePlaceGenerator).buildCareers(careerGenerator).getCharacter();

        assertEquals(c1,c2);
    }

    @Test
    public void apperanceSeedCheck(){
        Character c1 = characterBuilder.buildRace(raceGenerator).buildBirthPlace(characterBirthPlaceGenerator).
                buildBaseStats(statisticsGenerator).buildCareers(careerGenerator).buildSex(sexGenerator).buildReligion(religionGenerator).
                buildHeight(heightGenerator).buildWeight(weightGenerator).buildApperances(apperanceGenerator).getCharacter();
        Character c2 = characterBuilder.buildRace(raceGenerator).buildBirthPlace(characterBirthPlaceGenerator).
                buildBaseStats(statisticsGenerator).buildCareers(careerGenerator).buildSex(sexGenerator).buildReligion(religionGenerator).
                buildHeight(heightGenerator).buildWeight(weightGenerator).buildApperances(apperanceGenerator).getCharacter();

        assertEquals(c1,c2);
    }
}