package com.example.PRI.repositories.character;

import com.example.PRI.entities.character.Name;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NameRepository extends CrudRepository<Name, Long> {

    Name findByName(String name);

    List<Name> findByIsDwarf(Boolean isTrue);

    List<Name> findAll();
}
