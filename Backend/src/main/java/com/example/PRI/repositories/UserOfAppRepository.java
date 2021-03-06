package com.example.PRI.repositories;

import com.example.PRI.entities.Token;
import com.example.PRI.entities.UserOfApp;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserOfAppRepository extends CrudRepository<UserOfApp, Long> {


    UserOfApp findByUsername(String username);

    UserOfApp findByToken(Token token);

    UserOfApp findByPassword(String encode);

    UserOfApp findByMail(String mail);

}
