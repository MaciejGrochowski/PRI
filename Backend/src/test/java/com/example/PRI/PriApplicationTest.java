package com.example.PRI;

import com.example.PRI.entities.character.Character;
import com.example.PRI.enums.Race;
import com.example.PRI.enums.Sex;
import com.example.PRI.services.character.generator.CharacterBirthPlaceGenerator;
import com.example.PRI.services.character.generator.CharacterBuilder;
import com.example.PRI.services.character.generator.RaceGenerator;
import com.example.PRI.services.character.generator.SexGenerator;
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
}