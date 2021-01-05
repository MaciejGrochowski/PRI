package com.example.PRI.services;

import com.example.PRI.converters.UserOfAppConverter;
import com.example.PRI.dtos.users.UserOfAppCredentialsInputDto;
import com.example.PRI.dtos.users.UserOfAppDetailsInputDto;
import com.example.PRI.dtos.users.UserOfAppDetailsOutputDto;
import com.example.PRI.dtos.users.UserOfAppInputDto;
import com.example.PRI.entities.Token;
import com.example.PRI.entities.UserOfApp;
import com.example.PRI.exceptions.notUniqueArgumentException;
import com.example.PRI.repositories.TokenRepository;
import com.example.PRI.repositories.UserOfAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class UserOfAppService extends GeneralService {

    @Autowired
    UserOfAppRepository userOfAppRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    EmailService emailService;

    @Autowired
    TokenRepository tokenRepository;

    public UserOfApp findByUsername(String username) {
        return userOfAppRepository.findByUsername(username);
    }

    public void saveTokenToUser(String username, String token) {
        UserOfApp userOFApp = findByUsername(username);
        Token token1 = new Token(userOFApp, token);
        tokenRepository.save(token1);
        userOfAppRepository.save(userOFApp);
    }

    private UserOfApp findByToken(String token){
        //tokenRepository.findByTokenName(token);
        return userOfAppRepository.findByToken(tokenRepository.findByName(token));
    }

    public Boolean isTokenExpired(String token) {
        return tokenRepository.findByName(token) == null;
    }

    private void logoutUser(String username, Authentication auth){
        UserOfApp userOFApp = findByUsername(username);
        Token tok = userOFApp.getSingleToken(auth.toString());


        List<Token> tmp = userOFApp.getToken();
        tmp.remove(tok);
        userOFApp.setToken(tmp);
        //tok.setTokenUser(null);
        //tokenRepository.delete(tok);
        //userOFApp.setToken(null); //ToDo token should let more logged sessions
        userOfAppRepository.save(userOFApp);
    }

    public String getUsernameFromAuthentication(Authentication auth){
        User user = (User)auth.getPrincipal();
        return user.getUsername();
    }

    public void logoutUser(Authentication auth) {
        if(auth==null) return;
        logoutUser(auth.getName(), auth);
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

    private void updateUser(UserOfAppDetailsInputDto user, String authUsername){
        UserOfApp u = userOfAppRepository.findByUsername(authUsername);
        u.setDescription(user.getDescription());
//        u.setMail(user.getMail());
        u.setDiscord(user.getDiscord());
        u.setFacebook(user.getFacebook());
        userOfAppRepository.save(u);
    }

    public void updateUser(UserOfAppDetailsInputDto user, Authentication auth){
        UserOfApp userDb = findByUsername(((User) auth.getPrincipal()).getUsername());
        if(userDb.getPassword().equals(((User) auth.getPrincipal()).getPassword())){
            updateUser(user, ((User) auth.getPrincipal()).getUsername());
        }
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

    public void register(UserOfAppInputDto userOfAppInputDto) throws notUniqueArgumentException {
        if(!userOfAppInputDto.getPassword().equals(userOfAppInputDto.getConfirmPassword())){
            throw new notUniqueArgumentException("Hasła nie są identyczne", new Exception());
        }
        else {
            saveNewUser(userOfAppInputDto);
        }
    }

    private void saveNewUser(UserOfAppInputDto userOfAppInputDto) throws notUniqueArgumentException {
        if(userOfAppRepository.findByMail(userOfAppInputDto.getMail())==null && userOfAppRepository.findByUsername(userOfAppInputDto.getUsername())==null){
                UserOfApp user = new UserOfApp();
                user.setUsername(userOfAppInputDto.getUsername());
                user.setPassword(passwordEncoder.encode(userOfAppInputDto.getPassword()));
                user.setDescription(userOfAppInputDto.getDescription());
                user.setDiscord(userOfAppInputDto.getDiscord());
                user.setMail(userOfAppInputDto.getMail());
                user.setFacebook(userOfAppInputDto.getFacebook());
                userOfAppRepository.save(user);
            }
        else{
            throw new notUniqueArgumentException("Nazwa lub email istnieją już w bazie", new Exception());
        }

    }

    public void sendPasswordRemainder(String email) {
        UserOfApp uoa = userOfAppRepository.findByMail(email);
        if(uoa != null){
            emailService.sendPasswordRemainder(uoa.getUsername(), uoa.getMail(), uoa.getPassword());
        }
        else{
            System.err.println("nie ma takiego maia w bazie");
        }

    }
}
