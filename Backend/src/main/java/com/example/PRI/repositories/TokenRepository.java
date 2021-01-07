package com.example.PRI.repositories;

import com.example.PRI.entities.Place;
import com.example.PRI.entities.Token;
import com.example.PRI.entities.UserOfApp;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends CrudRepository<Token, Long> {

    Token findByName(String token);
}
