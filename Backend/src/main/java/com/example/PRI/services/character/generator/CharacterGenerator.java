package com.example.PRI.services.character.generator;

import com.example.PRI.converters.CharacterConverter;
import com.example.PRI.dtos.characters.CharacterDetailsOutputDto;
import com.example.PRI.dtos.characters.CharacterInputDto;
import com.example.PRI.entities.UserOfApp;
import com.example.PRI.entities.character.Career;
import com.example.PRI.entities.character.Character;
import com.example.PRI.entities.character.Surname;
import com.example.PRI.enums.CharacterAttribute;
import com.example.PRI.enums.Race;
import com.example.PRI.enums.Sex;
import com.example.PRI.services.GeneralService;
import com.example.PRI.services.UserOfAppService;
import com.example.PRI.services.character.CharacterSaveService;
import com.example.PRI.services.character.CharacterService;
import com.example.PRI.services.character.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public CharacterDetailsOutputDto generateAttribute(String attribute, CharacterInputDto character) throws Exception {
        Character c = this.saveGenerate(character);
        return CharacterConverter.convertDetails(generateOneAttribute(attribute, c));
    }

    public Character generateOneAttribute(String attribute, Character character){
        CharacterBuilder characterBuilder = new CharacterBuilder();
        characterBuilder.initialize();



        if(attribute.equals(CharacterAttribute.BIRTHPLACE.getName())) return characterBuilder.buildBirthPlace(characterBirthPlaceGenerator).getCharacter();
        else if(character.getBirthPlace() != null) characterBuilder.buildBirthPlace(characterBirthPlaceGenerator, character.getBirthPlace());

        if(attribute.equals(CharacterAttribute.RACE.getName())) { //ToDo enumy zamiast stringów
            do {
                characterBuilder.buildRace(new RaceGenerator());
            }
            while(characterBuilder.getCharacter().getRace().equals(character.getRace()));
            return characterBuilder.getCharacter();
        }
        else if(character.getRace() != null) characterBuilder.buildRace(new RaceGenerator(), character.getRace());

        if(attribute.equals(CharacterAttribute.SEX.getName())) {
            do{
                characterBuilder.buildSex(new SexGenerator());
            }while(characterBuilder.getCharacter().getSex().equals(character.getSex()));
            return characterBuilder.buildSex(new SexGenerator()).getCharacter();
        }
        else if(character.getSex() != null) characterBuilder.buildSex(new SexGenerator(), character.getSex());

        if(attribute.equals(CharacterAttribute.SURNAME.getName())) {
            while(characterBuilder.getCharacter().getSurname()==null) {
                characterBuilder.buildSurname(surnameGenerator);
            }
            return characterBuilder.getCharacter();
        }
        else if(character.getSurname() != null) characterBuilder.buildSurname(surnameGenerator, character.getSurname());

        if(attribute.equals(CharacterAttribute.NAME.getName())) return characterBuilder.buildName(nameGenerator).getCharacter();
        else if(character.getName() != null) characterBuilder.buildName(nameGenerator, character.getName());

        if(attribute.equals(CharacterAttribute.BASESTATS.getName())) return characterBuilder.buildBaseStats(new StatisticsGenerator()).getCharacter();
        else if(character.getBaseStats()!= null) characterBuilder.buildBaseStats(new StatisticsGenerator(), character.getBaseStats());

        if(attribute.equals(CharacterAttribute.BIRTHDATE.getName())) return characterBuilder.buildBirthDate(new BirthDateGenerator()).getCharacter();
        else if (character.getBirthDate()!=null) characterBuilder.buildBirthDate(new BirthDateGenerator(), character.getBirthDate());

        if(attribute.equals(CharacterAttribute.HEIGHT.getName())) characterBuilder.buildHeight(new HeightGenerator());
        else if(character.getHeight()!=null) return characterBuilder.buildHeight(new HeightGenerator(), character.getHeight()).getCharacter();

        if(attribute.equals(CharacterAttribute.WEIGHT.getName())) return characterBuilder.buildWeight(new WeightGenerator()).getCharacter();
        else if(character.getWeight()!=null) characterBuilder.buildWeight(new WeightGenerator(), character.getWeight());

        if(attribute.equals(CharacterAttribute.EYECOLOR.getName())) return characterBuilder.buildEyeColor(eyeColorGenerator).getCharacter();
        else if(character.getEyeColor()!=null) characterBuilder.buildEyeColor(eyeColorGenerator, character.getEyeColor());

        if(attribute.equals(CharacterAttribute.HAIRCOLOR.getName())) return characterBuilder.buildHairColor((hairColorGenerator)).getCharacter();
        else if (character.getHairColor()!=null) characterBuilder.buildHairColor(hairColorGenerator, character.getHairColor());

        if(attribute.equals(CharacterAttribute.DOMINATINGEMOTIONS.getName())) return characterBuilder.buildEmotions(emotionGenerator).getCharacter();
        else if (character.getDominatingEmotions()!=null) characterBuilder.buildEmotions(emotionGenerator, character.getDominatingEmotions());

        if(attribute.equals(CharacterAttribute.CURRENTCAREER.getName())){
            if (character.getCurrentCareer() == null && character.getPreviousCareers() == null) return characterBuilder.buildCareers(careerGenerator).getCharacter();
            if (character.getCurrentCareer() != null && character.getPreviousCareers() == null) return characterBuilder.buildCareers(careerGenerator).getCharacter();
            if (character.getCurrentCareer() != null && character.getPreviousCareers() != null) return characterBuilder.buildCareers(careerGenerator).getCharacter();
            if (character.getCurrentCareer() == null && character.getPreviousCareers() != null) return characterBuilder.buildCurrentCareer(careerGenerator, character.getPreviousCareers()).getCharacter();
        }
        else if (character.getCurrentCareer() != null) {
            List <Career> careers = new ArrayList();
            if(character.getPreviousCareers() != null) careers.addAll(character.getPreviousCareers());
            careers.add(character.getCurrentCareer());
            characterBuilder.buildCareers(careerGenerator, careers);
        }

        if(attribute.equals(CharacterAttribute.PREDICTION.getName())) {
            while(characterBuilder.getCharacter().getPrediction()==null)
            characterBuilder.buildPrediction(predictionGenerator);
            return characterBuilder.getCharacter();
        }
        else if (character.getPrediction()!=null) characterBuilder.buildPrediction(predictionGenerator, character.getPrediction());

        if(attribute.equals(CharacterAttribute.LIVEPLACE.getName())) return characterBuilder.buildLivePlace(livePlaceGenerator).getCharacter();
        else if(character.getLivePlace() != null) characterBuilder.buildLivePlace(livePlaceGenerator, character.getLivePlace());

        if(attribute.equals(CharacterAttribute.TALENTS.getName())) return characterBuilder.buildTalents(talentGenerator).getCharacter();
        else if(character.getTalents() != null) characterBuilder.buildTalents(talentGenerator, character.getTalents());

        if(attribute.equals(CharacterAttribute.ENDSTATS.getName())) return characterBuilder.buildCareerStatistics(new CareerStatisticsGenerator()).getCharacter();
        else characterBuilder.buildCareerStatistics(new CareerStatisticsGenerator(), character);

        if(attribute.equals(CharacterAttribute.SKILLS.getName())) return characterBuilder.buildSkills(skillGenerator).getCharacter();
        else if(character.getSkills() != null) characterBuilder.buildSkills(skillGenerator, character.getSkills());

        if(attribute.equals(CharacterAttribute.RELIGION.getName())) return characterBuilder.buildReligion(new ReligionGenerator()).getCharacter();
        else if(character.getReligion() != null) characterBuilder.buildReligion(new ReligionGenerator(), character.getReligion());

        if(attribute.equals(CharacterAttribute.APPERANCE.getName())) return characterBuilder.buildApperances(apperanceGenerator).getCharacter();
        else if(character.getApperance()!= null) characterBuilder.buildApperances(apperanceGenerator, character.getApperance());

        if(attribute.equals(CharacterAttribute.PERSONALITY.getName())) return characterBuilder.buildPersonalities(personalityGenerator).getCharacter();
        else if(character.getPersonality() != null) characterBuilder.buildPersonalities(personalityGenerator, character.getPersonality());

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

    @Autowired
    UserOfAppService userOfAppService;


    @Transactional
    public long save(CharacterInputDto characterInputDto, Authentication auth) throws Exception {

        if(auth==null || !auth.isAuthenticated()) return -1; //ToDo check it and do-it

        String username = userOfAppService.getUsernameFromAuthentication(auth);
        UserOfApp user = userOfAppService.findByUsername(username);


        Character character = new Character();
        character.setCreatedBy(user);
        character.setName(characterSaveService.nameConvert(characterInputDto.getName()));
        character.setCreatedDate(new Date());

        if(!(characterInputDto.getSurname() == null || characterInputDto.getSurname().length() == 0)) {
            character.setSurname(characterSaveService.surnameConvert(characterInputDto.getSurname()));
        }

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
    public Character saveGenerate(CharacterInputDto characterInputDto) throws Exception { //ToDo refactor
        Character character = new Character();
        if(characterInputDto.getName() != null && !characterInputDto.getName().equals("") && !characterSaveService.checkSpecialCharacter(characterInputDto.getName())) character.setName(characterSaveService.nameConvert(characterInputDto.getName()));
        if(characterInputDto.getSex() != null) character.setSex(characterSaveService.sexConverter(characterInputDto.getSex()));
        if(characterInputDto.getRace() != null) character.setRace(characterSaveService.raceConverter(characterInputDto.getRace()));
        if (characterInputDto.getSurname() != null && !characterSaveService.checkSpecialCharacter(characterInputDto.getSurname()))
        {
            Surname surname = characterSaveService.surnameConvert(characterInputDto.getSurname());
            character.setSurname(prepareSurnameProperties(character.getRace(), surname, character.getSex()));
        }
        if(characterInputDto.getPrediction() != null)character.setPrediction(characterSaveService.predictionConvert(characterInputDto.getPrediction()));
        if(characterInputDto.getCurrentCareer() != null) character.setCurrentCareer(characterSaveService.currentCareerConvert(characterInputDto.getCurrentCareer()));
        if(characterInputDto.getPreviousCareers() != null) character.setPreviousCareers(characterSaveService.previousCareersConvert(characterInputDto.getPreviousCareers(),characterInputDto.getCurrentCareer()));
        if(characterInputDto.getHairColor() != null && !characterInputDto.getHairColor().equals("")) character.setHairColor(characterSaveService.hairColorConverter(characterInputDto.getHairColor()));
        if(characterInputDto.getEyeColor() != null) character.setEyeColor(characterSaveService.eyeColorConverter(characterInputDto.getEyeColor()));
        if(characterInputDto.getLivePlace() != null) character.setLivePlace(characterSaveService.livePlaceConverter(characterInputDto.getLivePlace()));
        if(characterInputDto.getBirthPlace() != null) character.setBirthPlace(characterSaveService.bornPlaceConverter(characterInputDto.getBirthPlace()));
        if(characterInputDto.getHeight() != null && characterSaveService.checkNumber(characterInputDto.getHeight(), "height")) character.setHeight(characterSaveService.heightConverter(characterInputDto.getHeight()));
        if(characterInputDto.getWeight() != null && characterSaveService.checkNumber(characterInputDto.getWeight(), "weight")) character.setWeight(characterSaveService.weightConverter(characterInputDto.getWeight()));

        if(characterSaveService.personalityOrAppearanceOrEmotionNumber(characterInputDto.getDominatingEmotions(), "emotion") && characterSaveService.checkIfTheSameCategory(characterInputDto.getDominatingEmotions(), "emotion")
        ) character.setDominatingEmotions(characterSaveService.dominantingEmotionConvert(characterInputDto.getDominatingEmotions()));
        if(characterSaveService.personalityOrAppearanceOrEmotionNumber(characterInputDto.getPersonality(), "personality") && characterSaveService.checkIfTheSameCategory(characterInputDto.getPersonality(), "personality")
        )character.setPersonality(characterSaveService.personalityListConvert(characterInputDto.getPersonality()));
        if(characterSaveService.personalityOrAppearanceOrEmotionNumber(characterInputDto.getApperance(), "apperance") && characterSaveService.checkIfTheSameCategory(characterInputDto.getApperance(), "apperance")

        )character.setApperance(characterSaveService.apperanceConvert(characterInputDto.getApperance()));
        if(characterInputDto.getSkills() != null && !characterInputDto.getSkills().equals(""))character.setSkills(characterSaveService.skillsConvert(characterInputDto.getSkills()));
        if(characterInputDto.getTalents() != null && !characterInputDto.getTalents().equals(""))character.setTalents(characterSaveService.talentsConvert(characterInputDto.getTalents()));
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
