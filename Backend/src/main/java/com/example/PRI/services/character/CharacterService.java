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
import com.example.PRI.services.GeneralService;
import com.example.PRI.services.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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

        Random rand = new Random();

        for(int i=0;i<100;i++){
            Character c = new Character();
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

        //ToDo query umiejące sortować po wartościach klucza obcego
        Page<Character> tmp1 = characterRepository.findAll(pageable);

        List<Character> tmp = characterRepository.findAll();
        CharacterListOutputDto output = new CharacterListOutputDto();
        Page<Character> characters = characterRepository.findAll(pageable);
        List<CharacterDefaultAttributesOutputDto> outputData = new ArrayList<>();
        characters.get().forEach(c -> outputData.add(CharacterConverter.convert(c)));

        output.setList(outputData);
        output.setTotalCount(characters.getTotalElements());

        return output;
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
