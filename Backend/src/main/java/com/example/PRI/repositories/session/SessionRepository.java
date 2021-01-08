package com.example.PRI.repositories.session;

import com.example.PRI.entities.UserOfApp;
import com.example.PRI.entities.session.Session;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends CrudRepository<Session, Long> {

    public List<Session> findByCreatedUserOfApp(UserOfApp user);

}
