package com.example.PRI;

import com.example.PRI.entities.character.Character;
import com.example.PRI.services.character.generator.CharacterBirthPlaceGenerator;
import com.example.PRI.services.character.generator.CharacterBuilder;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CharacterGeneratorTest {

    private static int RANDOM_NUMBER;

    @Autowired
    CharacterBirthPlaceGenerator birthPlaceService;

    @BeforeAll
    public void prepareConsts(){
        this.RANDOM_NUMBER = 123456789;
    }

    @Test
    public void isBirthPlaceEqualsIfRandomServiceHasSameSeed() {
        long seed = RANDOM_NUMBER;
        Character c1 = new CharacterBuilder().initialize(seed).buildBirthPlace(birthPlaceService).getCharacter();
        Character c2 = new CharacterBuilder().initialize(seed).buildBirthPlace(birthPlaceService).getCharacter();

        assertEquals(c1,c2); //ToDo more tests.

    }
}