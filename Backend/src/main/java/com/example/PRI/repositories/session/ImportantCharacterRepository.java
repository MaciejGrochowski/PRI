package com.example.PRI.repositories.session;

import com.example.PRI.entities.session.ImportantCharacter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImportantCharacterRepository extends CrudRepository<ImportantCharacter, Long> {

}
