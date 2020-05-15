package com.example.PRI.repositories;

import com.example.PRI.entities.Place;
import com.example.PRI.enums.PlaceType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends CrudRepository<Place, Long> {

    Place findByName(String birthPlace);

    List<Place> findAll();

    List<Place> findByNameIn(List<String> placesList);

    List<Place> findByPropertiesLike(String s);

    List<Place> findByPlaceType(PlaceType type);
}
