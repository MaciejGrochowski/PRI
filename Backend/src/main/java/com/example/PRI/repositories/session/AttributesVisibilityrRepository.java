package com.example.PRI.repositories.session;

import com.example.PRI.entities.session.AttributesVisibility;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttributesVisibilityrRepository extends CrudRepository<AttributesVisibility, Long> {

}
