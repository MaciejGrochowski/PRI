package com.example.PRI.services.character;

import com.example.PRI.dtos.characters.CharacterInputDto;
import com.example.PRI.entities.ImperialDate;
import com.example.PRI.entities.Place;
import com.example.PRI.entities.character.*;
import com.example.PRI.entities.character.Character;
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
        Optional<Surname> surname = surnameService.findBySurname(surNew);
        if (surNew == null) {
            return new Surname();
        }
        if (surname.isPresent()) return surname.get();
        if (surNew.matches(".*\\d.*")) throw new CharacterSaveException("W nazwisku znajduje się liczba.", new IllegalArgumentException());
        if (surNew.matches("[a-z].*")){
            String firstLetter = surNew.substring(0, 1).toUpperCase();
            String newSurnameWithBigFirstLetter = firstLetter + surNew.substring(1);
            Surname nextSurname = new Surname();
            nextSurname.setSurname(newSurnameWithBigFirstLetter);
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
        if (inputName == null)
            throw new CharacterSaveException("Podaj imię postaci swojej postaci.", new IllegalArgumentException());
        Optional<Name> nameOptional = nameService.findByName(inputName);
        if (nameOptional.isPresent()) return nameOptional.get();
        if (inputName.matches(".*\\d.*")) throw new CharacterSaveException("W imieniu znajduje się liczba.", new IllegalArgumentException());
        if (inputName.matches("[a-z].*")) {
            String firstLetter = inputName.substring(0, 1).toUpperCase();
            String newNameWithBigFirstLetter = firstLetter + inputName.substring(1);
            Name nextName = new Name();
            nextName.setName(newNameWithBigFirstLetter);
            nameService.save(nextName);
            return nextName;
        } else {
            Name nameNew = new Name();
            nameNew.setName(inputName);
            nameService.save(nameNew);
            return nameNew;
        }
    }

    public Prediction predictionConvert(String inputPrediction) {
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
        Career currentCareer = careerService.findByName(inputCurrentCareer);
        if (currentCareer != null) return currentCareer;
        else throw new CharacterSaveException("Wybierz profesje swojej postaci.", new IllegalArgumentException());
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
        else throw new CharacterSaveException("Wybierz kolor włosów swojej postaci.", new IllegalArgumentException());
    }

    public EyeColor eyeColorConverter(String inputEyeColor) {
        Optional<EyeColor> eyeColor = eyeColorService.findByName(inputEyeColor);
        if (eyeColor.isPresent()) return eyeColor.get();
        else throw new CharacterSaveException("Wybierz kolor oczu swojej postaci.", new IllegalArgumentException());
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
        if (dayInput == null) throw new CharacterSaveException("Podaj dzień urodzin twojej postaci.", new IllegalArgumentException());
        int day = 0;
        if (dayInput.matches("[0-9]+")) {
            day = Integer.parseInt(dayInput);
            if (day < 1 || day > 34) throw new CharacterSaveException("Niepoprawny dzień urodzenia", new IllegalArgumentException());
        } else throw new CharacterSaveException("Niepoprawny format dnia.", new IllegalArgumentException());
        return day;
    }

    public int birthYearConverter(String yearInput) {
        if (yearInput == null) throw new CharacterSaveException("Podaj rok urodzin twojej postaci.", new IllegalArgumentException());
        int year = 0;
        if (yearInput.matches("[0-9]*")) {
            year = Short.parseShort(yearInput);
            if (year < 0 || year > 3000) throw new CharacterSaveException("Niepoprawny rok urodzenia", new IllegalArgumentException());
        } else throw new CharacterSaveException("Niepoprawny format roku.", new IllegalArgumentException());
        return year;
    }

    public Month birthMonthConverter(String birthMonthInput) {
        if (birthMonthInput == null) throw new CharacterSaveException("Podaj miesiąc urodzin twojej postaci.", new IllegalArgumentException());
        return Month.findByMonthName(birthMonthInput);
    }

    public ImperialDate imperialDateConverter(String day, String month, String year) {
        ImperialDate newDate = new ImperialDate(birthDayConverter(day), birthMonthConverter(month), birthYearConverter(year));
        imperialDateService.save(newDate);
        return newDate;
    }

    private StarSign saveStarSign(Character character, ImperialDate date) {
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
                if(day >= startDay && day < endDay){
                    characterStarSign = starSign;
                    break;
                }
            }
            else{
                if((startMonth.equals(month) && day >= startDay) || (endMonth.equals(month) && day < endDay) ){
                    characterStarSign = starSign;
                    break;
                }
            }
        }
        character.setStarSign(characterStarSign);
        return characterStarSign;
    }

    public Integer heightConverter(String height) {
        if (height == null) throw new CharacterSaveException("Podaj wzrost twojej postaci.", new IllegalArgumentException());
        Integer newHeight = null;
        if (height.matches("[0-9]*")) {
            newHeight = Integer.parseInt(height);
            if (newHeight < 50 || newHeight > 300) throw new CharacterSaveException("Podaj poprawny wzrost twojej postaci.", new IllegalArgumentException());
        } else throw new CharacterSaveException("Niepoprawny format wzrostu.", new IllegalArgumentException());
        return newHeight;
    }

    public Integer weightConverter(String weight) {
        if (weight == null) throw new CharacterSaveException("Podaj wagę twojej postaci.", new IllegalArgumentException());
        Integer newWeight = null;
        if (weight.matches("[0-9]*")) {
            newWeight = Integer.parseInt(weight);
            if (newWeight < 10 || newWeight > 800) throw new CharacterSaveException("Podaj poprawną wagę twojej postaci.", new IllegalArgumentException());
        } else throw new CharacterSaveException("Niepoprawny format wagi.", new IllegalArgumentException());
        return newWeight;
    }

    public List<Personality> personalityListConvert(String inputPersonality) {
        if (inputPersonality == null) return null;
        List<String> stringList = Arrays.asList(inputPersonality.split(","));
        List<Personality> personalityList = personalityService.findByNameIn(stringList);
        if (personalityList.size() > 5) throw new CharacterSaveException("Zbyt dużo cech charakteru.\nMaksymalna liczba cech: 5", new IllegalArgumentException());
        List<String> personalityCheck = personalityList.stream().map(Personality::getType).collect(Collectors.toList());
        if(personalityCheck.stream().distinct().count() <personalityCheck.size()) throw new CharacterSaveException("Cechy charakteru twojej postaci się wykluczają.", new IllegalArgumentException());
        return  personalityList;
    }

    public List<Apperance> apperanceConvert(String inputApperance) {
        if (inputApperance == null) return null;
        List<String> stringList = Arrays.asList(inputApperance.split(","));
        List<Apperance> apperanceList = apperanceService.findByNameIn(stringList);
        if (apperanceList.size() > 5) throw new CharacterSaveException("Zbyt dużo cech wyglądu.\nMaksymalna liczba cech: 5", new IllegalArgumentException());
        List<String> apperanceCheck = apperanceList.stream().map(Apperance::getType).collect(Collectors.toList());
        if(apperanceCheck.stream().distinct().count() <apperanceCheck.size()) throw new CharacterSaveException("Cechy wyglądu twojej postaci się wykluczają.", new IllegalArgumentException());
        return apperanceList;
    }

    public List<Skill> skillsConvert(String inputSkills) {
        if (inputSkills == null) return null;
        List<String> stringList = Arrays.asList(inputSkills.split(","));
        List<Skill> skillList = skillService.findByNameIn(stringList.stream().map(s -> s.split(" \\+")[0]).collect(Collectors.toList()));
        List<Skill> characterSkills = new ArrayList<>();
        for(String skillString : stringList){
            String name = skillString.split(" \\+")[0];
            String level = skillString.split(" \\+")[1];
            Optional<Skill> maybeSkill = skillList.stream().filter(s -> s.getName().equals(name) && s.getLevel().toString().equals(level)).findFirst();
            maybeSkill.ifPresent(characterSkills::add);
        }
        if (characterSkills.size() > 0)  return skillList;
        else throw new CharacterSaveException("Podaj poprawne umiejętności.", new IllegalArgumentException());
    }

    public List<Talent> talentsConvert(String inputTalents) {
        if (inputTalents == null) return null;
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
        if(emotionCheck.stream().distinct().count() <emotionCheck.size()) throw new CharacterSaveException("Emocje twojej postaci się wykluczają.", new IllegalArgumentException());
        return emotionList;
    }

    public Integer endWeaponSkillConvert(String WeaponSkill, Character character) {
        if (WeaponSkill == null) throw new IllegalArgumentException();
        Integer newWeaponSkill = null;
        if (WeaponSkill.matches("[0-9]+")) {
            newWeaponSkill = Integer.parseInt(WeaponSkill);
            if (newWeaponSkill < 100) character.setEndWeaponSkills(newWeaponSkill);
            else throw new IllegalArgumentException();
        } else throw new IllegalArgumentException();

        return newWeaponSkill;
    }

    public Integer endBallisticSkillsConvert(String BallisticSkills, Character character) {
        if (BallisticSkills == null) throw new IllegalArgumentException();
        Integer newBallisticSkill = null;
        if (BallisticSkills.matches("[0-9]+")) {
            newBallisticSkill = Integer.parseInt(BallisticSkills);
            if (newBallisticSkill < 100) character.setEndBallisticSkills(newBallisticSkill);
            else throw new IllegalArgumentException();
        } else throw new IllegalArgumentException();

        return newBallisticSkill;
    }

    public Integer endStrengthConvert(String endStrength, Character character) {
        if (endStrength == null) throw new IllegalArgumentException();
        Integer newEndStrength = null;
        if (endStrength.matches("[0-9]+")) {
            newEndStrength = Integer.parseInt(endStrength);
            if (newEndStrength < 100) character.setEndStrength(newEndStrength);
            else throw new IllegalArgumentException();
        } else throw new IllegalArgumentException();

        return newEndStrength;
    }

    public Integer endToughnessConvert(String endToughness, Character character) {
        if (endToughness == null) throw new IllegalArgumentException();
        Integer newEndToughness = null;
        if (endToughness.matches("[0-9]+")) {
            newEndToughness = Integer.parseInt(endToughness);
            if (newEndToughness < 100) character.setEndToughness(newEndToughness);
            else throw new IllegalArgumentException();
        } else throw new IllegalArgumentException();

        return newEndToughness;
    }

    public Integer endAgilityConvert(String endAgility, Character character) {
        if (endAgility == null) throw new IllegalArgumentException();
        Integer newEndAgility = null;
        if (endAgility.matches("[0-9]+")) {
            newEndAgility = Integer.parseInt(endAgility);
            if (newEndAgility < 100) character.setEndAgility(newEndAgility);
            else throw new IllegalArgumentException();
        } else throw new IllegalArgumentException();

        return newEndAgility;
    }

    public Integer endIntelligenceConvert(String endIntelligence, Character character) {
        if (endIntelligence == null) throw new IllegalArgumentException();
        Integer newEndIntelligence = null;
        if (endIntelligence.matches("[0-9]+")) {
            newEndIntelligence = Integer.parseInt(endIntelligence);
            if (newEndIntelligence < 100) character.setEndIntelligence(newEndIntelligence);
            else throw new IllegalArgumentException();
        } else throw new IllegalArgumentException();

        return newEndIntelligence;
    }

    public Integer endWillPowerConvert(String endWillPower, Character character) {
        if (endWillPower == null) throw new IllegalArgumentException();
        Integer newEndWillPower = null;
        if (endWillPower.matches("[0-9]+")) {
            newEndWillPower = Integer.parseInt(endWillPower);
            if (newEndWillPower < 100) character.setEndWillPower(newEndWillPower);
            else throw new IllegalArgumentException();
        } else throw new IllegalArgumentException();

        return newEndWillPower;
    }

    public Integer endFellowshipConvert(String endFellowship, Character character) {
        if (endFellowship == null) throw new IllegalArgumentException();
        Integer newEndFellowship = null;
        if (endFellowship.matches("[0-9]+")) {
            newEndFellowship = Integer.parseInt(endFellowship);
            if (newEndFellowship < 100) character.setEndFellowship(newEndFellowship);
            else throw new IllegalArgumentException();
        } else throw new IllegalArgumentException();

        return newEndFellowship;
    }

    public Integer endAttacksConvert(String endAttack, Character character) {
        if (endAttack == null) throw new IllegalArgumentException();
        Integer newEndAttack = null;
        if (endAttack.matches("[0-9]+")) {
            newEndAttack = Integer.parseInt(endAttack);
            if (newEndAttack < 100) character.setEndAttacks(newEndAttack);
            else throw new IllegalArgumentException();
        } else throw new IllegalArgumentException();

        return newEndAttack;
    }

    public Integer endWoundsConvert(String endWound, Character character) {
        if (endWound == null) throw new IllegalArgumentException();
        Integer newEndWound = null;
        if (endWound.matches("[0-9]+")) {
            newEndWound = Integer.parseInt(endWound);
            if (newEndWound < 100) character.setEndWounds(newEndWound);
            else throw new IllegalArgumentException();
        } else throw new IllegalArgumentException();

        return newEndWound;
    }

    public Integer endMovementConvert(String endMovement, Character character) {
        if (endMovement == null) throw new IllegalArgumentException();
        Integer newEndMovement = null;
        if (endMovement.matches("[0-9]+")) {
            newEndMovement = Integer.parseInt(endMovement);
            if (newEndMovement < 100) character.setEndMovement(newEndMovement);
            else throw new IllegalArgumentException();
        } else throw new IllegalArgumentException();

        return newEndMovement;
    }

    public Integer endMagicConvert(String endMagic, Character character) {
        if (endMagic == null) throw new IllegalArgumentException();
        Integer newEndMagic = null;
        if (endMagic.matches("[0-9]+")) {
            newEndMagic = Integer.parseInt(endMagic);
            if (newEndMagic < 100) character.setEndMagic(newEndMagic);
            else throw new IllegalArgumentException();
        } else throw new IllegalArgumentException();

        return newEndMagic;
    }

    public Statistics baseStatisticsConvert(CharacterInputDto characterInputDto, Character character) {
        if (characterInputDto.getBaseWeaponSkills() == null || characterInputDto.getBaseBallisticSkills() == null ||
                characterInputDto.getBaseStrength() == null || characterInputDto.getBaseToughness() == null ||
                characterInputDto.getBaseAgility() == null || characterInputDto.getBaseIntelligence() == null ||
                characterInputDto.getBaseWillPower() == null || characterInputDto.getBaseFellowship() == null ||
                characterInputDto.getBaseAttacks() == null || characterInputDto.getBaseWounds() == null ||
                characterInputDto.getBaseMagic() == null || characterInputDto.getBaseMovement() == null
        ) throw new IllegalArgumentException();

        Statistics newStatistics = new Statistics();


        if (characterInputDto.getBaseWeaponSkills().matches("[0-9]+")) {
            Integer baseWeaponSkills = Integer.parseInt(characterInputDto.getBaseWeaponSkills());
            if (baseWeaponSkills < 100) newStatistics.setWeaponSkill(baseWeaponSkills);
            else throw new IllegalArgumentException();
        } else throw new IllegalArgumentException();


        if (characterInputDto.getBaseBallisticSkills().matches("[0-9]+")) {
            Integer baseBallisticSkills = Integer.parseInt(characterInputDto.getBaseBallisticSkills());
            if (baseBallisticSkills < 100) newStatistics.setBallisticSkill(baseBallisticSkills);
            else throw new IllegalArgumentException();
        } else throw new IllegalArgumentException();


        if (characterInputDto.getBaseStrength().matches("[0-9]+")) {
            Integer baseStrength = Integer.parseInt(characterInputDto.getBaseStrength());
            if (baseStrength < 100) newStatistics.setStrength(baseStrength);
            else throw new IllegalArgumentException();
        } else throw new IllegalArgumentException();


        if (characterInputDto.getBaseToughness().matches("[0-9]+")) {
            Integer baseToughness = Integer.parseInt(characterInputDto.getBaseToughness());
            if (baseToughness < 100) newStatistics.setToughness(baseToughness);
            else throw new IllegalArgumentException();
        } else throw new IllegalArgumentException();

        if (characterInputDto.getBaseAgility().matches("[0-9]+")) {
            Integer baseAgility = Integer.parseInt(characterInputDto.getBaseAgility());
            if (baseAgility < 100) newStatistics.setAgility(baseAgility);
            else throw new IllegalArgumentException();
        } else throw new IllegalArgumentException();

        if (characterInputDto.getBaseIntelligence().matches("[0-9]+")) {
            Integer baseIntelligence = Integer.parseInt(characterInputDto.getBaseIntelligence());
            if (baseIntelligence < 100) newStatistics.setIntelligence(baseIntelligence);
            else throw new IllegalArgumentException();
        } else throw new IllegalArgumentException();

        if (characterInputDto.getBaseWillPower().matches("[0-9]+")) {
            Integer baseWillPower = Integer.parseInt(characterInputDto.getBaseWillPower());
            if (baseWillPower < 100) newStatistics.setWillPower(baseWillPower);
            else throw new IllegalArgumentException();
        } else throw new IllegalArgumentException();

        if (characterInputDto.getBaseFellowship().matches("[0-9]+")) {
            Integer baseFellowship = Integer.parseInt(characterInputDto.getBaseFellowship());
            if (baseFellowship < 100) newStatistics.setFellowship(baseFellowship);
            else throw new IllegalArgumentException();
        } else throw new IllegalArgumentException();

        if (characterInputDto.getBaseAttacks().matches("[0-9]+")) {
            Integer baseAttacks = Integer.parseInt(characterInputDto.getBaseAttacks());
            if (baseAttacks < 100) newStatistics.setAttacks(baseAttacks);
            else throw new IllegalArgumentException();
        } else throw new IllegalArgumentException();

        if (characterInputDto.getBaseWounds().matches("[0-9]+")) {
            Integer baseWounds = Integer.parseInt(characterInputDto.getBaseWounds());
            if (baseWounds < 100) newStatistics.setWounds(baseWounds);
            else throw new IllegalArgumentException();
        } else throw new IllegalArgumentException();

        if (characterInputDto.getBaseMovement().matches("[0-9]+")) {
            Integer baseMovement = Integer.parseInt(characterInputDto.getBaseMovement());
            if (baseMovement < 100) newStatistics.setMovement(baseMovement);
            else throw new IllegalArgumentException();
        } else throw new IllegalArgumentException();

        if (characterInputDto.getBaseMagic().matches("[0-9]+")) {
            Integer baseMagic = Integer.parseInt(characterInputDto.getBaseMagic());
            if (baseMagic < 100) newStatistics.setMagic(baseMagic);
            else throw new IllegalArgumentException();
        } else throw new IllegalArgumentException();

        character.setBaseStats(newStatistics);
        statisticsService.save(newStatistics);
        return newStatistics;
    }
}
