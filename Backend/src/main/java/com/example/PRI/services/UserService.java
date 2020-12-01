package com.example.PRI.services;

import com.example.PRI.entities.User;
import com.example.PRI.entities.history.History;
import com.example.PRI.entities.session.Session;
import com.example.PRI.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.PRI.entities.character.Character;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Service
public class UserService extends GeneralService {

    @Autowired
    UserRepository userRepository;


    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void saveTokenToUser(String username, String token) {
        User user = findByUsername(username);
        user.setToken(token);
        userRepository.save(user);
    }

    private User findByToken(String token){
        return userRepository.findByToken(token);
    }

    public Boolean isTokenExpired(String token) {
        return findByToken(token) == null;
    }

    private void logoutUser(String username){
        User user = findByUsername(username);
        user.setToken(null);
        userRepository.save(user);
    }

    public void logoutUser(Authentication auth) {
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)auth.getPrincipal();
        logoutUser(user.getUsername());
    }
}
