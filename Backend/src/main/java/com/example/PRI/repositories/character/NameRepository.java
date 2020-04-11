package com.example.PRI.repositories.character;

import com.example.PRI.entities.character.Name;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NameRepository extends CrudRepository<Name, Long> {

    Name findByName(String name);
}
