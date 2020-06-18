package com.example.PRI.services.character.generator;

import com.example.PRI.converters.CharacterConverter;
import com.example.PRI.dtos.characters.CharacterDetailsOutputDto;
import com.example.PRI.dtos.characters.CharacterInputDto;
import com.example.PRI.entities.character.Career;
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

    public CharacterDetailsOutputDto generateAttribute(String attribute, CharacterInputDto character){
        Character c = this.saveGenerate(character);
        return CharacterConverter.convertDetails(generateOneAttribute(attribute, c));
    }

    public Character generateOneAttribute(String attribute, Character character){
        CharacterBuilder characterBuilder = new CharacterBuilder();
        characterBuilder.initialize();

        if(attribute.equals("miejsce-urodzenia")) characterBuilder.buildBirthPlace(characterBirthPlaceGenerator);
        else if(character.getBirthPlace() != null) characterBuilder.buildBirthPlace(characterBirthPlaceGenerator, character.getBirthPlace());

        if(attribute.equals("rasa")) characterBuilder.buildRace(new RaceGenerator());
        else if(character.getRace() != null) characterBuilder.buildRace(new RaceGenerator(), character.getRace());

        if(attribute.equals("płeć")) characterBuilder.buildSex(new SexGenerator());
        else if(character.getSex() != null) characterBuilder.buildSex(new SexGenerator(), character.getSex());

        if(attribute.equals("nazwisko")) {
            while(characterBuilder.getCharacter().getSurname()==null) {
                characterBuilder.buildSurname(surnameGenerator);
            }
        }
        else if(character.getSurname() != null) characterBuilder.buildSurname(surnameGenerator, character.getSurname());

        if(attribute.equals("imię")) characterBuilder.buildName(nameGenerator);
        else if(character.getName() != null) characterBuilder.buildName(nameGenerator, character.getName());

        if(attribute.equals("bazowe-statystyki")) characterBuilder.buildBaseStats(new StatisticsGenerator());
        else if(character.getBaseStats()!= null) characterBuilder.buildBaseStats(new StatisticsGenerator(), character.getBaseStats());

        if(attribute.equals("data-urodzenia")) characterBuilder.buildBirthDate(new BirthDateGenerator());
        else if (character.getBirthDate()!=null) characterBuilder.buildBirthDate(new BirthDateGenerator(), character.getBirthDate());

        if(attribute.equals("wzrost")) characterBuilder.buildHeight(new HeightGenerator());
        else if(character.getHeight()!=null) characterBuilder.buildHeight(new HeightGenerator(), character.getHeight());

        if(attribute.equals("waga")) characterBuilder.buildWeight(new WeightGenerator());
        else if(character.getWeight()!=null) characterBuilder.buildWeight(new WeightGenerator(), character.getWeight());

        if(attribute.equals("kolor-oczu")) characterBuilder.buildEyeColor(eyeColorGenerator);
        else if(character.getEyeColor()!=null) characterBuilder.buildEyeColor(eyeColorGenerator, character.getEyeColor());

        if(attribute.equals("kolor-włosów")) characterBuilder.buildHairColor((hairColorGenerator));
        else if (character.getHairColor()!=null) characterBuilder.buildHairColor(hairColorGenerator, character.getHairColor());

        if(attribute.equals("dominujące-emocje")) characterBuilder.buildEmotions(emotionGenerator);
        else if (character.getDominatingEmotions()!=null) characterBuilder.buildEmotions(emotionGenerator, character.getDominatingEmotions());

        if(attribute.equals("profesja")){
            if (character.getCurrentCareer() == null && character.getPreviousCareers() == null) characterBuilder.buildCareers(careerGenerator);
            if (character.getCurrentCareer() != null && character.getPreviousCareers() == null) characterBuilder.buildCareers(careerGenerator);
            if (character.getCurrentCareer() != null && character.getPreviousCareers() != null) characterBuilder.buildCareers(careerGenerator);
            if (character.getCurrentCareer() == null && character.getPreviousCareers() != null) characterBuilder.buildCurrentCareer(careerGenerator, character.getPreviousCareers());
        }
        else if (character.getCurrentCareer() != null) {
            List <Career> careers = new ArrayList();
            if(character.getPreviousCareers() != null) careers.addAll(character.getPreviousCareers());
            careers.add(character.getCurrentCareer());
            characterBuilder.buildCareers(careerGenerator, careers);
        }

        if(attribute.equals("przepowiednia")) {
            while(characterBuilder.getCharacter().getPrediction()==null)
            characterBuilder.buildPrediction(predictionGenerator);
        }
        else if (character.getPrediction()!=null) characterBuilder.buildPrediction(predictionGenerator, character.getPrediction());

        if(attribute.equals("religia")) characterBuilder.buildReligion(new ReligionGenerator());
        else if(character.getReligion() != null) characterBuilder.buildReligion(new ReligionGenerator(), character.getReligion());

        if(attribute.equals("miejsce-pobytu")) characterBuilder.buildLivePlace(livePlaceGenerator);
        else if(character.getLivePlace() != null) characterBuilder.buildLivePlace(livePlaceGenerator, character.getLivePlace());

        if(attribute.equals("cechy-wyglądu")) characterBuilder.buildApperances(apperanceGenerator);
        else if(character.getApperance()!= null) characterBuilder.buildApperances(apperanceGenerator, character.getApperance());

        if(attribute.equals("cechy-charakteru")) characterBuilder.buildPersonalities(personalityGenerator);
        else if(character.getPersonality() != null) characterBuilder.buildPersonalities(personalityGenerator, character.getPersonality());

        if(attribute.equals("zdolności")) characterBuilder.buildTalents(talentGenerator);
        else if(character.getTalents() != null) characterBuilder.buildTalents(talentGenerator, character.getTalents());

        if(attribute.equals("statystyki-końcowe")) characterBuilder.buildCareerStatistics(new CareerStatisticsGenerator());
        else characterBuilder.buildCareerStatistics(new CareerStatisticsGenerator(), character);

        if(attribute.equals("umiejętności")) characterBuilder.buildSkills(skillGenerator);
        else if(character.getSkills() != null) characterBuilder.buildSkills(skillGenerator, character.getSkills());

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
                .buildSkills(skillGenerator);

        return characterBuilder.getCharacter();
    }

    @Autowired
    StatisticsService statisticsService;


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
        statisticsService.save(character.getBaseStats());
        characterService.save(character);

        return character.getId();
    }


    @Transactional
    public Character saveGenerate(CharacterInputDto characterInputDto){
        Character character = new Character();
        if(characterInputDto.getName() != null && !characterInputDto.getName().equals("") && !characterSaveService.checkSpecialCharacter(characterInputDto.getName())) character.setName(characterSaveService.nameConvert(characterInputDto.getName()));
        if(characterInputDto.getSex() != null) character.setSex(characterSaveService.sexConverter(characterInputDto.getSex()));
        if(characterInputDto.getRace() != null) character.setRace(characterSaveService.raceConverter(characterInputDto.getRace()));
        if (characterInputDto.getSurname() != null && !characterSaveService.checkSpecialCharacter(characterInputDto.getSurname()))
        {
            Surname surname = characterSaveService.surnameConvert(characterInputDto.getSurname());
            character.setSurname(prepareSurnameProperties(character.getRace(), surname, character.getSex()));
        }
        character.setPrediction(characterSaveService.predictionConvert(characterInputDto.getPrediction()));
        if(characterInputDto.getCurrentCareer() != null) character.setCurrentCareer(characterSaveService.currentCareerConvert(characterInputDto.getCurrentCareer()));
        if(characterInputDto.getPreviousCareers() != null) character.setPreviousCareers(characterSaveService.previousCareersConvert(characterInputDto.getPreviousCareers(),characterInputDto.getCurrentCareer()));
        if(characterInputDto.getHairColor() != null && !characterInputDto.getHairColor().equals("")) character.setHairColor(characterSaveService.hairColorConverter(characterInputDto.getHairColor()));
        if(characterInputDto.getEyeColor() != null) character.setEyeColor(characterSaveService.eyeColorConverter(characterInputDto.getEyeColor()));
        if(characterInputDto.getApperance() != null) character.setApperance(characterSaveService.apperanceConvert(characterInputDto.getApperance()));
        if(characterInputDto.getDominatingEmotions() != null && characterSaveService.personalityOrAppearanceOrEmotionNumber(characterInputDto.getDominatingEmotions(), "emotion")) character.setDominatingEmotions(characterSaveService.dominantingEmotionConvert(characterInputDto.getDominatingEmotions()));
        if(characterInputDto.getLivePlace() != null) character.setLivePlace(characterSaveService.livePlaceConverter(characterInputDto.getLivePlace()));
        if(characterInputDto.getBirthPlace() != null) character.setBirthPlace(characterSaveService.bornPlaceConverter(characterInputDto.getBirthPlace()));
        if(characterInputDto.getHeight() != null && characterSaveService.checkNumber(characterInputDto.getHeight(), "height")) character.setHeight(characterSaveService.heightConverter(characterInputDto.getHeight()));
        if(characterInputDto.getWeight() != null && characterSaveService.checkNumber(characterInputDto.getWeight(), "weight")) character.setWeight(characterSaveService.weightConverter(characterInputDto.getWeight()));
        character.setPersonality(characterSaveService.personalityListConvert(characterInputDto.getPersonality()));
        character.setApperance(characterSaveService.apperanceConvert(characterInputDto.getApperance()));
        character.setSkills(characterSaveService.skillsConvert(characterInputDto.getSkills()));
        character.setTalents(characterSaveService.talentsConvert(characterInputDto.getTalents()));
        if(characterInputDto.getReligion() != null && !characterInputDto.getReligion().equals("")) character.setReligion(characterSaveService.religionConverter(characterInputDto.getReligion()));
        if (characterInputDto.getDayOfBirth() != null  && characterSaveService.checkNumber(characterInputDto.getDayOfBirth(), "day") && characterInputDto.getMonthOfBirth() != null
            && characterInputDto.getYearOfBirth() != null && characterSaveService.checkNumber(characterInputDto.getYearOfBirth(), "year")) character.setBirthDate(characterSaveService.imperialDateConverter(characterInputDto.getDayOfBirth(), characterInputDto.getMonthOfBirth(),characterInputDto.getYearOfBirth()));
        if (characterInputDto.getDayOfBirth() != null && characterSaveService.checkNumber(characterInputDto.getDayOfBirth(), "day") && characterInputDto.getMonthOfBirth() != null
            && characterInputDto.getYearOfBirth() != null && characterSaveService.checkNumber(characterInputDto.getYearOfBirth(), "year")) character.setStarSign(characterSaveService.saveStarSign(character,character.getBirthDate()));

        if (characterInputDto.getBaseWeaponSkills() != null && characterInputDto.getBaseBallisticSkills() != null &&
                characterInputDto.getBaseStrength() != null && characterInputDto.getBaseToughness() != null &&
                characterInputDto.getBaseAgility() != null && characterInputDto.getBaseIntelligence() != null &&
                characterInputDto.getBaseWillPower() != null && characterInputDto.getBaseFellowship() != null &&
                characterInputDto.getBaseAttacks() != null && characterInputDto.getBaseWounds() != null &&
                characterInputDto.getBaseMagic() != null && characterInputDto.getBaseMovement() != null
                && characterSaveService.baseStatisticCheck(characterInputDto)
        )        character.setBaseStats(characterSaveService.baseStatisticsConvert(characterInputDto));

        if (characterInputDto.getEndWeaponSkills() != null && characterInputDto.getEndBallisticSkills() != null &&
                characterInputDto.getEndStrength() != null && characterInputDto.getEndToughness() != null &&
                characterInputDto.getEndAgility() != null && characterInputDto.getEndIntelligence() != null &&
                characterInputDto.getEndWillPower() != null && characterInputDto.getEndFellowship() != null &&
                characterInputDto.getEndAttacks() != null && characterInputDto.getEndWounds() != null &&
                characterInputDto.getEndMagic() != null && characterInputDto.getEndMovement() != null
                && characterSaveService.endStatisticCheck(characterInputDto)
        ) {
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
        }
        return character;
    }


    private Surname prepareSurnameProperties(Race race, Surname surname, Sex sex){
        if(race == null || sex == null || surname.getSurname() == null) return null;
        if (race.equals(Race.HUMAN)) surname.setHuman(true);
        else if (race.equals(Race.ELF)) surname.setElf(true);
        else if (race.equals(Race.DWARF)) surname.setDwarf(true);
        else if (race.equals(Race.HALFLING)) surname.setHalfling(true);

        if (sex.equals(Sex.MALE)) surname.setMale(true);
        else if (sex.equals(Sex.FEMALE)) surname.setFemale(true);

        if (surname.getSurname().startsWith("von ")) surname.setGentry(true);
        return surname;
    }
}
