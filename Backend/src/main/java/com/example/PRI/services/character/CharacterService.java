package com.example.PRI.services.character;

import com.example.PRI.converters.CharacterConverter;
import com.example.PRI.dtos.characters.*;
import com.example.PRI.entities.Place;
import com.example.PRI.entities.character.*;
import com.example.PRI.entities.character.Character;
import com.example.PRI.enums.Race;
import com.example.PRI.enums.Religion;
import com.example.PRI.enums.Sex;
import com.example.PRI.enums.StarSign;
import com.example.PRI.repositories.character.CharacterRepository;
import com.example.PRI.repositories.character.NameRepository;
import com.example.PRI.services.GeneralService;
import com.example.PRI.services.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CharacterService extends GeneralService {

    @Autowired
    CharacterRepository characterRepository;

    @Autowired
    NameService nameService;



    @Autowired
    SurnameService surnameService;

    @Autowired
    CareerService careerService;

    @Autowired
    PlaceService placeService;

    @Autowired
    HairColorService hairColorService;

    @Autowired
    PersonalityService personalityService;

    @Autowired
    EmotionService emotionService;

    @Autowired
    TalentService talentService;

    @Autowired
    PredictionService predictionService;

    @Autowired
    EyeColorService eyeColorService;

    @Autowired
    SkillService skillService;

    @Autowired
    StatisticsService statisticsService;

    @Autowired
    ApperanceService apperanceService;

    public List<CharacterDefaultAttributesOutputDto> getAllCharacters() {
        Iterable<Character> characters = characterRepository.findAll();
        List<CharacterDefaultAttributesOutputDto> output = new ArrayList<>();

        for (Character character : characters) {
            output.add(CharacterConverter.convert(character));
        }
        return output;
    }

    public void save(Character character) {
        nameService.save(character.getName());

        careerService.save(character.getCurrentCareer());
//        placeService.save(character.getLivePlace());
        if (character.getSurname() != null) surnameService.save(character.getSurname());
        characterRepository.save(character);
    }

    public void saveExampleCharacters() {
        List<Name> names = new ArrayList<>();
        names.add(new Name("Wolfgang", true, false, false, true, false, false, false, 0.1, 0.2));
        names.add(new Name("Johann", true, false, false, true, false, false, false, 0.1, 0.2));
        names.add(new Name("Hans", true, false, false, true, false, false, false, 0.1, 0.2));
        names.add(new Name("Waldemar", true, false, false, true, false, false, false, 0.1, 0.2));
        names.add(new Name("Otto", true, false, false, true, false, false, false, 0.1, 0.2));
        names.add(new Name("Dieter", true, false, false, true, false, false, false, 0.1, 0.2));
        names.add(new Name("Felix", true, false, false, true, false, false, false, 0.1, 0.2));
        names.add(new Name("Magnus", true, false, false, true, false, false, false, 0.1, 0.2));

        Statistics stats = new Statistics(0,0,0,0,0,0,0,0,0,0,0,0);

        statisticsService.save(stats);

        List<Career> careers = new ArrayList<>();
        Career c1 = new Career();
        c1.setName("Chłop");
        c1.setBaseProfession(true);
        c1.setExitChance(0.1);
        c1.setStatistics(stats);
        careers.add(c1);
        Career c2 = new Career();
        c2.setName("Żołnierz");
        c1.setBaseProfession(true);
        c1.setExitChance(0.1);
        c2.setStatistics(stats);
        careers.add(c2);
        Career c3 = new Career();
        c3.setName("Mieszczanin");
        c3.setBaseProfession(true);
        c3.setExitChance(0.1);
        c3.setStatistics(stats);
        careers.add(c3);
        Career c4 = new Career();
        c4.setName("Rzemieślnik");
        c4.setBaseProfession(true);
        c4.setExitChance(0.1);
        c4.setStatistics(stats);
        careers.add(c4);
        Career c5 = new Career();
        c5.setName("Oprych");
        c5.setBaseProfession(true);
        c5.setExitChance(0.1);
        c5.setStatistics(stats);
        careers.add(c5);
        Career c6 = new Career();
        c6.setName("Sługa");
        c6.setBaseProfession(true);
        c6.setExitChance(0.1);
        c6.setStatistics(stats);
        careers.add(c6);

        List<Place> places = new ArrayList<>();

        Place p1 = new Place();
        p1.setName("Altdorf");
        places.add(p1);
        placeService.save(p1);
        Place p2 = new Place();
        p2.setName("Nuln");
        places.add(p2);
        placeService.save(p2);
        Place p3 = new Place();
        p3.setName("Averheim");
        placeService.save(p3);
        places.add(p3);


        List<Talent> talents = new ArrayList<>();
        Talent t = new Talent("hamster", "hamster");
        talentService.save(t);
        Talent t2 = new Talent("strateg", "strateg");
        talentService.save(t2);
        Talent t3 = new Talent("wizjoner", "wizjoner");
        talentService.save(t3);
        Talent t4 = new Talent("dowodzenie", "dowodzenie");
        talentService.save(t4);
        Talent t5 = new Talent("komunikatywność", "komunikatywność");
        talentService.save(t5);
        Talent t6 = new Talent("szczęściarz", "szczęściarz");
        talentService.save(t6);
        Talent t7 = new Talent("szybka regeneracja", "szybka regeneracja");
        talentService.save(t7);
        Talent t8 = new Talent("urok", "urok");
        talentService.save(t8);
        Talent t9 = new Talent("refleks", "refleks");
        talentService.save(t9);
        Talent t10 = new Talent("pomysłowość", "pomysłowość");
        talentService.save(t10);

        talents.add(t);
        talents.add(t2);
        talents.add(t3);
        talents.add(t4);
        talents.add(t5);
        talents.add(t6);
        talents.add(t7);
        talents.add(t8);
        talents.add(t9);
        talents.add(t10);


        List<Skill> skills = new ArrayList<>();
        Skill s1 = new Skill("smażenie pysznych frytek", 1);
        skillService.save(s1);
        Skill s2 = new Skill("plotkowanie", 0);
        skillService.save(s2);
        Skill s3 = new Skill("tropienie", 4);
        skillService.save(s3);
        Skill s4 = new Skill("szycie", 0);
        skillService.save(s4);
        Skill s5 = new Skill("szybkie pisanie", 6);
        skillService.save(s5);
        Skill s6 = new Skill("ręce które piszą kod", 7);
        skillService.save(s6);
        Skill s7 = new Skill("przywoływanie piesełów", 1);
        skillService.save(s7);
        Skill s8 = new Skill("wołanie o pomoc", 2);
        skillService.save(s8);

        skills.add(s1);
        skills.add(s2);
        skills.add(s3);
        skills.add(s4);
        skills.add(s5);
        skills.add(s6);
        skills.add(s7);
        skills.add(s8);

        List<Emotion> emotions = new ArrayList<>();
        Emotion e1 = new Emotion("gniew");
        emotionService.save(e1);
        Emotion e2 = new Emotion("radość");
        emotionService.save(e2);
        Emotion e3 = new Emotion("frustracja");
        emotionService.save(e3);
        Emotion e4 = new Emotion("nienawiść");
        emotionService.save(e4);
        Emotion e5 = new Emotion("samotność");
        emotionService.save(e5);
        Emotion e6 = new Emotion("nerwowość");
        emotionService.save(e6);
        Emotion e7 = new Emotion("euforia");
        emotionService.save(e7);
        Emotion e8 = new Emotion("rozpacz");
        emotionService.save(e8);
        Emotion e9 = new Emotion("strach");
        emotionService.save(e9);

        emotions.add(e1);
        emotions.add(e2);
        emotions.add(e3);
        emotions.add(e4);
        emotions.add(e5);
        emotions.add(e6);
        emotions.add(e7);
        emotions.add(e8);
        emotions.add(e9);

        List<EyeColor> eyecolors = new ArrayList<>();
        EyeColor ey1 = new EyeColor("niebieskie", 0.8, 0.1, 0.4, 0.3);
        eyeColorService.save(ey1);
        EyeColor ey2 = new EyeColor("brązowe", 0.3, 0.8, 0.6, 0.4);
        eyeColorService.save(ey2);
        EyeColor ey3 = new EyeColor("zielone", 0.5, 0.2, 0.2, 0.7);
        eyeColorService.save(ey3);
        EyeColor ey4 = new EyeColor("czerwone", 0.1, 0.1, 0.0, 0.8);
        eyeColorService.save(ey4);
        EyeColor ey5 = new EyeColor("złote", 0.3, 0.1, 0.0, 0.7);
        eyeColorService.save(ey5);

        eyecolors.add(ey1);
        eyecolors.add(ey2);
        eyecolors.add(ey3);
        eyecolors.add(ey4);
        eyecolors.add(ey5);

        List<HairColor> haircolors = new ArrayList<>();
        HairColor hai1 = new HairColor("blond", 0.9, 0.2, 0.3, 0.1);
        hairColorService.save(hai1);
        HairColor hai2 = new HairColor("czarne", 0.9, 0.2, 0.3, 0.1);
        hairColorService.save(hai2);
        HairColor hai3 = new HairColor("brązowe", 0.3, 0.9, 0.6, 0.7);
        hairColorService.save(hai3);
        HairColor hai4 = new HairColor("białe", 0.5, 0.4, 0.1, 0.2);
        hairColorService.save(hai4);
        HairColor hai5 = new HairColor("rude", 0.2, 0.5, 0.1, 0.1);
        hairColorService.save(hai5);

        haircolors.add(hai1);
        haircolors.add(hai2);
        haircolors.add(hai3);
        haircolors.add(hai4);
        haircolors.add(hai5);

        List<Apperance> apperances = new ArrayList<>();
        Apperance ap1 = new Apperance("blizna pod okiem", "test", 0.1);
        apperanceService.save(ap1);
        Apperance ap2 = new Apperance("brak ręki","test", 0.3);
        apperanceService.save(ap2);
        Apperance ap3 = new Apperance("znamię na nodze","test", 0.2);
        apperanceService.save(ap3);
        Apperance ap4 = new Apperance("blizna na plecach", "test", 0.5);
        apperanceService.save(ap4);
        Apperance ap5 = new Apperance("długie rzęsy", "test", 0.4);
        apperanceService.save(ap5);

        apperances.add(ap1);
        apperances.add(ap2);
        apperances.add(ap3);
        apperances.add(ap4);
        apperances.add(ap5);

        List<Personality> personalities = new ArrayList<>();
        Personality per1 = new Personality("złośliwy", 0.3);
        personalityService.save(per1);
        Personality per2 = new Personality("wesoły", 0.2);
        personalityService.save(per2);
        Personality per3 = new Personality("optymista", 0.5);
        personalityService.save(per3);
        Personality per4 = new Personality("pesymista", 0.6);
        personalityService.save(per4);
        Personality per5 = new Personality("lekkoduch", 0.8);
        personalityService.save(per5);

        personalities.add(per1);
        personalities.add(per2);
        personalities.add(per3);
        personalities.add(per4);
        personalities.add(per5);

        List<Prediction> predictions = new ArrayList<>();
        Prediction pre1 = new Prediction("Wystrzegaj się lisiego futra", 0.4);
        predictionService.save(pre1);
        Prediction pre2 = new Prediction("Największe szczęście spoczywa w morskich głębinach", 0.1);
        predictionService.save(pre2);
        Prediction pre3 = new Prediction("Zguba twa cztery kopyta ma", 0.7);
        predictionService.save(pre3);
        Prediction pre4 = new Prediction("Zagrożenie zawsze czai się niedaleko", 0.2);
        predictionService.save(pre4);

        predictions.add(pre1);
        predictions.add(pre2);
        predictions.add(pre3);
        predictions.add(pre4);

        List<Surname> surnames = new ArrayList<>();
        Surname sur1 = new Surname("Mohl", true, true, true, true, false, false, false, false, 0.5);
        surnameService.save(sur1);
        surnames.add(sur1);
        Surname sur2 = new Surname("Keffermüller", true, true, true, true, false, true, true, false, 0.2);
        surnameService.save(sur2);
        surnames.add(sur2);
        Surname sur3 = new Surname("Krüger", true, true, true, true, true, false, false, false, 0.3);
        surnameService.save(sur3);
        surnames.add(sur3);
        Surname sur4 = new Surname("Vertalen", true, true, true, true, true, true, true, false, 0.6);
        surnameService.save(sur4);
        surnames.add(sur4);
        Surname sur5 = new Surname("Yorck von Wartenburg", true, true, true, true, false, false, false, false, 0.3);
        surnameService.save(sur5);
        surnames.add(sur5);


        Random rand = new Random();

        for (int i = 0; i < 100; i++) {
            Character c = new Character();
            if (rand.nextInt(5) < 3) c.setTalents(Collections.singletonList(t));
            c.setName(names.get(rand.nextInt(names.size())));
            c.setSurname(null);
            c.setCurrentCareer(careers.get(rand.nextInt(careers.size())));
            c.setSex(Sex.MALE);
            c.setRace(Race.HUMAN);
            c.setLivePlace(places.get(rand.nextInt(places.size())));
            c.setHeight(150 + rand.nextInt(50));
            c.setWeight(50 + rand.nextInt(50));
            c.setReligion(Religion.SIGMAR);

            c.setBirthPlace(places.get(rand.nextInt(places.size())));
            c.setTalents(Collections.singletonList(talents.get(rand.nextInt(talents.size()))));
            c.setSkills(Collections.singletonList(skills.get(rand.nextInt(skills.size()))));
            c.setDominatingEmotions(Collections.singletonList(emotions.get(rand.nextInt(emotions.size()))));
            c.setEyeColor(eyecolors.get(rand.nextInt(eyecolors.size())));
            c.setHairColor(haircolors.get(rand.nextInt(haircolors.size())));
            c.setApperance(Collections.singletonList(apperances.get(rand.nextInt(apperances.size()))));
            c.setPersonality(Collections.singletonList(personalities.get(rand.nextInt(personalities.size()))));
            c.setPrediction(predictions.get(rand.nextInt(predictions.size())));
            c.setSurname(surnames.get(rand.nextInt(surnames.size())));
            c.setStarSign(StarSign.DRAGOMAS_THE_DRAKE);
            this.save(c);
        }
    }

    @Autowired
    NameRepository nameRepository;


    /*
    POST: localhost:8080/app/characters/paged
    Example JSON to Send and test:
    {
        "sortedBy": "name",
            "isAscending": true,
            "filters": {"name": "Otto", "talents": "hamster"},
        "currentPage": 0,
            "rowsPerPage": 25
    }

     */
    public CharacterListOutputDto getSomeCharactersPaged(CharacterListFilterInputDto requestInfo) {
        Pageable pageable;
        if (requestInfo.getSortedBy() == null)
            pageable = PageRequest.of(requestInfo.getCurrentPage(), requestInfo.getRowsPerPage());
        else {
            if (requestInfo.getIsAscending())
                pageable = PageRequest.of(requestInfo.getCurrentPage(),
                        requestInfo.getRowsPerPage(), Sort.by(requestInfo.getSortedBy()).ascending());
            else
                pageable = PageRequest.of(requestInfo.getCurrentPage(),
                        requestInfo.getRowsPerPage(), Sort.by(requestInfo.getSortedBy()).descending());
        }

        Specification<Character> specifications = this.getSpecificationsFromFilter(requestInfo);

        Page<Character> charactersFilteredPage = characterRepository.findAll(specifications, pageable); //ToDo Konwersja typów

        charactersFilteredPage.forEach(c -> System.out.println(c.getName().getName() + ", " + c.getId() + " " + c.getTalents().size()));
        //Szybkie sprawdzenie czy ktoś w nowej wersji istnieje i

        CharacterListOutputDto output = new CharacterListOutputDto();
        List<CharacterDefaultAttributesOutputDto> outputData = new ArrayList<>();
        charactersFilteredPage.get().forEach(c -> outputData.add(CharacterConverter.convert(c)));

        output.setList(outputData);
        output.setTotalCount(charactersFilteredPage.getTotalElements());

        return output;
    }

    private Specification<Character> getSpecificationsFromFilter(CharacterListFilterInputDto requestInfo) {
        Specification<Character> specifications = CharacterSpecifications.getAll();
        if (requestInfo.getFilters() == null || requestInfo.getFilters().size() == 0) return specifications;

        if (requestInfo.getFilters().containsKey("name")) {
            Optional<Name> name = nameService.findByName(requestInfo.getFilters().get("name"));
            if (name.isPresent()) specifications = specifications.and(CharacterSpecifications.getByName(name.get()));
            else return specifications.and(CharacterSpecifications.GetNoone());
        }

        if (requestInfo.getFilters().containsKey("surname")) {
            Optional<Surname> surname = surnameService.findBySurname(requestInfo.getFilters().get("surname"));
            if (surname.isPresent())
                specifications = specifications.and(CharacterSpecifications.getBySurname(surname.get()));
            else return specifications.and(CharacterSpecifications.GetNoone());
        }

        if (requestInfo.getFilters().containsKey("race")) {
            Race race = Race.valueOf(requestInfo.getFilters().get("race"));
            specifications = specifications.and(CharacterSpecifications.getByRace(race));
        }

        if (requestInfo.getFilters().containsKey("eyeColor")) {
            Optional<EyeColor> eyeColor = eyeColorService.findByName(requestInfo.getFilters().get("eyeColor"));
            if (eyeColor.isPresent())
                specifications = specifications.and(CharacterSpecifications.getByEyeColor(eyeColor.get()));
            else return specifications.and(CharacterSpecifications.GetNoone());
        }

        if (requestInfo.getFilters().containsKey("hairColor")) {
            Optional<HairColor> hairColor = hairColorService.findByName(requestInfo.getFilters().get("hairColor"));
            if (hairColor.isPresent())
                specifications = specifications.and(CharacterSpecifications.getByHairColor(hairColor.get()));
            else return specifications.and(CharacterSpecifications.GetNoone());
        }

        if (requestInfo.getFilters().containsKey("birthPlace")) {
            String birthPlaceData = requestInfo.getFilters().get("birthPlace");
            List<String> birthPlacesListString = new ArrayList<>(Arrays.asList(birthPlaceData.split(",")));
            List<Place> birthPlaces = placeService.findByNameIn(birthPlacesListString);
            if (birthPlaces.size() > 0)
                specifications = specifications.and(CharacterSpecifications.getByBirthPlaces(birthPlaces));
            else return specifications.and(CharacterSpecifications.GetNoone());
        }


        //ToDo Filtrowanie po dacie urodzenia

        if (requestInfo.getFilters().containsKey("starSign")) { //ToDo Autocomplete
            String starSign = requestInfo.getFilters().get("starSign");
            List<String> starSignListString = new ArrayList(Arrays.asList(starSign.split(",")));
            List<StarSign> starSigns = starSignListString.stream().map(StarSign::findByName).collect(Collectors.toList());
            if(starSigns.size() > 0) specifications.and(CharacterSpecifications.getByStarSigns(starSign));
            else return specifications.and(CharacterSpecifications.GetNoone());
        }

        if (requestInfo.getFilters().containsKey("dominatingEmotions")) {
            String dominatingEmotionsData = requestInfo.getFilters().get("dominatingEmotions");
            List<String> dominatingEmotionsListString = new ArrayList<>(Arrays.asList(dominatingEmotionsData.split(",")));
            List<Emotion> dominatingEmotions = emotionService.findByNameIn(dominatingEmotionsListString);
            if (dominatingEmotions.size() > 0)
                specifications = specifications.and(CharacterSpecifications.getByEmotions(dominatingEmotions));
            else return specifications.and(CharacterSpecifications.GetNoone());
        }

        if (requestInfo.getFilters().containsKey("sex")) {
            Sex sex = Sex.valueOf(requestInfo.getFilters().get("sex"));
            specifications = specifications.and(CharacterSpecifications.getBySex(sex));
        }

        if (requestInfo.getFilters().containsKey("religion")) {
            String religionsData = requestInfo.getFilters().get("religion");
            List<String> religionsListString = new ArrayList<>(Arrays.asList(religionsData.split(",")));
            List<Religion> religions = religionsListString.stream().map(Religion::findByGodName).collect(Collectors.toList());
            if (religions.size() > 0) specifications = specifications.and(CharacterSpecifications.getByReligions(religions));
            else return specifications.and(CharacterSpecifications.GetNoone());
}

        if (requestInfo.getFilters().containsKey("prediction")) {
            Optional<Prediction> prediction = predictionService.findByText(requestInfo.getFilters().get("prediction"));
            if (prediction.isPresent())
                specifications = specifications.and(CharacterSpecifications.getByPrediction(prediction.get()));
            else return specifications.and(CharacterSpecifications.GetNoone());
        }

        if (requestInfo.getFilters().containsKey("careers")) { //ToDo to jest po profesjach końcowych!!
            String careerData = requestInfo.getFilters().get("careers");
            List<String> careersListString = new ArrayList<>(Arrays.asList(careerData.split(",")));
            List<Career> careers = careerService.findByNameIn(careersListString);
            if (careers.size() > 0) specifications = specifications.and(CharacterSpecifications.getByCareer(careers));
            else return specifications.and(CharacterSpecifications.GetNoone());
        }


        if (requestInfo.getFilters().containsKey("skills")) {
            String skillsData = requestInfo.getFilters().get("skills");
            List<String> skillsListString = new ArrayList<>(Arrays.asList(skillsData.split(",")));
            List<Skill> skills = skillService.findByNameIn(skillsListString);
            if (skills.size() > 0) specifications = specifications.and(CharacterSpecifications.getBySkills(skills));
            else return specifications.and(CharacterSpecifications.GetNoone());
        }

        if (requestInfo.getFilters().containsKey("personalities")) {
            String personalityData = requestInfo.getFilters().get("personalities");
            List<String> personalityListString = new ArrayList<>(Arrays.asList(personalityData.split(",")));
            List<Personality> personalities = personalityService.findByNameIn(personalityListString);
            if (personalities.size() > 0)
                specifications = specifications.and(CharacterSpecifications.getByPersonalities(personalities));
            else return specifications.and(CharacterSpecifications.GetNoone());
        }

        if (requestInfo.getFilters().containsKey("apperances")) {
            String apperanceData = requestInfo.getFilters().get("apperances");
            List<String> apperanceListString = new ArrayList<>(Arrays.asList(apperanceData.split(",")));
            List<Apperance> apperances = apperanceService.findByNameIn(apperanceListString);
            if (apperances.size() > 0)
                specifications = specifications.and(CharacterSpecifications.getByApperances(apperances));
            else return specifications.and(CharacterSpecifications.GetNoone());
        }

        if (requestInfo.getFilters().containsKey("livePlace")) {
            String livePlacesData = requestInfo.getFilters().get("livePlace");
            List<String> livePlacesListString = new ArrayList<>(Arrays.asList(livePlacesData.split(",")));
            List<Place> places = placeService.findByNameIn(livePlacesListString);
            if(places.size() > 0){
                specifications = specifications.and(CharacterSpecifications.getByLivePlaces(places));
            }
            else return specifications.and(CharacterSpecifications.GetNoone());
        }


        //ToDo Filtry po statystykach liczbowych (Wzrost, waga, statystyki)


        if (requestInfo.getFilters().containsKey("talents")) {
            String talentsData = requestInfo.getFilters().get("talents");
            //Przerabiam String[] na Arraylistę, pozbywając się przy okazji przecinków
            List<String> talentsListString = new ArrayList<String>(Arrays.asList(talentsData.split(",")));
            //Pobieram listę obiektów typu talents
            List<Talent> talentsList = talentService.findByNameIn(talentsListString);
            if (talentsList.size() > 0)
                specifications = specifications.and(CharacterSpecifications.getByTalents(talentsList));
            else
                return specifications.and(CharacterSpecifications.GetNoone()); //Jeśli nie istnieją talenty jak w filtrze, to nic nie spełnia wymagań
        }

        return specifications;
    }

    public Long getCountCharacters() {
        return characterRepository.count();
    }

    public List<CharacterDefaultAttributesOutputDto> getByHeight() {
        Pageable pageable = PageRequest.of(0, 5);
        Page<Character> characters = characterRepository.findByHeight(180, pageable);
        List<CharacterDefaultAttributesOutputDto> output = new ArrayList<>();
        characters.get().forEach(c -> output.add(CharacterConverter.convert(c)));
        return output;

    }

    public AutocompleteFilterCharactersOutputDto getAutoCompletes() {
        return new AutocompleteFilterCharactersOutputDto(placeService.getAllNames(),
                careerService.getAllNames(), apperanceService.getAllNames(),
                personalityService.getAllNames(), talentService.getAllNames(),
                skillService.getAllNames(), emotionService.getAllNames()
        );
    }

    public CharacterDetailsOutputDto getDetailsById(Long id) {
        Optional<Character> c = characterRepository.findById(id);
        return c.map(CharacterConverter::convertDetails).orElse(null);
    }
}
