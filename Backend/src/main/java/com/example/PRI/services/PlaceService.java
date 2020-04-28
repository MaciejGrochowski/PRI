package com.example.PRI.services;

import com.example.PRI.entities.Place;
import com.example.PRI.repositories.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
}
