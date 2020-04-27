package com.example.PRI.repositories.character;

import com.example.PRI.entities.character.Character;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepository extends PagingAndSortingRepository<Character, Long> {

    public Page<Character> findByHeight(int height, Pageable pageable);

    public List<Character> findAll();


    //ToDo query które zadziała
    @Query(value = "SELECT c, n.name FROM Character c " +
            "JOIN Name n",
            countQuery = "SELECT count(c) FROM Character c",
            nativeQuery = true) //Dzięki temu Pageable powinno działać
    public Page<Character> findAllNormalSorted(Pageable pageable);

}
