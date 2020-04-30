package com.example.PRI.repositories.character;

import com.example.PRI.entities.character.Character;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepository extends PagingAndSortingRepository<Character, Long>, JpaSpecificationExecutor<Character> {

    public Page<Character> findByHeight(int height, Pageable pageable);

    public Page<Character> findAll();



}
