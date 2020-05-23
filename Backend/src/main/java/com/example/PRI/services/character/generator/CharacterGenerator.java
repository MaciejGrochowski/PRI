package com.example.PRI.services.character.generator;

import com.example.PRI.converters.CharacterGeneratorConverter;
import com.example.PRI.dtos.characters.CharacterInputDto;
import com.example.PRI.entities.ImperialDate;
import com.example.PRI.entities.Place;
import com.example.PRI.entities.character.*;
import com.example.PRI.entities.character.Character;
import com.example.PRI.enums.Month;
import com.example.PRI.enums.Race;
import com.example.PRI.enums.Religion;
import com.example.PRI.enums.Sex;
import com.example.PRI.services.GeneralService;
import com.example.PRI.services.ImperialDateService;
import com.example.PRI.services.PlaceService;
import com.example.PRI.services.character.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.channels.AcceptPendingException;
import java.util.*;

@Service
public class CharacterGenerator extends GeneralService {

    @Autowired
    private CharacterBirthPlaceGenerator characterBirthPlaceGenerator;

    @Autowired
    private SurnameGenerator surnameGenerator;

    @Autowired
    private EyeColorGenerator eyeColorGenerator;

    @Autowired
    private EmotionGenerator emotionGenerator;

//    @Autowired
//    private BirthDateGenerator birthDateGenerator;

    @Autowired
    private HairColorGenerator hairColorGenerator;

    @Autowired
    private CareerService careerService;

    @Autowired
    private NameService nameService;

    @Autowired
    private CharacterService characterService;

    @Autowired
    private SurnameService surnameService;

    @Autowired
    private PredictionService predictionService;

    @Autowired
    private HairColorService hairColorService;

    @Autowired
    private EyeColorService eyeColorService;

    @Autowired
    private PlaceService placeService;

    @Autowired
    private ImperialDateService imperialDateService;

    @Autowired
    private PersonalityService personalityService;

    @Autowired
    private ApperanceService apperanceService;

    @Autowired
    private SkillService skillService;

    @Autowired
    private TalentService talentService;

    @Autowired
    private EmotionService emotionService;

    @Autowired
    private CharacterSaveService characterSaveService;


    public Character generateFullCharacter(){

        CharacterBuilder characterBuilder = new CharacterBuilder();
        characterBuilder.initialize()
                .buildBirthPlace(characterBirthPlaceGenerator)
                .buildRace(new RaceGenerator())
                .buildSex(new SexGenerator())
                .buildSurname(surnameGenerator)
                .buildBaseStats(new StatisticsGenerator())
                .buildBirthDate(new BirthDateGenerator())
                .buildHeight(new HeightGenerator())
                .buildWeight(new WeightGenerator())
                .buildEyeColor(eyeColorGenerator)
                .buildHairColor(hairColorGenerator)
                .buildEmotions(emotionGenerator)
        ;

        Character generated = characterBuilder.getCharacter();

        HashMap<String, String> tmp = characterBuilder.getProperties();

        return characterBuilder.getCharacter();
    }


    @Transactional
    public long save(CharacterInputDto characterInputDto) {
        Character character = new Character();
        character.setName(characterSaveService.nameConvert(characterInputDto.getName()));
        character.setSurname(characterSaveService.surnameConvert(characterInputDto.getSurname()));
        character.setPrediction(characterSaveService.predictionConvert(characterInputDto.getPrediction()));
        character.setCurrentCareer(characterSaveService.currentCareerConvert(characterInputDto.getCurrentCareer()));
        character.setPreviousCareers(characterSaveService.previousCareersConvert(characterInputDto.getPreviousCareers(),characterInputDto.getCurrentCareer()));
        character.setHairColor(characterSaveService.hairColorConverter(characterInputDto.getHairColor()));
        character.setEyeColor(characterSaveService.eyeColorConverter(characterInputDto.getEyeColor()));
        character.setBirthPlace(characterSaveService.bornPlaceConverter(characterInputDto.getBirthPlace()));
        character.setLivePlace(characterSaveService.livePlaceConverter(characterInputDto.getLivePlace()));
        character.setSex(characterSaveService.sexConverter(characterInputDto.getSex()));
        character.setRace(characterSaveService.raceConverter(characterInputDto.getRace()));
        character.setReligion(characterSaveService.religionConverter(characterInputDto.getReligion()));
        character.setBirthDate(characterSaveService.imperialDateConverter(characterInputDto.getDayOfBirth(), characterInputDto.getMonthOfBirth(),characterInputDto.getYearOfBirth()));
        character.setHeight(characterSaveService.heightConverter(characterInputDto.getHeight()));
        character.setWeight(characterSaveService.weightConverter(characterInputDto.getWeight()));
        character.setPersonality(characterSaveService.personalityListConvert(characterInputDto.getPersonality()));
        character.setApperance(characterSaveService.apperanceConvert(characterInputDto.getApperance()));
        character.setSkills(characterSaveService.skillsConvert(characterInputDto.getSkills()));
        character.setTalents(characterSaveService.talentsConvert(characterInputDto.getTalents()));
        character.setDominatingEmotions(characterSaveService.dominantingEmotionConvert(characterInputDto.getDominatingEmotions()));
        character.setEndWeaponSkills(characterSaveService.endWeaponSkillConvert(characterInputDto.getEndWeaponSkills()));
        /*characterSaveService.endBallisticSkillsConvert(characterInputDto.getEndBallisticSkills(),character);
        characterSaveService.endStrengthConvert(characterInputDto.getEndStrength(),character);
        characterSaveService.endToughnessConvert(characterInputDto.getEndToughness(),character);
        characterSaveService.endAgilityConvert(characterInputDto.getEndAgility(),character);
        characterSaveService.endIntelligenceConvert(characterInputDto.getEndIntelligence(),character);
        characterSaveService.endWillPowerConvert(characterInputDto.getEndWillPower(),character);
        characterSaveService.endFellowshipConvert(characterInputDto.getEndFellowship(),character);
        characterSaveService.endAttacksConvert(characterInputDto.getEndAttacks(),character);
        characterSaveService.endWoundsConvert(characterInputDto.getEndWounds(),character);
        characterSaveService.endMovementConvert(characterInputDto.getEndMovement(),character);
        characterSaveService.endMagicConvert(characterInputDto.getEndMagic(),character);
        characterSaveService.baseStatisticsConvert(characterInputDto,character);*/
        characterService.save(character);

        return character.getId();
    }
}
