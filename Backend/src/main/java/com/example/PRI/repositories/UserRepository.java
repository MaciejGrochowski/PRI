package com.example.PRI.repositories;

import com.example.PRI.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {


    User findByUsername(String username);

    User findByToken(String token);
}
