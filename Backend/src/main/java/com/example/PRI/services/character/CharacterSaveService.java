package com.example.PRI.services.character;

import com.example.PRI.dtos.characters.CharacterInputDto;
import com.example.PRI.entities.ImperialDate;
import com.example.PRI.entities.Place;
import com.example.PRI.entities.character.Character;
import com.example.PRI.entities.character.*;
import com.example.PRI.enums.*;
import com.example.PRI.exceptions.CharacterSaveException;
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
import java.util.stream.Collectors;

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

    @Autowired
    private StatisticsService statisticsService;


    public Surname surnameConvert(String surNew) {
        String prefix = "";
        if (surNew == null || surNew.equals("")) {
            return new Surname();
        }
        surNew = surNew.trim();
        Optional<Surname> surname = surnameService.findBySurname(surNew);
        if (surname.isPresent()) return surname.get();
        if (surNew.matches(".*\\d.*")  || checkSpecialCharacter(surNew) || surNew.matches("\\s*")) throw new CharacterSaveException("Nazwisko może zawierać tylko litery.", new IllegalArgumentException());
        if (surNew.startsWith("von ") || surNew.startsWith("Von ")){
            prefix = "von ";
            surNew = surNew.substring(4);
            surNew = surNew.trim();
        }
        if (surNew.matches("[A-Za-z].*")){
            String firstLetter = surNew.substring(0, 1).toUpperCase();
            String newSurnameWithBigFirstLetter = firstLetter + surNew.substring(1);
            Surname nextSurname = new Surname();
            nextSurname.setSurname(prefix + newSurnameWithBigFirstLetter);
            surnameService.save(nextSurname);
            return nextSurname;
        }
        else {
            Surname surnameNew = new Surname();
            surnameNew.setSurname(surNew);
            surnameService.save(surnameNew);
            return  surnameNew;
        }

    }


    public Name nameConvert(String inputName) {
        if (inputName == null || inputName.equals(""))
            throw new CharacterSaveException("Podaj imię postaci swojej postaci.", new IllegalArgumentException());
        inputName = inputName.trim();
        inputName = inputName.substring(0, 1).toUpperCase() + inputName.substring(1).toLowerCase();  //ta linijka zamiast ifa niżej
        Optional<Name> nameOptional = nameService.findByName(inputName);
        if (nameOptional.isPresent()) return nameOptional.get();
        if (checkSpecialCharacter(inputName) || inputName.matches(".*\\d.*")) throw new CharacterSaveException("Imię może zawierać tylko litery.", new IllegalArgumentException());
//        if (inputName.matches("[a-z].*")) {
//           String firstLetter = inputName.substring(0, 1).toUpperCase();
//            String newNameWithBigFirstLetter = firstLetter + inputName.substring(1);
//            Name nextName = new Name();
//            nextName.setName(newNameWithBigFirstLetter);
//            nameService.save(nextName);
//            return nextName;}
            else {
            Name nameNew = new Name();
            nameNew.setName(inputName);
            nameService.save(nameNew);
            return nameNew;
        }
    }

    public Prediction predictionConvert(String inputPrediction) {
        if (inputPrediction == null) return new Prediction();
        Optional<Prediction> prediction = predictionService.findByText(inputPrediction);
        if (prediction.isPresent()) return prediction.get();
        if (inputPrediction.matches("[a-z].*")) {
            String firstLetter = inputPrediction.substring(0, 1).toUpperCase();
            String newPredictionWithBigFirstLetter = firstLetter + inputPrediction.substring(1);
            Prediction nextPrediction = new Prediction();
            nextPrediction.setText(newPredictionWithBigFirstLetter);
            predictionService.save(nextPrediction);
            return nextPrediction;
        }
        else{
            Prediction nextPrediction = new Prediction();
            nextPrediction.setText(inputPrediction);
            predictionService.save(nextPrediction);
            return nextPrediction;
        }
    }

    public Career currentCareerConvert(String inputCurrentCareer) {
        return careerService.findByName(inputCurrentCareer);
    }

    public List<Career> previousCareersConvert(String inputPreviousCareers, String inputCurrentCareer) {
        if (inputPreviousCareers == null) return null;
        List<String> stringList = Arrays.asList(inputPreviousCareers.split(","));
        List<Career> careerList = careerService.findByNameIn(stringList);
        Career currentCareer = careerService.findByName(inputCurrentCareer);
        if (careerList.contains(currentCareer)) throw new CharacterSaveException("Obecna i poprzednia profejsa są takie same.", new IllegalArgumentException());
        else return careerList;
    }

    public HairColor hairColorConverter(String inputHairColor) {
        Optional<HairColor> hairColor = hairColorService.findByName(inputHairColor);
        if (hairColor.isPresent()) return hairColor.get();
        else throw new CharacterSaveException("Podany kolor włosów nie istnieje.", new IllegalArgumentException());
    }

    public EyeColor eyeColorConverter(String inputEyeColor) {
        Optional<EyeColor> eyeColor = eyeColorService.findByName(inputEyeColor);
        if (eyeColor.isPresent()) return eyeColor.get();
        else throw new CharacterSaveException("Podany kolor oczu nie istnieje.", new IllegalArgumentException());
    }

    public Place bornPlaceConverter(String bornPlaceInput) {
        Optional<Place> bornPlace = placeService.findByName(bornPlaceInput);
        if (bornPlace.isPresent()) return bornPlace.get();
            else throw new CharacterSaveException("Wybierz miejsce urodzenia swojej postaci.", new IllegalArgumentException());
    }

    public Place livePlaceConverter(String livePlaceInput) {
        Optional<Place> livePlace = placeService.findByName(livePlaceInput);
        if (livePlace.isPresent()) return livePlace.get();
        else throw new CharacterSaveException("Wybierz miejsce pobytu swojej postaci.", new IllegalArgumentException());
    }

    public Sex sexConverter(String sexInput) {
        if (sexInput == null)  throw new CharacterSaveException("Wybierz płeć swojej postaci.", new IllegalArgumentException());
        Sex newSex = null;
        if (sexInput.equals("Kobieta")) newSex = Sex.FEMALE;
        if (sexInput.equals("Mężczyzna")) newSex = Sex.MALE;
        if (newSex == null) throw new IllegalArgumentException();
        return newSex;
    }

    public Race raceConverter(String raceInput) {
        if (raceInput == null) throw new CharacterSaveException("Wybierz rase swojej postaci.", new IllegalArgumentException());
        Race newRace = null;
        if (raceInput.equals("Elf")) newRace = Race.ELF;
        else if (raceInput.equals("Krasnolud")) newRace = Race.DWARF;
        else if (raceInput.equals("Niziołek")) newRace = Race.HALFLING;
        else if (raceInput.equals("Człowiek")) newRace = Race.HUMAN;
        if (newRace == null) throw new CharacterSaveException("Nieprawidłowa rasa.", new IllegalArgumentException());
        return newRace;
    }

    public Religion religionConverter(String religionInput) {
        Religion newReligion = Religion.findByGodName(religionInput);
        if (newReligion == null) throw new CharacterSaveException("Wybierz religie swojej postaci.", new IllegalArgumentException());
        return newReligion;
    }

    public int birthDayConverter(String dayInput) {
        if (dayInput == null || dayInput.equals("")) throw new CharacterSaveException("Podaj dzień urodzenia twojej postaci.", new IllegalArgumentException());
        int day = 0;
        if (dayInput.matches("[0-9]+")) {
            day = Integer.parseInt(dayInput);
            if (day < 1 || day > 34) throw new CharacterSaveException("Niepoprawny dzień urodzenia", new IllegalArgumentException());
        } else throw new CharacterSaveException("Niepoprawny format dnia.", new IllegalArgumentException());
        return day;
    }

    public int birthYearConverter(String yearInput) {
        if (yearInput == null || yearInput.equals("")) throw new CharacterSaveException("Podaj rok urodzenia twojej postaci.", new IllegalArgumentException());
        int year = 0;
        if (yearInput.matches("[0-9]*")) {
            year = Short.parseShort(yearInput);
            if (year < 0 || year > 3000) throw new CharacterSaveException("Niepoprawny rok urodzenia", new IllegalArgumentException());
        } else throw new CharacterSaveException("Niepoprawny format roku.", new IllegalArgumentException());
        return year;
    }

    public Month birthMonthConverter(String birthMonthInput) {
        if (birthMonthInput == null) throw new CharacterSaveException("Podaj miesiąc urodzenia twojej postaci.", new IllegalArgumentException());
        return Month.findByMonthName(birthMonthInput);
    }

    public ImperialDate imperialDateConverter(String day, String month, String year) {
        ImperialDate newDate = new ImperialDate(birthDayConverter(day), birthMonthConverter(month), birthYearConverter(year));
        imperialDateService.save(newDate);
        return newDate;
    }

    public StarSign saveStarSign(Character character, ImperialDate date) {
        Integer day = date.getDay();
        StarSign characterStarSign = null;
        String month = date.getMonth().getMonthName();
        List<StarSign> starSigns = Arrays.asList(StarSign.values());
        starSigns = starSigns.stream().filter(s -> s.getStartDate().split(" ")[1].equals(month) || s.getEndDate().split(" ")[1].equals(month)).collect(Collectors.toList());

        for(StarSign starSign : starSigns){
            Integer startDay = Integer.valueOf(starSign.getStartDate().split(" ")[0]);
            Integer endDay = Integer.valueOf(starSign.getEndDate().split(" ")[0]);
            String startMonth = starSign.getStartDate().split(" ")[1];
            String endMonth = starSign.getEndDate().split(" ")[1];
            if(startMonth.equals(endMonth)){
                if(day >= startDay && day <= endDay){
                    characterStarSign = starSign;
                    break;
                }
            }
            else{
                if((startMonth.equals(month) && day >= startDay) || (endMonth.equals(month) && day <= endDay) ){
                    characterStarSign = starSign;
                    break;
                }
            }
        }
        character.setStarSign(characterStarSign);
        return characterStarSign;
    }

    public Integer heightConverter(String height) {
        if (height == null || height.equals("")) throw new CharacterSaveException("Podaj wzrost twojej postaci.", new IllegalArgumentException());
        Integer newHeight = null;
        if (height.matches("[0-9]*")) {
            newHeight = Integer.parseInt(height);
            if (newHeight < 50 || newHeight > 300) throw new CharacterSaveException("Podaj poprawny wzrost twojej postaci.", new IllegalArgumentException());
        } else throw new CharacterSaveException("Niepoprawny format wzrostu.", new IllegalArgumentException());
        return newHeight;
    }

    public Integer weightConverter(String weight) {
        if (weight == null || weight.equals("")) throw new CharacterSaveException("Podaj wagę twojej postaci.", new IllegalArgumentException());
        Integer newWeight = null;
        if (weight.matches("[0-9]*")) {
            newWeight = Integer.parseInt(weight);
            if (newWeight < 10 || newWeight > 800) throw new CharacterSaveException("Podaj poprawną wagę twojej postaci.", new IllegalArgumentException());
        } else throw new CharacterSaveException("Niepoprawny format wagi.", new IllegalArgumentException());
        return newWeight;
    }
    //TODO THIS SHOULD BE IN CONVERTER CLASS!!!

    public List<Personality> personalityListConvert(String inputPersonality) {
        if (inputPersonality == null) return null;
        List<String> stringList = Arrays.asList(inputPersonality.split(","));
        List<Personality> personalityList = personalityService.findByNameIn(stringList);
        if (personalityList.size() > 5) throw new CharacterSaveException("Zbyt dużo cech charakteru.\nMaksymalna liczba cech: 5", new IllegalArgumentException());
        List<String> personalityCheck = personalityList.stream().map(Personality::getType).collect(Collectors.toList());
        if(personalityCheck.stream().distinct().count() <personalityCheck.size()){
            String repeatElement = "";
            List<String> repeatElementList = new ArrayList<>();
            List<String> wrongPersonality = new ArrayList<>();
            for (String stringSearch: personalityCheck){
                if (repeatElementList.contains(stringSearch)) {repeatElement = stringSearch; break;}
                else {repeatElementList.add(stringSearch); }
                }
            for (Personality personalitySearch: personalityList) { if (personalitySearch.getType().equals(repeatElement)) wrongPersonality.add(personalitySearch.getName()); }
            throw new CharacterSaveException("Cechy charakteru \"" + wrongPersonality.get(0) + "\" oraz \"" + wrongPersonality.get(1) +"\" się wykluczają.", new IllegalArgumentException());}
        return  personalityList;
    }

    public List<Apperance> apperanceConvert(String inputApperance) {
        if (inputApperance == null) return null;
        List<String> stringList = Arrays.asList(inputApperance.split(","));
        List<Apperance> apperanceList = apperanceService.findByNameIn(stringList);
        if (apperanceList.size() > 5) throw new CharacterSaveException("Zbyt dużo cech wyglądu.\nMaksymalna liczba cech: 5", new IllegalArgumentException());
        List<String> apperanceCheck = apperanceList.stream().map(Apperance::getType).collect(Collectors.toList());
        if(apperanceCheck.stream().distinct().count() <apperanceCheck.size()){
            String repeatElement = "";
            List<String> repeatElementList = new ArrayList<>();
            List<String> wrongApperance = new ArrayList<>();
            for (String stringSearch: apperanceCheck){
                if (repeatElementList.contains(stringSearch)) {repeatElement = stringSearch; break;}
                else {repeatElementList.add(stringSearch); }
            }
            for (Apperance apperanceSearch: apperanceList) { if (apperanceSearch.getType().equals(repeatElement)) wrongApperance.add(apperanceSearch.getName()); }
            throw new CharacterSaveException("Cechy wyglądu \"" + wrongApperance.get(0) + "\" oraz \"" + wrongApperance.get(1) +"\" się wykluczają.", new IllegalArgumentException());}
        return apperanceList;
    }

    public List<Skill> skillsConvert(String inputSkills) {
        if (inputSkills == null || inputSkills.equals("")) throw new CharacterSaveException("Postać musi posiadać umiejętności.", new IllegalArgumentException());
        List<String> stringList = Arrays.asList(inputSkills.split(","));
        List<Skill> skillList = skillService.findByNameIn(stringList.stream().map(s -> s.split(" \\+")[0]).collect(Collectors.toList()));
        List<Skill> characterSkills = new ArrayList<>();
        for(String skillString : stringList){
            String name = skillString.split(" \\+")[0];
            String level = skillString.split(" \\+")[1];
            Optional<Skill> maybeSkill = skillList.stream().filter(s -> s.getName().equals(name) && s.getLevel().toString().equals(level)).findFirst();
            maybeSkill.ifPresent(characterSkills::add);
        }
        if (characterSkills.size() > 0)  return characterSkills;
        else throw new CharacterSaveException("Podaj poprawne umiejętności.", new IllegalArgumentException());
    }

    public List<Talent> talentsConvert(String inputTalents) {
        if (inputTalents == null) throw new CharacterSaveException("Postać musi posiadać talenty.", new IllegalArgumentException());
        List<String> stringList = Arrays.asList(inputTalents.split(","));
        List<Talent> talentList = talentService.findByNameIn(stringList);
        if (talentList != null) return talentList;
        else throw new CharacterSaveException("Podaj poprawne zdolności.", new IllegalArgumentException());
    }

    public List<Emotion> dominantingEmotionConvert(String inputEmotion) {
        if (inputEmotion == null) return null;
        List<String> stringList = Arrays.asList(inputEmotion.split(","));
        List<Emotion> emotionList = emotionService.findByNameIn(stringList);
        if (emotionList.size() > 4) throw new CharacterSaveException("Zbyt dużo emocji.\nMaksymalna liczba emocji: 4", new IllegalArgumentException());
        List<String> emotionCheck = emotionList.stream().map(Emotion::getType).collect(Collectors.toList());
        if(emotionCheck.stream().distinct().count() <emotionCheck.size()) {
            String repeatElement = "";
            List<String> repeatElementList = new ArrayList<>();
            List<String> wrongEmotion = new ArrayList<>();
            for (String stringSearch: emotionCheck){
                if (repeatElementList.contains(stringSearch)) {repeatElement = stringSearch; break;}
                else {repeatElementList.add(stringSearch); }
            }
            for (Emotion emotionSearch: emotionList) { if (emotionSearch.getType().equals(repeatElement)) wrongEmotion.add(emotionSearch.getName()); }
            throw new CharacterSaveException("Emocje \"" + wrongEmotion.get(0) + "\" oraz \"" + wrongEmotion.get(1) +"\" się wykluczają.", new IllegalArgumentException());}
        return emotionList;
    }

    public boolean personalityOrAppearanceOrEmotionNumber(String checkString, String type){
        if (checkString == null) return false;
        List<String> stringList = Arrays.asList(checkString.split(","));
        switch (type) {
            case "emotion":
                List<Emotion> emotionList = emotionService.findByNameIn(stringList);
                return emotionList.size() < 5;
            case "personality":
                List<Personality> personalityList = personalityService.findByNameIn(stringList);
                return personalityList.size() < 6;
            case "apperance":
                List<Apperance> apperanceList = apperanceService.findByNameIn(stringList);
                return apperanceList.size() < 6;
            default:
                return false;
        }
    }

    public Integer endWeaponSkillConvert(String WeaponSkill) {
        if (WeaponSkill == null) throw new CharacterSaveException("Podaj statystyke dla obecnej walki wręcz.", new IllegalArgumentException());
        Integer newWeaponSkill = null;
        if (WeaponSkill.matches("[0-9]+")) {
            newWeaponSkill = Integer.parseInt(WeaponSkill);
            if (newWeaponSkill < 100) return newWeaponSkill;
            else throw new CharacterSaveException("Za duża liczba dla obecnej walki wręcz.\nMaksymalna liczba: 99", new IllegalArgumentException());
        } else throw new CharacterSaveException("Niepoprawny format dla obecnej walki wręcz.", new IllegalArgumentException());
    }

    public Integer endBallisticSkillsConvert(String BallisticSkills) {
        if (BallisticSkills == null) throw new CharacterSaveException("Podaj statystyke dla obecnych umiejętności strzeleckich.", new IllegalArgumentException());
        Integer newBallisticSkill = null;
        if (BallisticSkills.matches("[0-9]+")) {
            newBallisticSkill = Integer.parseInt(BallisticSkills);
            if (newBallisticSkill < 100) return newBallisticSkill;
            else throw new CharacterSaveException("Za duża liczba dla obecnych umiejętności strzeleckich.\nMaksymalna liczba: 99", new IllegalArgumentException());
        } else throw new CharacterSaveException("Niepoprawny format dla obecnych umiejętności strzeleckich.", new IllegalArgumentException());
    }

    public Integer endStrengthConvert(String endStrength) {
        if (endStrength == null) throw new CharacterSaveException("Podaj statystyke dla obecnej krzepy.", new IllegalArgumentException());
        Integer newEndStrength = null;
        if (endStrength.matches("[0-9]+")) {
            newEndStrength = Integer.parseInt(endStrength);
            if (newEndStrength < 100) return newEndStrength;
            else throw new CharacterSaveException("Za duża liczba dla obecnej krzepy.\nMaksymalna liczba: 99", new IllegalArgumentException());
        } else throw new CharacterSaveException("Niepoprawny format dla obecnej krzepy.", new IllegalArgumentException());
    }

    public Integer endToughnessConvert(String endToughness) {
        if (endToughness == null) throw new CharacterSaveException("Podaj statystyke dla obecnej odporności.", new IllegalArgumentException());
        Integer newEndToughness = null;
        if (endToughness.matches("[0-9]+")) {
            newEndToughness = Integer.parseInt(endToughness);
            if (newEndToughness < 100) return newEndToughness;
            else throw new CharacterSaveException("Za duża liczba dla obecnej odporności.\nMaksymalna liczba: 99", new IllegalArgumentException());
        } else throw new CharacterSaveException("Niepoprawny format dla obecnej odporności.", new IllegalArgumentException());
    }

    public Integer endAgilityConvert(String endAgility) {
        if (endAgility == null) throw new CharacterSaveException("Podaj statystyke dla obecnej zręczności.", new IllegalArgumentException());
        Integer newEndAgility = null;
        if (endAgility.matches("[0-9]+")) {
            newEndAgility = Integer.parseInt(endAgility);
            if (newEndAgility < 100) return newEndAgility;
            else throw new CharacterSaveException("Za duża liczba dla obecnej zręczności.\nMaksymalna liczba: 99", new IllegalArgumentException());
        } else throw new CharacterSaveException("Niepoprawny format dla obecnej zręczności.", new IllegalArgumentException());
    }

    public Integer endIntelligenceConvert(String endIntelligence) {
        if (endIntelligence == null) throw new CharacterSaveException("Podaj statystyke dla obecnej inteligencji.", new IllegalArgumentException());
        Integer newEndIntelligence = null;
        if (endIntelligence.matches("[0-9]+")) {
            newEndIntelligence = Integer.parseInt(endIntelligence);
            if (newEndIntelligence < 100) return newEndIntelligence;
            else throw new CharacterSaveException("Za duża liczba dla obecnej inteligencji.\nMaksymalna liczba: 99", new IllegalArgumentException());
        } else throw new CharacterSaveException("Niepoprawny format dla obecnej inteligencji.", new IllegalArgumentException());
    }

    public Integer endWillPowerConvert(String endWillPower) {
        if (endWillPower == null) throw new CharacterSaveException("Podaj statystyke dla obecnej siły woli.", new IllegalArgumentException());
        Integer newEndWillPower = null;
        if (endWillPower.matches("[0-9]+")) {
            newEndWillPower = Integer.parseInt(endWillPower);
            if (newEndWillPower < 100) return newEndWillPower;
            else throw new CharacterSaveException("Za duża liczba dla obecnej siły woli.\nMaksymalna liczba: 99", new IllegalArgumentException());
        } else throw new CharacterSaveException("Niepoprawny format dla obecnej siły woli.", new IllegalArgumentException());
    }

    public Integer endFellowshipConvert(String endFellowship) {
        if (endFellowship == null) throw new CharacterSaveException("Podaj statystyke dla obecnej ogłady.", new IllegalArgumentException());
        Integer newEndFellowship = null;
        if (endFellowship.matches("[0-9]+")) {
            newEndFellowship = Integer.parseInt(endFellowship);
            if (newEndFellowship < 100) return newEndFellowship;
            else throw new CharacterSaveException("Za duża liczba dla obecnej ogłady.\nMaksymalna liczba: 99", new IllegalArgumentException());
        } else throw new CharacterSaveException("Niepoprawny format dla obecnej ogłady.", new IllegalArgumentException());
    }

    public Integer endAttacksConvert(String endAttack) {
        if (endAttack == null) throw new CharacterSaveException("Podaj statystyke dla obecnego ataku.", new IllegalArgumentException());
        Integer newEndAttack = null;
        if (endAttack.matches("[0-9]+")) {
            newEndAttack = Integer.parseInt(endAttack);
            if (newEndAttack < 100) return newEndAttack;
            else throw new CharacterSaveException("Za duża liczba dla obecnego ataku.\nMaksymalna liczba: 99", new IllegalArgumentException());
        } else throw new CharacterSaveException("Niepoprawny format dla obecnego ataku.", new IllegalArgumentException());
    }

    public Integer endWoundsConvert(String endWound) {
        if (endWound == null) throw new CharacterSaveException("Podaj statystyke dla obecnej żywotności.", new IllegalArgumentException());
        Integer newEndWound = null;
        if (endWound.matches("[0-9]+")) {
            newEndWound = Integer.parseInt(endWound);
            if (newEndWound < 100) return newEndWound;
            else throw new CharacterSaveException("Za duża liczba dla obecnej żywotności.\nMaksymalna liczba: 99", new IllegalArgumentException());
        } else throw new CharacterSaveException("Niepoprawny format dla obecnej żywotności.", new IllegalArgumentException());
    }

    public Integer endMovementConvert(String endMovement) {
        if (endMovement == null) throw new CharacterSaveException("Podaj statystyke dla obecnej szybkości.", new IllegalArgumentException());
        Integer newEndMovement = null;
        if (endMovement.matches("[0-9]+")) {
            newEndMovement = Integer.parseInt(endMovement);
            if (newEndMovement < 100) return newEndMovement;
            else throw new CharacterSaveException("Za duża liczba dla obecnej szybkości.\nMaksymalna liczba: 99", new IllegalArgumentException());
        } else throw new CharacterSaveException("Niepoprawny format dla obecnej szybkości.", new IllegalArgumentException());
    }

    public Integer endMagicConvert(String endMagic) {
        if (endMagic == null) throw new CharacterSaveException("Podaj statystyke dla obecnej magii.", new IllegalArgumentException());
        Integer newEndMagic = null;
        if (endMagic.matches("[0-9]+")) {
            newEndMagic = Integer.parseInt(endMagic);
            if (newEndMagic < 100) return newEndMagic;
            else throw new CharacterSaveException("Za duża liczba dla obecnej magii.\nMaksymalna liczba: 99", new IllegalArgumentException());
        } else throw new CharacterSaveException("Niepoprawny format dla obecnej magii.", new IllegalArgumentException());
    }


    public boolean checkSpecialCharacter (String inputName){
        return inputName.contains("!") || inputName.contains("@") || inputName.contains("#") || inputName.contains("$") ||
                inputName.contains("%") || inputName.contains("^") || inputName.contains("&") || inputName.contains("*") ||
                inputName.contains("(") || inputName.contains(")") || inputName.contains("_") || inputName.contains("-") ||
                inputName.contains("{") || inputName.contains("}") || inputName.contains("[") || inputName.contains("]") ||
                inputName.contains(",") || inputName.contains(".") || inputName.contains("/") || inputName.contains("<") ||
                inputName.contains(">") || inputName.contains("?") || inputName.contains("|") || inputName.contains("\\")||
                inputName.contains("~") || inputName.contains("`") || inputName.contains("+") || inputName.contains("=") ||
                inputName.contains(":") || inputName.contains(";") || inputName.contains("\"") || inputName.contains("'")||
                inputName.contains("0") || inputName.contains("1") || inputName.contains("2") || inputName.contains("3") ||
                inputName.contains("4") || inputName.contains("5") || inputName.contains("6") || inputName.contains("7") ||
                inputName.contains("8") || inputName.contains("9");
    }

    public boolean checkNumber (String dayOrYear, String type){
        if (dayOrYear.equals("")|| dayOrYear.contains("!") || dayOrYear.contains("@") || dayOrYear.contains("#") || dayOrYear.contains("$") ||
                dayOrYear.contains("%") || dayOrYear.contains("^") || dayOrYear.contains("&") || dayOrYear.contains("*") ||
                dayOrYear.contains("(") || dayOrYear.contains(")") || dayOrYear.contains("_") || dayOrYear.contains("-") ||
                dayOrYear.contains("{") || dayOrYear.contains("}") || dayOrYear.contains("[") || dayOrYear.contains("]") ||
                dayOrYear.contains(",") || dayOrYear.contains(".") || dayOrYear.contains("/") || dayOrYear.contains("<") ||
                dayOrYear.contains(">") || dayOrYear.contains("?") || dayOrYear.contains("|") || dayOrYear.contains("\\")||
                dayOrYear.contains(":") || dayOrYear.contains(";") || dayOrYear.contains("\"")|| dayOrYear.contains("'") ||
                dayOrYear.contains("~") || dayOrYear.contains("`") || dayOrYear.contains("+") || dayOrYear.contains("=") ||
                dayOrYear.contains(" ") || dayOrYear.matches("\\w*([a-z,A-Z])\\w*")) return false;
        int convertCheck = Integer.parseInt(dayOrYear);
        if (type.equals("height") && convertCheck < 50 || convertCheck > 300) return false;
        else if (type.equals("weight") && convertCheck < 10) return false;
        else if (type.equals("statistics") && convertCheck < 100 ) return true;
        else if (type.equals("day") && convertCheck < 1 || convertCheck > 34 ) return false;
        else return !type.equals("year") || convertCheck >= 0;
    }


    public boolean checkIfTheSameCategory (String characterData, String type){
        switch (type) {
            case "apperance": {
                List<String> apperanceCheck = apperanceService.findByNameIn(Arrays.asList(characterData.split(","))).stream().map(Apperance::getType).collect(Collectors.toList());
                return apperanceCheck.stream().distinct().count() == apperanceCheck.size(); }
            case "personality": {
                List<String> personalityCheck = personalityService.findByNameIn(Arrays.asList(characterData.split(","))).stream().map(Personality::getType).collect(Collectors.toList());
                return personalityCheck.stream().distinct().count() == personalityCheck.size();}
            case "emotion": {
                List<String> emotionCheck = emotionService.findByNameIn(Arrays.asList(characterData.split(","))).stream().map(Emotion::getType).collect(Collectors.toList());
                return emotionCheck.stream().distinct().count() == emotionCheck.size();}
        }
        return true;
    }

    public boolean baseStatisticCheck(CharacterInputDto characterInputDto){
        return  checkNumber(characterInputDto.getBaseWeaponSkills(), "statistics") && checkNumber(characterInputDto.getBaseBallisticSkills(), "statistics") &&
                checkNumber(characterInputDto.getBaseStrength(), "statistics") && checkNumber(characterInputDto.getBaseToughness(), "statistics") &&
                checkNumber(characterInputDto.getBaseAgility(), "statistics") && checkNumber(characterInputDto.getBaseIntelligence(), "statistics") &&
                checkNumber(characterInputDto.getBaseWillPower(), "statistics") && checkNumber(characterInputDto.getBaseFellowship(), "statistics") &&
                checkNumber(characterInputDto.getBaseAttacks(), "statistics") && checkNumber(characterInputDto.getBaseWounds(), "statistics") &&
                checkNumber(characterInputDto.getBaseMagic(), "statistics") && checkNumber(characterInputDto.getBaseMovement(), "statistics");
    }

    public boolean endStatisticCheck(CharacterInputDto characterInputDto){
        return  checkNumber(characterInputDto.getEndWeaponSkills(), "statistics") && checkNumber(characterInputDto.getEndBallisticSkills(), "statistics") &&
                checkNumber(characterInputDto.getEndStrength(), "statistics") && checkNumber(characterInputDto.getEndToughness(), "statistics") &&
                checkNumber(characterInputDto.getEndAgility(), "statistics") && checkNumber(characterInputDto.getEndIntelligence(), "statistics") &&
                checkNumber(characterInputDto.getEndWillPower(), "statistics") && checkNumber(characterInputDto.getEndFellowship(), "statistics") &&
                checkNumber(characterInputDto.getEndAttacks(), "statistics") && checkNumber(characterInputDto.getEndWounds(), "statistics") &&
                checkNumber(characterInputDto.getEndMagic(), "statistics") && checkNumber(characterInputDto.getEndMovement(), "statistics");
    }

    public Statistics baseStatisticsConvert(CharacterInputDto characterInputDto) {
        if (characterInputDto.getBaseWeaponSkills() == null || characterInputDto.getBaseBallisticSkills() == null ||
                characterInputDto.getBaseStrength() == null || characterInputDto.getBaseToughness() == null ||
                characterInputDto.getBaseAgility() == null || characterInputDto.getBaseIntelligence() == null ||
                characterInputDto.getBaseWillPower() == null || characterInputDto.getBaseFellowship() == null ||
                characterInputDto.getBaseAttacks() == null || characterInputDto.getBaseWounds() == null ||
                characterInputDto.getBaseMagic() == null || characterInputDto.getBaseMovement() == null
        ) throw new CharacterSaveException("Brak statystyki bazowej.", new IllegalArgumentException());

        Statistics newStatistics = new Statistics();

        if (characterInputDto.getBaseWeaponSkills().matches("[0-9]+")) {
            int baseWeaponSkills = Integer.parseInt(characterInputDto.getBaseWeaponSkills());
            if (baseWeaponSkills < 100) newStatistics.setWeaponSkill(baseWeaponSkills);
            else throw new CharacterSaveException("Za duża liczba dla bazowej walki wręcz.", new IllegalArgumentException());
        } else throw new CharacterSaveException("Niepoprawny format dla bazowej walki wręcz.", new IllegalArgumentException());


        if (characterInputDto.getBaseBallisticSkills().matches("[0-9]+")) {
            int baseBallisticSkills = Integer.parseInt(characterInputDto.getBaseBallisticSkills());
            if (baseBallisticSkills < 100) newStatistics.setBallisticSkill(baseBallisticSkills);
            else throw new CharacterSaveException("Za duża liczba dla bazowych umiejętności strzeleckich.", new IllegalArgumentException());
        } else throw new CharacterSaveException("Niepoprawny format dla bazowych umiejętności strzeleckich.", new IllegalArgumentException());


        if (characterInputDto.getBaseStrength().matches("[0-9]+")) {
            int baseStrength = Integer.parseInt(characterInputDto.getBaseStrength());
            if (baseStrength < 100) newStatistics.setStrength(baseStrength);
            else throw new CharacterSaveException("Za duża liczba dla bazowej krzepy.", new IllegalArgumentException());
        } else throw new CharacterSaveException("Niepoprawny format dla bazowej krzepy.", new IllegalArgumentException());


        if (characterInputDto.getBaseToughness().matches("[0-9]+")) {
            int baseToughness = Integer.parseInt(characterInputDto.getBaseToughness());
            if (baseToughness < 100) newStatistics.setToughness(baseToughness);
            else throw new CharacterSaveException("Za duża liczba dla bazowej odporności.", new IllegalArgumentException());
        } else throw new CharacterSaveException("Niepoprawny format dla bazowej odporności.", new IllegalArgumentException());

        if (characterInputDto.getBaseAgility().matches("[0-9]+")) {
            Integer baseAgility = Integer.parseInt(characterInputDto.getBaseAgility());
            if (baseAgility < 100) newStatistics.setAgility(baseAgility);
            else throw new CharacterSaveException("Za duża liczba dla bazowej zręczności.", new IllegalArgumentException());
        } else throw new CharacterSaveException("Niepoprawny format dla bazowej zręczności.", new IllegalArgumentException());

        if (characterInputDto.getBaseIntelligence().matches("[0-9]+")) {
            int baseIntelligence = Integer.parseInt(characterInputDto.getBaseIntelligence());
            if (baseIntelligence < 100) newStatistics.setIntelligence(baseIntelligence);
            else throw new CharacterSaveException("Za duża liczba dla bazowej inteligencji.", new IllegalArgumentException());
        } else throw new CharacterSaveException("Niepoprawny format dla bazowej inteligencji.", new IllegalArgumentException());

        if (characterInputDto.getBaseWillPower().matches("[0-9]+")) {
            int baseWillPower = Integer.parseInt(characterInputDto.getBaseWillPower());
            if (baseWillPower < 100) newStatistics.setWillPower(baseWillPower);
            else throw new CharacterSaveException("Za duża liczba dla bazowej siły woli.", new IllegalArgumentException());
        } else throw new CharacterSaveException("Niepoprawny format dla bazowej siły woli.", new IllegalArgumentException());

        if (characterInputDto.getBaseFellowship().matches("[0-9]+")) {
            int baseFellowship = Integer.parseInt(characterInputDto.getBaseFellowship());
            if (baseFellowship < 100) newStatistics.setFellowship(baseFellowship);
            else throw new CharacterSaveException("Za duża liczba dla bazowej ogłady.", new IllegalArgumentException());
        } else throw new CharacterSaveException("Niepoprawny format dla bazowej ogłady.", new IllegalArgumentException());

        if (characterInputDto.getBaseAttacks().matches("[0-9]+")) {
            int baseAttacks = Integer.parseInt(characterInputDto.getBaseAttacks());
            if (baseAttacks < 100) newStatistics.setAttacks(baseAttacks);
            else throw new CharacterSaveException("Za duża liczba dla bazowego ataku.", new IllegalArgumentException());
        } else throw new CharacterSaveException("Niepoprawny format dla bazowego ataku.", new IllegalArgumentException());

        if (characterInputDto.getBaseWounds().matches("[0-9]+")) {
            int baseWounds = Integer.parseInt(characterInputDto.getBaseWounds());
            if (baseWounds < 100) newStatistics.setWounds(baseWounds);
            else throw new CharacterSaveException("Za duża liczba dla bazowej żywotności.", new IllegalArgumentException());
        } else throw new CharacterSaveException("Niepoprawny format dla bazowej żywotności.", new IllegalArgumentException());

        if (characterInputDto.getBaseMovement().matches("[0-9]+")) {
            int baseMovement = Integer.parseInt(characterInputDto.getBaseMovement());
            if (baseMovement < 100) newStatistics.setMovement(baseMovement);
            else throw new CharacterSaveException("Za duża liczba dla bazowej szybkości.", new IllegalArgumentException());
        } else throw new CharacterSaveException("Niepoprawny format dla bazowej szybkości.", new IllegalArgumentException());

        if (characterInputDto.getBaseMagic().matches("[0-9]+")) {
            int baseMagic = Integer.parseInt(characterInputDto.getBaseMagic());
            if (baseMagic < 100) newStatistics.setMagic(baseMagic);
            else throw new CharacterSaveException("Za duża liczba dla bazowej magii.", new IllegalArgumentException());
        } else throw new CharacterSaveException("Niepoprawny format dla bazowej magii.", new IllegalArgumentException());

        return newStatistics;
    }
}
