package com.example.PRI.services;

import com.example.PRI.entities.Place;
import com.example.PRI.entities.character.Career;
import com.example.PRI.repositories.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlaceService extends GeneralService {

    @Autowired
    PlaceRepository placeRepository;

    public void save(Place place){
        placeRepository.save(place);
    }

    public Optional<Place> findByName(String birthPlace) {
        return Optional.ofNullable(placeRepository.findByName(birthPlace));
    }

    public List<String> getAllNames() {
        List<String> tmp = placeRepository.findAll().stream().map(Place::getName).distinct().collect(Collectors.toList());
        return tmp;
    }

    public List<Place> findByNameIn(List<String> placesList) {
        return placeRepository.findByNameIn(placesList);
    }
}
