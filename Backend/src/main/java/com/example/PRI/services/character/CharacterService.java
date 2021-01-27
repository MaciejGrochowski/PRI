package com.example.PRI.services.character;

import com.example.PRI.converters.CharacterConverter;
import com.example.PRI.dtos.characters.*;
import com.example.PRI.entities.Place;
import com.example.PRI.entities.UserOfApp;
import com.example.PRI.entities.character.Character;
import com.example.PRI.entities.character.*;
import com.example.PRI.enums.*;
import com.example.PRI.repositories.character.CharacterRepository;
import com.example.PRI.services.GeneralService;
import com.example.PRI.services.PlaceService;
import com.example.PRI.services.UserOfAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static com.example.PRI.converters.CharacterConverter.convertFilterAttributeToClassFieldName;

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

    @Transactional
    public void save(Character character) {
        List<Skill> skills = character.getSkills();
        skills.sort(new Comparator<Skill>() {
            @Override
            public int compare(Skill skill, Skill t1) {
                return skill.getName().compareTo(t1.getName());
            }
        });
        character.setSkills(skills);
        List<Talent> talents = character.getTalents();
        talents.sort(new Comparator<Talent>() {
            @Override
            public int compare(Talent talent, Talent t1) {
                return talent.getName().compareTo(t1.getName());
            }
        });
        characterRepository.save(character);
    }

    public CharacterListOutputDto getSomeCharactersPaged(CharacterListFilterInputDto requestInfo) throws Throwable {
        if(requestInfo.getRowsPerPage() > 100) {
            requestInfo.setRowsPerPage(100);
        }

        requestInfo.setSortedBy(convertFilterAttributeToClassFieldName(requestInfo.getSortedBy()));

        Pageable pageable;
        if (requestInfo.getSortedBy() == null){
            pageable = PageRequest.of(requestInfo.getCurrentPage(), requestInfo.getRowsPerPage(), Sort.by("id").descending());
        }
        else {
            if (requestInfo.getIsAscending()){
                pageable = PageRequest.of(requestInfo.getCurrentPage(),
                        requestInfo.getRowsPerPage(), Sort.by(requestInfo.getSortedBy()).ascending());
            }
            else {
                pageable = PageRequest.of(requestInfo.getCurrentPage(),
                        requestInfo.getRowsPerPage(), Sort.by(requestInfo.getSortedBy()).descending());
            }

        }

        Specification<Character> specifications = this.getSpecificationsFromFilter(requestInfo);

        Page<Character> charactersFilteredPage = characterRepository.findAll(specifications, pageable);

        CharacterListOutputDto output = new CharacterListOutputDto();
        List<CharacterDefaultAttributesOutputDto> outputData = new ArrayList<>();
        charactersFilteredPage.get().forEach(c -> outputData.add(CharacterConverter.convert(c)));
        output.setList(outputData);
        output.setTotalCount(charactersFilteredPage.getTotalElements());
        return output;
    }

    @Autowired
    UserOfAppService userService;

    private Specification<Character> getSpecificationsFromFilter(CharacterListFilterInputDto requestInfo) {
        Specification<Character> specifications = CharacterSpecifications.getAll();
        if (requestInfo.getFilters() == null || requestInfo.getFilters().size() == 0) return specifications;

        if(requestInfo.getFilters().containsKey(CharacterAttribute.CREATEDBY.getName())){
            String username = requestInfo.getFilters().get(CharacterAttribute.CREATEDBY.getName());
            UserOfApp user = userService.findByUsername(username);
            if(user != null) specifications = specifications.and(CharacterSpecifications.getByUser(Collections.singletonList(user)));
            else return specifications.and(CharacterSpecifications.GetNoone());
        }

        if (requestInfo.getFilters().containsKey(CharacterAttribute.NAME.getName())) {
            Optional<Name> name = nameService.findByName(requestInfo.getFilters().get(CharacterAttribute.NAME.getName()));
            if (name.isPresent()) specifications = specifications.and(CharacterSpecifications.getByName(name.get()));
            else return specifications.and(CharacterSpecifications.GetNoone());
        }

        if (requestInfo.getFilters().containsKey(CharacterAttribute.SURNAME.getName())) {
            Optional<Surname> surname = surnameService.findBySurname(requestInfo.getFilters().get(CharacterAttribute.SURNAME.getName()));
            if (surname.isPresent())
                specifications = specifications.and(CharacterSpecifications.getBySurname(surname.get()));
            else return specifications.and(CharacterSpecifications.GetNoone());
        }

        if (requestInfo.getFilters().containsKey(CharacterAttribute.RACE.getName())) {
            List<String> racesList = Arrays.asList(requestInfo.getFilters().get(CharacterAttribute.RACE.getName()).split(","));
            List<Race> races = racesList.stream().map(Race::valueOf).collect(Collectors.toList());
            if(races.size() > 0) specifications = specifications.and(CharacterSpecifications.getByRaces(races));
            else return specifications.and(CharacterSpecifications.GetNoone());
        }

        if (requestInfo.getFilters().containsKey(CharacterAttribute.EYECOLOR.getName())) {
            Optional<EyeColor> eyeColor = eyeColorService.findByName(requestInfo.getFilters().get(CharacterAttribute.EYECOLOR.getName()));
            if (eyeColor.isPresent())
                specifications = specifications.and(CharacterSpecifications.getByEyeColor(eyeColor.get()));
            else return specifications.and(CharacterSpecifications.GetNoone());
        }

        if (requestInfo.getFilters().containsKey(CharacterAttribute.HAIRCOLOR.getName())) {
            Optional<HairColor> hairColor = hairColorService.findByName(requestInfo.getFilters().get(CharacterAttribute.HAIRCOLOR.getName()));
            if (hairColor.isPresent())
                specifications = specifications.and(CharacterSpecifications.getByHairColor(hairColor.get()));
            else return specifications.and(CharacterSpecifications.GetNoone());
        }

        if (requestInfo.getFilters().containsKey(CharacterAttribute.BIRTHPLACE.getName())) {
            String birthPlaceData = requestInfo.getFilters().get(CharacterAttribute.BIRTHPLACE.getName());
            List<String> birthPlacesListString = new ArrayList<>(Arrays.asList(birthPlaceData.split(",")));
            List<Place> birthPlaces = placeService.findByNameIn(birthPlacesListString);
            if (birthPlaces.size() > 0)
                specifications = specifications.and(CharacterSpecifications.getByBirthPlaces(birthPlaces));
            else return specifications.and(CharacterSpecifications.GetNoone());
        }


        //ToDo Filtrowanie po dacie urodzenia

        if (requestInfo.getFilters().containsKey(CharacterAttribute.STARSIGN.getName())) {
            String starSign = requestInfo.getFilters().get(CharacterAttribute.STARSIGN.getName());
            List<String> starSignListString = new ArrayList(Arrays.asList(starSign.split(",")));
            List<StarSign> starSigns = starSignListString.stream().map(StarSign::findByName).collect(Collectors.toList());
            if (starSigns.size() > 0) specifications = specifications.and(CharacterSpecifications.getByStarSigns(starSigns));
            else return specifications.and(CharacterSpecifications.GetNoone());
        }

        if (requestInfo.getFilters().containsKey(CharacterAttribute.RELIGION.getName())) {
            String religionsData = requestInfo.getFilters().get(CharacterAttribute.RELIGION.getName());
            List<String> religionsListString = new ArrayList(Arrays.asList(religionsData.split(",")));
            List<Religion> religions = religionsListString.stream().map(Religion::findByGodName).collect(Collectors.toList());
            if (religions.size() > 0)
                specifications = specifications.and(CharacterSpecifications.getByReligions(religions));
            else return specifications.and(CharacterSpecifications.GetNoone());
        }

        if (requestInfo.getFilters().containsKey(CharacterAttribute.DOMINATINGEMOTIONS.getName())) {
            String dominatingEmotionsData = requestInfo.getFilters().get(CharacterAttribute.DOMINATINGEMOTIONS.getName());
            List<String> dominatingEmotionsListString = new ArrayList<>(Arrays.asList(dominatingEmotionsData.split(",")));
            List<Emotion> dominatingEmotions = emotionService.findByNameIn(dominatingEmotionsListString);
            if (dominatingEmotions.size() > 0)
                specifications = specifications.and(CharacterSpecifications.getByEmotions(dominatingEmotions));
            else return specifications.and(CharacterSpecifications.GetNoone());
        }

        if (requestInfo.getFilters().containsKey(CharacterAttribute.SEX.getName())) {
            Sex sex = Sex.valueOf(requestInfo.getFilters().get(CharacterAttribute.SEX.getName()));
            specifications = specifications.and(CharacterSpecifications.getBySex(sex));
        }

        if (requestInfo.getFilters().containsKey(CharacterAttribute.PREDICTION.getName())) {
            Optional<Prediction> prediction = predictionService.findByText(requestInfo.getFilters().get(CharacterAttribute.PREDICTION.getName()));
            if (prediction.isPresent())
                specifications = specifications.and(CharacterSpecifications.getByPrediction(prediction.get()));
            else return specifications.and(CharacterSpecifications.GetNoone());
        }

        if (requestInfo.getFilters().containsKey(CharacterAttribute.CURRENTCAREER.getName())) { //ToDo filter in all careers or only end?
            String careerData = requestInfo.getFilters().get(CharacterAttribute.CURRENTCAREER.getName());
            List<String> careersListString = new ArrayList<>(Arrays.asList(careerData.split(",")));
            List<Career> careers = careerService.findByNameIn(careersListString);
            if (careers.size() > 0) specifications = specifications.and(CharacterSpecifications.getByCareer(careers));
            else return specifications.and(CharacterSpecifications.GetNoone());
        }


        if (requestInfo.getFilters().containsKey(CharacterAttribute.SKILLS.getName())) {
            String skillsData = requestInfo.getFilters().get(CharacterAttribute.SKILLS.getName());
            List<String> skillsListString = new ArrayList<>(Arrays.asList(skillsData.split(",")));
            List<Skill> skills = skillService.findByNameIn(skillsListString);
            if (skills.size() > 0) specifications = specifications.and(CharacterSpecifications.getBySkills(skills));
            else return specifications.and(CharacterSpecifications.GetNoone());
        }

        if (requestInfo.getFilters().containsKey(CharacterAttribute.PERSONALITY.getName())) {
            String personalityData = requestInfo.getFilters().get(CharacterAttribute.PERSONALITY.getName());
            List<String> personalityListString = new ArrayList<>(Arrays.asList(personalityData.split(",")));
            List<Personality> personalities = personalityService.findByNameIn(personalityListString);
            if (personalities.size() > 0)
                specifications = specifications.and(CharacterSpecifications.getByPersonalities(personalities));
            else return specifications.and(CharacterSpecifications.GetNoone());
        }

        if (requestInfo.getFilters().containsKey(CharacterAttribute.APPERANCE.getName())) {
            String apperanceData = requestInfo.getFilters().get(CharacterAttribute.APPERANCE.getName());
            List<String> apperanceListString = new ArrayList<>(Arrays.asList(apperanceData.split(",")));
            List<Apperance> apperances = apperanceService.findByNameIn(apperanceListString);
            if (apperances.size() > 0)
                specifications = specifications.and(CharacterSpecifications.getByApperances(apperances));
            else return specifications.and(CharacterSpecifications.GetNoone());
        }

        if (requestInfo.getFilters().containsKey(CharacterAttribute.LIVEPLACE.getName())) {
            String livePlacesData = requestInfo.getFilters().get(CharacterAttribute.LIVEPLACE.getName());
            List<String> livePlacesListString = new ArrayList<>(Arrays.asList(livePlacesData.split(",")));
            List<Place> places = placeService.findByNameIn(livePlacesListString);
            if (places.size() > 0) {
                specifications = specifications.and(CharacterSpecifications.getByLivePlaces(places));
            } else return specifications.and(CharacterSpecifications.GetNoone());
        }
        //ToDo Filtry po statystykach liczbowych (Wzrost, waga, statystyki)
        if (requestInfo.getFilters().containsKey(CharacterAttribute.TALENTS.getName())) {
            String talentsData = requestInfo.getFilters().get(CharacterAttribute.TALENTS.getName());
            List<String> talentsListString = new ArrayList<String>(Arrays.asList(talentsData.split(",")));
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
        return new AutocompleteFilterCharactersOutputDto(
                placeService.getAllNames().stream().sorted().collect(Collectors.toList()),
                careerService.getAllNames().stream().sorted().collect(Collectors.toList()),
                apperanceService.getAllNames().stream().sorted().collect(Collectors.toList()),
                personalityService.getAllNames().stream().sorted().collect(Collectors.toList()),
                talentService.getAllNames().stream().sorted().collect(Collectors.toList()),
                skillService.getAllNames().stream().sorted().collect(Collectors.toList()),
                emotionService.getAllNames().stream().sorted().collect(Collectors.toList()),
                eyeColorService.getAllColors().stream().sorted().collect(Collectors.toList()),
                hairColorService.getAllColors().stream().sorted().collect(Collectors.toList()),
                Arrays.stream(Religion.values()).map(Religion::getGodName).collect(Collectors.toList()),
                Arrays.stream(StarSign.values()).map(StarSign::getName).sorted().collect(Collectors.toList())
        );
    }

    public CharacterDetailsOutputDto getDetailsById(Long id) {
        Optional<Character> c = characterRepository.findById(id);
        return c.map(CharacterConverter::convertDetails).orElse(null);
    }

    public List<CharacterTagOutputDto> getDataForTags() {
        Page<Character> allCharacters = characterRepository.findAll(PageRequest.of(0, (int) characterRepository.count()));
        List<CharacterTagOutputDto> output = new ArrayList<>();

        for(Character c : allCharacters ){
            CharacterTagOutputDto tag = new CharacterTagOutputDto();
            tag.setText(c.getName().getName() + (c.getSurname() != null ? " " + c.getSurname().getSurname() : "") + "#" + c.getId());
            tag.setValue(tag.getText());
            tag.setUrl(String.valueOf(c.getId()));
            output.add(tag);
        }
        return output;
    }

    public List<Character> getCharactersByIdIn(List<Long> characterIds) {
        return characterRepository.findByIdIn(characterIds);
    }
}
