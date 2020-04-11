package com.example.PRI.repositories.history;

import com.example.PRI.entities.history.History;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends CrudRepository<History, Long> {

}
