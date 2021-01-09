package com.example.PRI.repositories.session;

import com.example.PRI.entities.UserOfApp;
import com.example.PRI.entities.session.Session;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SessionRepository extends CrudRepository<Session, Long> {

    public List<Session> findByCreatedUserOfApp(UserOfApp user);

    Optional<Session> findByRandomIdSession(String randomIdSession);
}
