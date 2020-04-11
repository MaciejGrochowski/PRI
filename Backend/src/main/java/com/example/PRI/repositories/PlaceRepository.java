package com.example.PRI.repositories;

import com.example.PRI.entities.Place;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends CrudRepository<Place, Long> {

}
