package com.example.PRI.services.character.generator;

import com.example.PRI.entities.Place;
import com.example.PRI.enums.PlaceType;
import com.example.PRI.services.GeneralService;
import com.example.PRI.services.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.PRI.exceptions.*;
import com.example.PRI.entities.character.Character;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.example.PRI.services.character.generator.MapperJsonStringToMap.mapJsonStringToMap;

@Service
public class CharacterBirthPlaceGenerator extends GeneralService {

    @Autowired
    PlaceService placeService;

    public HashMap<String, String> generateBirthPlace(Character character){

        Random rand = new Random();
        Double placeTypeRand = rand.nextDouble();
        Place generatedPlace;

        generatedPlace = generateBirthPlace(PlaceType.VILLIAGE);
        generatedPlace = generateBirthPlace(PlaceType.SMALLTOWN);
        generatedPlace = generateBirthPlace(PlaceType.TOWN);
        generatedPlace = generateBirthPlace(PlaceType.CITY);
        generatedPlace = generateBirthPlace(PlaceType.CITYSTATE);
        generatedPlace = generateBirthPlace(PlaceType.ELFPLACES);
        generatedPlace = generateBirthPlace(PlaceType.DWARFPLACES);
        generatedPlace = generateBirthPlaceCapitol();
        if(generatedPlace == null) throw new CharacterGenerationException("BirthPlaceGenerator generated null", new NullPointerException());

        character.setBirthPlace(generatedPlace);

        if(generatedPlace.getProperties() != null)
            return (HashMap<String, String>) mapJsonStringToMap(generatedPlace.getProperties());
        return new HashMap<>();
    }

    private Place generateBirthPlace(PlaceType type) {
        List<Place> places = placeService.getByType(type);
        if(places.size() > 0){
            int random = new Random().nextInt(places.size());
            return places.get(random);
        }
        return null;
    }

    private Place generateBirthPlaceForestOrMountains() {
        return null;
    }

    private Place generateBirthPlaceCapitol() {
        List<Place> capitols = placeService.getCapitols();
        if(capitols.size() > 0){
            int random = new Random().nextInt(capitols.size());
            return capitols.get(random);
        }
        return null;
    }
}
