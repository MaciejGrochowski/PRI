package com.example.PRI.services;

import com.example.PRI.converters.UserConverter;
import com.example.PRI.dtos.users.UserDetailsOutputDto;
import com.example.PRI.entities.User;
import com.example.PRI.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public String getUsernameFromAuthentication(Authentication auth){
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)auth.getPrincipal();
        return user.getUsername();
    }

    public void logoutUser(Authentication auth) {
        if(auth==null) return;
        logoutUser(getUsernameFromAuthentication(auth));
    }

//    private void changeMailOfUser(String username){
//        User user = findByUsername(username);
////        user.setMail();
//    }

//    public void changeMailOfUser(Authentication auth) {
//        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)auth.getPrincipal();
//        changeMailOfUser(user.getUsername());
//    }

    public UserDetailsOutputDto getDetailsByUsername(String username) {
        Optional<User> c = Optional.ofNullable(userRepository.findByUsername(username));
        return c.map(UserConverter::convertDetails).orElse(null);
    }
}
