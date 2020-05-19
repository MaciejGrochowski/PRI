package com.example.PRI.services.character.generator;

import com.example.PRI.converters.CharacterGeneratorConverter;
import com.example.PRI.dtos.characters.CharacterInputDto;
import com.example.PRI.enums.Race;
import com.example.PRI.services.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.PRI.entities.character.Character;

import java.util.HashMap;

@Service
public class CharacterGenerator extends GeneralService {

    @Autowired
    private CharacterBirthPlaceGenerator characterBirthPlaceGenerator;

    @Autowired
    private SurnameGenerator surnameGenerator;

    @Autowired
    private EyeColorGenerator eyeColorGenerator;

    public Character generateFullCharacter(){

        CharacterBuilder characterBuilder = new CharacterBuilder();
        characterBuilder.initialize()
                .buildBirthPlace(characterBirthPlaceGenerator)
                .buildRace(new RaceGenerator())
                .buildSex(new SexGenerator())
                .buildSurname(surnameGenerator)
                .buildBaseStats(new StatisticsGenerator())
                .buildHeight(new HeightGenerator())
                .buildWeight(new WeightGenerator())
                .buildEyeColor(eyeColorGenerator)
        ;

        Character generated = characterBuilder.getCharacter();

        HashMap<String, String> tmp = characterBuilder.getProperties();

        return characterBuilder.getCharacter();
    }


    public Integer save(CharacterInputDto characterInputDto) {
        Character character = CharacterGeneratorConverter.convert(characterInputDto);


        return 0;
    }
}
