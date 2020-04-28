package com.example.PRI.services.character;

import com.example.PRI.converters.CharacterConverter;
import com.example.PRI.dtos.characters.CharacterDefaultAttributesOutputDto;
import com.example.PRI.dtos.characters.CharacterListFilterInputDto;
import com.example.PRI.dtos.characters.CharacterListOutputDto;
import com.example.PRI.entities.Place;
import com.example.PRI.entities.character.*;
import com.example.PRI.entities.character.Character;
import com.example.PRI.enums.Race;
import com.example.PRI.enums.Religion;
import com.example.PRI.enums.Sex;
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
    TalentService talentService;

    public List<CharacterDefaultAttributesOutputDto> getAllCharacters() {
        Iterable<Character> characters = characterRepository.findAll();
        List<CharacterDefaultAttributesOutputDto> output = new ArrayList<>();

        for(Character character : characters){
            output.add(CharacterConverter.convert(character));
        }
        return output;
    }

    public void save(Character character){
        nameService.save(character.getName());
        careerService.save(character.getCareers().get(character.getCareers().size()-1));
        placeService.save(character.getLivePlace());
        if (character.getSurname() != null) surnameService.save(character.getSurname());
        characterRepository.save(character);
    }

    public void saveExampleCharacters(){
        List<Name> names = new ArrayList<>();
        names.add(new Name("Wolfgang", true, false, false, true, false, false, false, 0.1, 0.2));
        names.add(new Name("Johann", true, false, false, true, false, false, false, 0.1, 0.2));
        names.add(new Name("Hans", true, false, false, true, false, false, false, 0.1, 0.2));
        names.add(new Name("Waldemar", true, false, false, true, false, false, false, 0.1, 0.2));
        names.add(new Name("Otto", true, false, false, true, false, false, false, 0.1, 0.2));
        names.add(new Name("Dieter", true, false, false, true, false, false, false, 0.1, 0.2));
        names.add(new Name("Felix", true, false, false, true, false, false, false, 0.1, 0.2));
        names.add(new Name("Magnus", true, false, false, true, false, false, false, 0.1, 0.2));


        List<Career> careers = new ArrayList<>();
        Career c1 = new Career(); c1.setName("Chłop"); c1.setBaseProfession(true); c1.setExitChance(0.1);
        careers.add(c1);
        Career c2 = new Career(); c2.setName("Żołnierz"); c1.setBaseProfession(true); c1.setExitChance(0.1);
        careers.add(c2);
        Career c3 = new Career(); c3.setName("Mieszczanin"); c3.setBaseProfession(true); c3.setExitChance(0.1);
        careers.add(c3);
        Career c4 = new Career(); c4.setName("Rzemieślnik"); c4.setBaseProfession(true); c4.setExitChance(0.1);
        careers.add(c4);
        Career c5 = new Career(); c5.setName("Oprych"); c5.setBaseProfession(true); c5.setExitChance(0.1);
        careers.add(c5);
        Career c6 = new Career(); c6.setName("Sługa"); c6.setBaseProfession(true); c6.setExitChance(0.1);
        careers.add(c6);

        List<Place> places = new ArrayList<>();

        Place p1 = new Place();p1.setName("Altdorf");
        places.add(p1);
        Place p2 = new Place();p2.setName("Nuln");
        places.add(p2);
        Place p3 = new Place();p3.setName("Averheim");
        places.add(p3);

        Talent t = new Talent("hamster", "hamster");
        talentService.save(t);

        Random rand = new Random();

        for(int i=0;i<100;i++){
            Character c = new Character();
            if(rand.nextInt(5) < 3) c.setTalents(Collections.singletonList(t));
            c.setName(names.get(rand.nextInt(names.size())));
            c.setSurname(null);
            c.setCareers(Collections.singletonList(careers.get(rand.nextInt(careers.size()))));
            c.setSex(Sex.MALE);
            c.setRace(Race.HUMAN);
            c.setLivePlace(places.get(rand.nextInt(places.size())));
            c.setHeight(150 + rand.nextInt(50));
            c.setWeight(50 + rand.nextInt(50));
            c.setReligion(Religion.SIGMAR);
            this.save(c);
        }
    }

    @Autowired
    NameRepository nameRepository;


    /*Example JSON to Send and test:
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
        if(requestInfo.getSortedBy() == null) pageable = PageRequest.of(requestInfo.getCurrentPage(), requestInfo.getRowsPerPage());
        else{
            if(requestInfo.getIsAscending())
                pageable = PageRequest.of(requestInfo.getCurrentPage(),
                        requestInfo.getRowsPerPage(), Sort.by(requestInfo.getSortedBy()).ascending());
            else
                pageable = PageRequest.of(requestInfo.getCurrentPage(),
                        requestInfo.getRowsPerPage(), Sort.by(requestInfo.getSortedBy()).descending());
        }

        Specification<Character> specifications = this.getSpecificationsFromFilter(requestInfo);



        Page<Character> charactersFilteredPage = characterRepository.findAll(specifications, pageable);

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
        if(requestInfo.getFilters().size() == 0) return specifications;

        if(requestInfo.getFilters().containsKey("name")){
            Optional<Name> name = nameService.findByName(requestInfo.getFilters().get("name"));
            if(name.isPresent()) specifications = specifications.and(CharacterSpecifications.getByName(name.get()));
            else return specifications.and(CharacterSpecifications.GetNoone());
        }

        if(requestInfo.getFilters().containsKey("surname")){
            Optional<Surname> surname = surnameService.findBySurname(requestInfo.getFilters().get("surname"));
            if(surname.isPresent()) specifications = specifications.and(CharacterSpecifications.getBySurname(surname.get()));
            else return specifications.and(CharacterSpecifications.GetNoone());
        }

        if(requestInfo.getFilters().containsKey("talents")){
            String talentsData = requestInfo.getFilters().get("talents");
            //Przerabiam String[] na Arraylistę, pozbywając się przy okazji przecinków
            List<String> talentsListString = new ArrayList<String>(Arrays.asList(talentsData.split(",")));
            //Pobieram listę obiektów typu talents
            List<Talent> talentsList = talentService.findByNameIn(talentsListString);
            if(talentsList.size() > 0) specifications = specifications.and(CharacterSpecifications.getByTalents(talentsList));
            else return specifications.and(CharacterSpecifications.GetNoone()); //Jeśli nie istnieją talenty jak w filtrze, to nic nie spełnia wymagań

        }

        return specifications;
    }

    public Long getCountCharacters() {
        return characterRepository.count();
    }

    public List<CharacterDefaultAttributesOutputDto> getByHeight(){
        Pageable pageable = PageRequest.of(0, 5);
        Page<Character> characters = characterRepository.findByHeight(180, pageable);
        List<CharacterDefaultAttributesOutputDto> output = new ArrayList<>();
        characters.get().forEach(c -> output.add(CharacterConverter.convert(c)));
        return output;

    }
}
