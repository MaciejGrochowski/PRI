package com.example.PRI.services.character;

import com.example.PRI.entities.ImperialDate;
import com.example.PRI.entities.Place;
import com.example.PRI.entities.character.*;
import com.example.PRI.entities.character.Character;
import com.example.PRI.enums.Month;
import com.example.PRI.enums.Race;
import com.example.PRI.enums.Religion;
import com.example.PRI.enums.Sex;
import com.example.PRI.services.ImperialDateService;
import com.example.PRI.services.PlaceService;
import com.example.PRI.services.character.generator.CharacterBirthPlaceGenerator;
import com.example.PRI.services.character.generator.EyeColorGenerator;
import com.example.PRI.services.character.generator.SurnameGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CharacterSaveService {

    @Autowired
    private CharacterBirthPlaceGenerator characterBirthPlaceGenerator;

    @Autowired
    private SurnameGenerator surnameGenerator;

    @Autowired
    private EyeColorGenerator eyeColorGenerator;

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


    public Optional<Surname> surnameConvert(String surNew, Character character){
        Optional<Surname> surname = surnameService.findBySurname(surNew);
        surname.ifPresent(character::setSurname);
        if (!surname.isPresent() && surNew != null){
            Surname surnameNew = new Surname();
            surnameNew.setSurname(surNew);
            surnameService.save(surnameNew);
            character.setSurname(surnameNew);
        }
        return surname;
    }

    public Optional<Name> nameConvert(String inputName, Character character){
        if (inputName ==  null) throw new IllegalArgumentException();
        Optional<Name> nameOptional = nameService.findByName(inputName);
        nameOptional.ifPresent(character::setName);
        if (!nameOptional.isPresent() && inputName != null){
            Name nameNew = new Name();
            nameNew.setName(inputName);
            nameService.save(nameNew);
            character.setName(nameNew);
        }
        return nameOptional;
    }

    public Optional<Prediction> predictionConvert(String inputPrediction, Character character){
        Optional<Prediction> prediction = predictionService.findByText(inputPrediction);
        prediction.ifPresent(character::setPrediction);
        if (!prediction.isPresent() && inputPrediction != null){
            Prediction predictionNew = new Prediction();
            predictionNew.setText(inputPrediction);
            predictionService.save(predictionNew);
            character.setPrediction(predictionNew);
        }
        return prediction;
    }

    public Career currentCareerConvert(String inputCurrentCareer, Character character){
        List<String> stringList = new ArrayList<>();
        stringList.add(inputCurrentCareer);
        List <Career> careerList = careerService.findByNameIn(stringList);
        if (careerList != null){
            character.setCurrentCareer(careerList.get(0));
            return careerList.get(0);
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public List<Career> previousCareersConvert(String inputPreviousCareers, Character character){
        if(inputPreviousCareers == null){
            character.setPreviousCareers(null);
            return null;
        }
        List<String> stringList = Arrays.asList(inputPreviousCareers.split(","));
        List<Career> careerList = careerService.findByNameIn(stringList);
        if (careerList != null){
            character.setPreviousCareers(careerList);
        }
        return careerList;
    }

    public Optional<HairColor> hairColorConverter(String inputHairColor, Character character){
        Optional<HairColor> hairColor = hairColorService.findByName(inputHairColor);
        hairColor.ifPresent(character::setHairColor);
        if (!hairColor.isPresent() && inputHairColor != null){
            HairColor hairColorNew = new HairColor();
            hairColorNew.setColor(inputHairColor);
            character.setHairColor(hairColorNew);
            //return hairColor;
        }
        if(hairColor.get() == null) {
            throw new IllegalArgumentException();
        }
        return hairColor;
    }

    public Optional<EyeColor> eyeColorConverter(String inputeyeColor, Character character){
        Optional<EyeColor> eyeColor = eyeColorService.findByName(inputeyeColor);
        eyeColor.ifPresent(character::setEyeColor);
        if (!eyeColor.isPresent() && inputeyeColor != null){
            EyeColor eyeColorNew = new EyeColor();
            eyeColorNew.setColor(inputeyeColor);
            character.setEyeColor(eyeColorNew);
        }
        if(eyeColor.get() == null) {
            throw new IllegalArgumentException();
        }
        return eyeColor;
    }

    public Optional<Place> bornPlaceConverter(String bornPlaceInput, Character character) {
        Optional<Place> bornPlace = placeService.findByName(bornPlaceInput);
        bornPlace.ifPresent(character::setBirthPlace);
        if (!bornPlace.isPresent() && bornPlaceInput != null){
            Place bornPlaceNew = new Place();
            bornPlaceNew.setName(bornPlaceInput);
            character.setBirthPlace(bornPlaceNew);
        }
        if(bornPlace.get() == null) {
            throw new IllegalArgumentException();
        }
        return bornPlace;
    }

    public Optional<Place> livePlaceConverter(String livePlaceInput, Character character) {
        Optional<Place> livePlace = placeService.findByName(livePlaceInput);
        livePlace.ifPresent(character::setLivePlace);
        if (!livePlace.isPresent() && livePlaceInput != null){
            Place bornPlaceNew = new Place();
            bornPlaceNew.setName(livePlaceInput);
            character.setLivePlace(bornPlaceNew);
        }
        if(livePlace.get() == null) {
            throw new IllegalArgumentException();
        }
        return livePlace;
    }


    public Sex sexConverter(String sexInput, Character character) {
        if(sexInput == null) {
            throw new IllegalArgumentException();
        }
        Sex newSex = null;
        if (sexInput.equals("Kobieta")) newSex = Sex.FEMALE;
        else if (sexInput.equals("Mężczyzna")) newSex = Sex.MALE;
        if (newSex == null) throw new IllegalArgumentException();
        character.setSex(newSex);
        return newSex;
    }

    public Race raceConverter(String raceInput, Character character) {
        if(raceInput == null) {
            throw new IllegalArgumentException();
        }
        Race newRace = null;
        if (raceInput.equals("Elf")){ newRace = Race.ELF; }
        else if(raceInput.equals("Krasnolud")){ newRace = Race.DWARF;}
        else if (raceInput.equals("Niziołek")) { newRace = Race.HALFLING;}
        else if (raceInput.equals("Człowiek")) { newRace = Race.HUMAN;}
        if (newRace == null) throw new IllegalArgumentException();
        character.setRace(newRace);
        return newRace;
    }

    public Religion religionConverter(String religionInput, Character character) {
        Religion newReligion = Religion.findByGodName(religionInput);
        if (newReligion == null) throw new IllegalArgumentException();
        character.setReligion(newReligion);
        return newReligion;
    }

    public int birthDayConverter(String dayInput) {
        if(dayInput == null) {
            throw new IllegalArgumentException();
        }
        int day = 0;
        if (dayInput.matches("[0-9]+")){
            day = Integer.parseInt(dayInput);
            if (day < 1 || day > 34){
                throw new IllegalArgumentException();
            }
        }
        else {
            throw new IllegalArgumentException();
        }
        return day;
    }

    public int birthYearConverter(String yearInput) {
        if(yearInput == null) {
            throw new IllegalArgumentException();
        }
        int year = 0;
        if (yearInput.matches("[0-9]*")){
            year = Short.parseShort(yearInput);
            if (year < 0 || year > 3000){
                throw new IllegalArgumentException();
            }
        }
        else {
            throw new IllegalArgumentException();
        }
        return year;
    }

    public Month birthMonthConverter(String birthMonthInput) {
        if(birthMonthInput == null) {
            throw new IllegalArgumentException();
        }
        Month newMonth = Month.findByMonthName(birthMonthInput);
        return newMonth;
    }

    public ImperialDate imperialDateConverter(int day, Month month, int year, Character character) {
        ImperialDate newDate = new ImperialDate(day,month,year);
        ImperialDate x = imperialDateService.save(newDate);
        character.setBirthDate(x);
        return newDate;
    }

    public Integer heightConverter(String height, Character character) {
        if (height == null) throw new IllegalArgumentException();
        Integer newHeight = null;
        if (height.matches("[0-9]*")){
            newHeight = Integer.parseInt(height);
            if (newHeight < 50 || newHeight > 300){
                throw new IllegalArgumentException();
            }
            else {
                character.setHeight(newHeight);
            }
        }
        else {
            throw new IllegalArgumentException();
        }
        return newHeight;
    }

    public Integer weightConverter(String weight, Character character) {
        if (weight == null) throw new IllegalArgumentException();
        Integer newWeight = null;
        if (weight.matches("[0-9]*")){
            newWeight = Integer.parseInt(weight);
            if (newWeight < 10 || newWeight > 800){
                throw new IllegalArgumentException();
            }
            else {
                character.setWeight(newWeight);
            }
        }
        else {
            throw new IllegalArgumentException();
        }
        return newWeight;
    }

    public List<Personality> personalityListConvert(String inputPersonality, Character character){
        if(inputPersonality == null){
            character.setPersonality(null);
            return null;
        }
        List<String> stringList = Arrays.asList(inputPersonality.split(","));
        List<Personality> personalityList = personalityService.findByNameIn(stringList);
        if (personalityList != null){
            character.setPersonality(personalityList);
        }
        return personalityList;
    }

    public List<Apperance> apperanceConvert(String inputApperance, Character character){
        if(inputApperance == null){
            character.setApperance(null);
            return null;
        }
        List<String> stringList = Arrays.asList(inputApperance.split(","));
        List<Apperance> apperanceList = apperanceService.findByNameIn(stringList);
        if (apperanceList != null){
            character.setApperance(apperanceList);
        }
        return apperanceList;
    }

    public List<Skill> skillsConvert(String inputSkills, Character character){
        if(inputSkills == null){
            character.setSkills(null);
            return null;
        }
        List<String> stringList = Arrays.asList(inputSkills.split(","));
        List<Skill> skillList = skillService.findByNameIn(stringList);
        if (skillList != null){
            character.setSkills(skillList);
        }
        return skillList;
    }

    public List<Talent> talentsConvert(String inputTalents, Character character){
        if(inputTalents == null){
            character.setTalents(null);
            return null;
        }
        List<String> stringList = Arrays.asList(inputTalents.split(","));
        List<Talent> talentList = talentService.findByNameIn(stringList);
        if (talentList != null){
            character.setTalents(talentList);
        }
        return talentList;
    }

    public List<Emotion> dominantingEmotionConvert(String inputEmotion, Character character){
        if(inputEmotion == null){
            character.setDominatingEmotions(null);
            return null;
        }
        List<String> stringList = Arrays.asList(inputEmotion.split(","));
        List<Emotion> emotionList = emotionService.findByNameIn(stringList);
        if (emotionList != null){
            character.setDominatingEmotions(emotionList);
        }
        return emotionList;
    }

    public Integer endWeaponSkillConvert(String WeaponSkill, Character character){
        if (WeaponSkill == null) throw new IllegalArgumentException();
        Integer newWeaponSkill = null;
        if (WeaponSkill.matches("[0-9]+")){
            newWeaponSkill = Integer.parseInt(WeaponSkill);
            if (newWeaponSkill < 99) character.setEndWeaponSkills(newWeaponSkill);
            else throw new IllegalArgumentException();
        }
        else throw new IllegalArgumentException();

        return newWeaponSkill;
    }

    public Integer endBallisticSkillsConvert(String BallisticSkills, Character character){
        if (BallisticSkills == null) throw new IllegalArgumentException();
        Integer newBallisticSkill = null;
        if (BallisticSkills.matches("[0-9]+")){
            newBallisticSkill = Integer.parseInt(BallisticSkills);
            if (newBallisticSkill < 99) character.setEndBallisticSkills(newBallisticSkill);
            else throw new IllegalArgumentException();
        }
        else throw new IllegalArgumentException();

        return newBallisticSkill;
    }

    public Integer endStrengthConvert(String endStrength, Character character){
        if (endStrength == null) throw new IllegalArgumentException();
        Integer newEndStrength = null;
        if (endStrength.matches("[0-9]+")){
            newEndStrength = Integer.parseInt(endStrength);
            if (newEndStrength < 99) character.setEndStrength(newEndStrength);
            else throw new IllegalArgumentException();
        }
        else throw new IllegalArgumentException();

        return newEndStrength;
    }

    public Integer endToughnessConvert(String endToughness, Character character){
        if (endToughness == null) throw new IllegalArgumentException();
        Integer newEndToughness = null;
        if (endToughness.matches("[0-9]+")){
            newEndToughness = Integer.parseInt(endToughness);
            if (newEndToughness < 99) character.setEndToughness(newEndToughness);
            else throw new IllegalArgumentException();
        }
        else throw new IllegalArgumentException();

        return newEndToughness;
    }

    public Integer endAgilityConvert(String endAgility, Character character){
        if (endAgility == null) throw new IllegalArgumentException();
        Integer newEndAgility = null;
        if (endAgility.matches("[0-9]+")){
            newEndAgility = Integer.parseInt(endAgility);
            if (newEndAgility < 99) character.setEndAgility(newEndAgility);
            else throw new IllegalArgumentException();
        }
        else throw new IllegalArgumentException();

        return newEndAgility;
    }

    public Integer endIntelligenceConvert(String endIntelligence, Character character){
        if (endIntelligence == null) throw new IllegalArgumentException();
        Integer newEndIntelligence = null;
        if (endIntelligence.matches("[0-9]+")){
            newEndIntelligence = Integer.parseInt(endIntelligence);
            if (newEndIntelligence < 99) character.setEndIntelligence(newEndIntelligence);
            else throw new IllegalArgumentException();
        }
        else throw new IllegalArgumentException();

        return newEndIntelligence;
    }

    public Integer endWillPowerConvert(String endWillPower, Character character){
        if (endWillPower == null) throw new IllegalArgumentException();
        Integer newEndWillPower = null;
        if (endWillPower.matches("[0-9]+")){
            newEndWillPower = Integer.parseInt(endWillPower);
            if (newEndWillPower < 99) character.setEndWillPower(newEndWillPower);
            else throw new IllegalArgumentException();
        }
        else throw new IllegalArgumentException();

        return newEndWillPower;
    }

    public Integer endFellowshipConvert(String endFellowship, Character character){
        if (endFellowship == null) throw new IllegalArgumentException();
        Integer newEndFellowship = null;
        if (endFellowship.matches("[0-9]+")){
            newEndFellowship = Integer.parseInt(endFellowship);
            if (newEndFellowship < 99) character.setEndFellowship(newEndFellowship);
            else throw new IllegalArgumentException();
        }
        else throw new IllegalArgumentException();

        return newEndFellowship;
    }

    public Integer endAttacksConvert(String endAttack, Character character){
        if (endAttack == null) throw new IllegalArgumentException();
        Integer newEndAttack = null;
        if (endAttack.matches("[0-9]+")){
            newEndAttack = Integer.parseInt(endAttack);
            if (newEndAttack < 99) character.setEndAttacks(newEndAttack);
            else throw new IllegalArgumentException();
        }
        else throw new IllegalArgumentException();

        return newEndAttack;
    }

    public Integer endWoundsConvert(String endWound, Character character){
        if (endWound == null) throw new IllegalArgumentException();
        Integer newEndWound = null;
        if (endWound.matches("[0-9]+")){
            newEndWound = Integer.parseInt(endWound);
            if (newEndWound < 99) character.setEndWounds(newEndWound);
            else throw new IllegalArgumentException();
        }
        else throw new IllegalArgumentException();

        return newEndWound;
    }

    public Integer endMovementConvert(String endMovement, Character character){
        if (endMovement == null) throw new IllegalArgumentException();
        Integer newEndMovement = null;
        if (endMovement.matches("[0-9]+")){
            newEndMovement = Integer.parseInt(endMovement);
            if (newEndMovement < 99) character.setEndMovement(newEndMovement);
            else throw new IllegalArgumentException();
        }
        else throw new IllegalArgumentException();

        return newEndMovement;
    }

    public Integer endMagicConvert(String endMagic, Character character){
        if (endMagic == null) throw new IllegalArgumentException();
        Integer newEndMagic = null;
        if (endMagic.matches("[0-9]+")){
            newEndMagic = Integer.parseInt(endMagic);
            if (newEndMagic < 99) character.setEndMagic(newEndMagic);
            else throw new IllegalArgumentException();
        }
        else throw new IllegalArgumentException();

        return newEndMagic;
    }
}
