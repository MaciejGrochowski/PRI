package com.example.PRI.services.character;

import com.example.PRI.converters.CharacterConverter;
import com.example.PRI.dtos.characters.CharacterDefaultAttributesOutputDto;
import com.example.PRI.entities.Place;
import com.example.PRI.entities.character.Career;
import com.example.PRI.entities.character.Character;
import com.example.PRI.entities.character.Name;
import com.example.PRI.enums.Race;
import com.example.PRI.enums.Sex;
import com.example.PRI.repositories.character.CharacterRepository;
import com.example.PRI.services.GeneralService;
import com.example.PRI.services.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        Character character = new Character();
        Name name = new Name();
        name.setDwarf(false);
        name.setElf(false);
        name.setHalfling(false);
        name.setHuman(true);
        name.setMale(true);
        name.setFemale(false);
        name.setProbabilityGentry(0.01);
        name.setProbabilityNotGentry(0.1);
        name.setUsedByGenerator(true);
        name.setName("Johann");

        Name name2 = new Name();
        name2.setDwarf(false);
        name2.setElf(false);
        name2.setHalfling(false);
        name2.setHuman(true);
        name2.setMale(false);
        name2.setFemale(true);
        name2.setProbabilityGentry(0.02);
        name2.setProbabilityNotGentry(0.05);
        name2.setUsedByGenerator(true);
        name2.setName("Gertruda");

        Career career = new Career();
        career.setName("Chłop");
        career.setExitChance(0.2);
        career.setBaseProfession(true);

        Career career2 = new Career();
        career2.setName("Rybak");
        career2.setExitChance(0.1);
        career2.setBaseProfession(true);

        Place place = new Place();
        place.setName("Altdorf");

        character.setName(name);
        character.setSurname(null);
        character.setCareers(Collections.singletonList(career));
        character.setSex(Sex.MALE);
        character.setRace(Race.HUMAN);
        character.setLivePlace(place);
        character.setHeight(180);
        character.setWeight(80);

        Character character2 = new Character();
        character2.setName(name2);
        character2.setSurname(null);
        character2.setCareers(Collections.singletonList(career2));
        character2.setSex(Sex.FEMALE);
        character2.setRace(Race.HUMAN);
        character2.setLivePlace(place);
        character.setHeight(160);
        character.setWeight(55);

        this.save(character);
        this.save(character2);
    }


}
