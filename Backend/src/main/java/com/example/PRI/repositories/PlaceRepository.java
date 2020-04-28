package com.example.PRI.repositories;

import com.example.PRI.entities.Place;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends CrudRepository<Place, Long> {

    Place findByName(String birthPlace);

    List<Place> findAll();
}
