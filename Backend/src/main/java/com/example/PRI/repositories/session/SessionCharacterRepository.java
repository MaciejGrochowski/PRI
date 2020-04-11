package com.example.PRI.repositories.session;

import com.example.PRI.entities.session.SessionCharacter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionCharacterRepository extends CrudRepository<SessionCharacter, Long> {

}
