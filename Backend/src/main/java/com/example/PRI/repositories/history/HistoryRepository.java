package com.example.PRI.repositories.history;

import com.example.PRI.entities.history.History;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends PagingAndSortingRepository<History, Long>, JpaSpecificationExecutor<History> {

    public Page<History> findAll();
}
