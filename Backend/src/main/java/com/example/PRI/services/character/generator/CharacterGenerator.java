package com.example.PRI.services.character.generator;

import com.example.PRI.converters.CharacterConverter;
import com.example.PRI.dtos.characters.CharacterDetailsOutputDto;
import com.example.PRI.dtos.characters.CharacterInputDto;
import com.example.PRI.entities.character.Character;
import com.example.PRI.entities.character.Surname;
import com.example.PRI.enums.Race;
import com.example.PRI.enums.Sex;
import com.example.PRI.repositories.character.CharacterRepository;
import com.example.PRI.services.GeneralService;
import com.example.PRI.services.character.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private HairColorGenerator hairColorGenerator;

    @Autowired
    private CharacterService characterService;

    @Autowired
    private CareerGenerator careerGenerator;

    @Autowired
    private CharacterSaveService characterSaveService;

    @Autowired
    private PredictionGenerator predictionGenerator;

    @Autowired
    private NameGenerator nameGenerator;

    @Autowired
    private LivePlaceGenerator livePlaceGenerator;

    @Autowired
    private ApperanceGenerator apperanceGenerator;

    @Autowired
    private PersonalityGenerator personalityGenerator;

    @Autowired
    private TalentGenerator talentGenerator;

    @Autowired
    private SkillGenerator skillGenerator;

    public CharacterDetailsOutputDto generateCharacterDetails(){
        return CharacterConverter.convertDetails((generateFullCharacter()));
    }

    public Character generateOneAttribute(String attribute, Character character){
        CharacterBuilder characterBuilder = new CharacterBuilder();
        characterBuilder.initialize();

        if(attribute.equals("Miejsce urodzenia")) characterBuilder.buildBirthPlace(characterBirthPlaceGenerator);
        else if(character.getBirthPlace() != null) characterBuilder.buildBirthPlace(characterBirthPlaceGenerator, character.getBirthPlace());
        //ToDo Kasia inne atrybuty w ifach.

        return characterBuilder.getCharacter();
    }


    public Character generateFullCharacter(){

        CharacterBuilder characterBuilder = new CharacterBuilder();
        characterBuilder.initialize()
                .buildBirthPlace(characterBirthPlaceGenerator)
                .buildRace(new RaceGenerator())
                .buildSex(new SexGenerator())
                .buildSurname(surnameGenerator)
                .buildName(nameGenerator)
                .buildBaseStats(new StatisticsGenerator())
                .buildBirthDate(new BirthDateGenerator())
                .buildHeight(new HeightGenerator())
                .buildWeight(new WeightGenerator())
                .buildEyeColor(eyeColorGenerator)
                .buildHairColor(hairColorGenerator)
                .buildEmotions(emotionGenerator)
                .buildCareers(careerGenerator)
                .buildPrediction(predictionGenerator)
                .buildReligion(new ReligionGenerator())
                .buildLivePlace(livePlaceGenerator)
                .buildApperances(apperanceGenerator)
                .buildPersonalities(personalityGenerator)
                .buildTalents(talentGenerator)
                .buildCareerStatistics(new CareerStatisticsGenerator())
                .buildSkills(skillGenerator)
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
        character.setEndBallisticSkills(characterSaveService.endBallisticSkillsConvert(characterInputDto.getEndBallisticSkills()));
        character.setEndStrength(characterSaveService.endStrengthConvert(characterInputDto.getEndStrength()));
        character.setEndToughness(characterSaveService.endToughnessConvert(characterInputDto.getEndToughness()));
        character.setEndAgility(characterSaveService.endAgilityConvert(characterInputDto.getEndAgility()));
        character.setEndIntelligence(characterSaveService.endIntelligenceConvert(characterInputDto.getEndIntelligence()));
        character.setEndWillPower(characterSaveService.endWillPowerConvert(characterInputDto.getEndWillPower()));
        character.setEndFellowship(characterSaveService.endFellowshipConvert(characterInputDto.getEndFellowship()));
        character.setEndAttacks(characterSaveService.endAttacksConvert(characterInputDto.getEndAttacks()));
        character.setEndWounds(characterSaveService.endWoundsConvert(characterInputDto.getEndWounds()));
        character.setEndMovement(characterSaveService.endMovementConvert(characterInputDto.getEndMovement()));
        character.setEndMagic(characterSaveService.endMagicConvert(characterInputDto.getEndMagic()));
        character.setStarSign(characterSaveService.saveStarSign(character,character.getBirthDate()));
        character.setBaseStats(characterSaveService.baseStatisticsConvert(characterInputDto));
        characterService.save(character);

        return character.getId();
    }


    @Transactional
    public Character saveGenerate(CharacterInputDto characterInputDto){
        Character character = new Character();
        if(characterInputDto.getName() == null) character.setName(null);
        else character.setName(characterSaveService.nameConvert(characterInputDto.getName()));
        character.setSex(characterSaveService.sexConverter(characterInputDto.getSex()));
        character.setRace(characterSaveService.raceConverter(characterInputDto.getRace()));
        Surname surname = characterSaveService.surnameConvert(characterInputDto.getSurname());
        character.setSurname(prepareSurnameProperties(character.getRace(),surname,character.getSex()));
        character.setPrediction(characterSaveService.predictionConvert(characterInputDto.getPrediction()));
        if(characterInputDto.getCurrentCareer() == null) character.setCurrentCareer(null);
        else character.setCurrentCareer(characterSaveService.currentCareerConvert(characterInputDto.getCurrentCareer()));
        character.setPreviousCareers(characterSaveService.previousCareersConvert(characterInputDto.getPreviousCareers(),characterInputDto.getCurrentCareer()));
        character.setHairColor(characterSaveService.hairColorConverter(characterInputDto.getHairColor()));
        character.setEyeColor(characterSaveService.eyeColorConverter(characterInputDto.getEyeColor()));

        return character;
    }


    private Surname prepareSurnameProperties(Race race, Surname surname, Sex sex){
        if (race.equals(Race.HUMAN)) surname.setHuman(true);
        if (race.equals(Race.ELF)) surname.setElf(true);
        if (race.equals(Race.DWARF)) surname.setDwarf(true);
        if (race.equals(Race.HALFLING)) surname.setHalfling(true);

        if (sex.equals(Sex.MALE)) surname.setMale(true);
        if (sex.equals(Sex.FEMALE)) surname.setFemale(true);

        if (surname.getSurname().startsWith("von ")) surname.setGentry(true);
        return surname;
    }
}
