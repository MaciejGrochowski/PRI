package com.example.PRI.services;

import com.example.PRI.converters.UserOfAppConverter;
import com.example.PRI.dtos.users.UserOfAppCredentialsInputDto;
import com.example.PRI.dtos.users.UserOfAppDetailsOutputDto;
import com.example.PRI.dtos.users.UserOfAppInputDto;
import com.example.PRI.entities.UserOfApp;
import com.example.PRI.exceptions.notUniqueArgumentException;
import com.example.PRI.repositories.UserOfAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Optional;

@Service
public class UserOfAppService extends GeneralService {

    @Autowired
    UserOfAppRepository userOfAppRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public UserOfApp findByUsername(String username) {
        return userOfAppRepository.findByUsername(username);
    }

    public void saveTokenToUser(String username, String token) {
        UserOfApp userOFApp = findByUsername(username);
        userOFApp.setToken(token);
        userOfAppRepository.save(userOFApp);
    }

    private UserOfApp findByToken(String token){
        return userOfAppRepository.findByToken(token);
    }

    public Boolean isTokenExpired(String token) {
        return findByToken(token) == null;
    }

    private void logoutUser(String username){
        UserOfApp userOFApp = findByUsername(username);
        userOFApp.setToken(null);
        userOfAppRepository.save(userOFApp);
    }

    public String getUsernameFromAuthentication(Authentication auth){
        User user = (User)auth.getPrincipal();
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

    public UserOfAppDetailsOutputDto getDetailsByUsername(String username) {
        Optional<UserOfApp> c = Optional.ofNullable(userOfAppRepository.findByUsername(username));
        return c.map(UserOfAppConverter::convertDetails).orElse(null);
    }

    private void updateUser(UserOfAppInputDto user, String authUsername){
        UserOfApp u = userOfAppRepository.findByUsername(authUsername);
        u.setDescription(user.getDescription());
//        u.setMail(user.getMail());
        u.setDiscord(user.getDiscord());
        u.setFacebook(user.getFacebook());
        userOfAppRepository.save(u);
    }

    public void updateUser(UserOfAppInputDto user, Authentication auth){
            updateUser(user, ((User) auth.getPrincipal()).getUsername());
    }

    public void updateUserCredentials(@Valid UserOfAppCredentialsInputDto user, Authentication auth) throws notUniqueArgumentException {
        String oldUsername = ((User) auth.getPrincipal()).getUsername();

        if(passwordEncoder.matches(user.getOldPassword(), ((User) auth.getPrincipal()).getPassword())){
            updateUserCredentials(oldUsername, user);
        }
        else throw new notUniqueArgumentException("Niepoprawne stare hasło", new Exception());
    }
    private void updateUserCredentials(String oldUsername, UserOfAppCredentialsInputDto user) throws notUniqueArgumentException {
        if (userOfAppRepository.findByUsername(user.getUsername()) != null && !oldUsername.equals(userOfAppRepository.findByUsername(user.getUsername()).getUsername())) {
            throw new notUniqueArgumentException("Już istnieje taka nazwa użytkownika", new Exception());
        }
        else {
            UserOfApp u = userOfAppRepository.findByUsername(oldUsername);
            u.setUsername(user.getUsername());
            u.setPassword(passwordEncoder.encode(user.getNewPassword()));
            userOfAppRepository.save(u);
        }

    }
}
