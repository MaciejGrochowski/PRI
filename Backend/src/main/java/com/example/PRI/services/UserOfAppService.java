package com.example.PRI.services;

import com.example.PRI.converters.UserOfAppConverter;
import com.example.PRI.dtos.users.*;
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
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    private void logoutUser(String username, Authentication auth, String token){
        UserOfApp userOFApp = findByUsername(username);
        Token tok = userOFApp.getSingleToken(token);
        List<Token> tmp = userOFApp.getToken();
        tmp.remove(tok);
        userOFApp.setToken(tmp);
        try{
            tokenRepository.delete(tok);
        }
        catch(IllegalArgumentException e){
            System.out.println("Token doesnt exist, no need to delete.");
        }
        userOfAppRepository.save(userOFApp);
    }

    public String getUsernameFromAuthentication(Authentication auth){
        if(auth==null) return null;
        User user = (User)auth.getPrincipal();
        return user.getUsername();
    }

    public void logoutUser(Authentication auth, String token) {
        if(auth==null) return;
        logoutUser(auth.getName(), auth, token);
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

    public ResponseEntity<?> updateUserCredentials(@Valid UserOfAppCredentialsInputDto user, Authentication auth) throws notUniqueArgumentException {
        String oldUsername = ((User) auth.getPrincipal()).getUsername();

        if(passwordEncoder.matches(user.getOldPassword(), ((User) auth.getPrincipal()).getPassword())){
            return updateUserCredentials(oldUsername, user);
        }
        return ResponseEntity.badRequest().body("BAD_PASSWORD");
    }
    private ResponseEntity<?> updateUserCredentials(String oldUsername, UserOfAppCredentialsInputDto user) throws notUniqueArgumentException {
        if (userOfAppRepository.findByUsername(user.getUsername()) != null && !oldUsername.equals(userOfAppRepository.findByUsername(user.getUsername()).getUsername())) {
            return ResponseEntity.badRequest().body("USER_ALREADY_EXISTS");
        }
        else {
            UserOfApp u = userOfAppRepository.findByUsername(oldUsername);
            u.setUsername(user.getUsername());
            u.setPassword(passwordEncoder.encode(user.getNewPassword()));
            userOfAppRepository.save(u);
            return ResponseEntity.ok(u.getId());
        }

    }

    public String register(UserOfAppInputDto userOfAppInputDto) throws notUniqueArgumentException {
        if(!userOfAppInputDto.getPassword().equals(userOfAppInputDto.getConfirmPassword())){
            throw new notUniqueArgumentException("Hasła nie są identyczne", new Exception());
        }
        else {
            return saveNewUser(userOfAppInputDto);
        }
    }

    private String saveNewUser(UserOfAppInputDto userOfAppInputDto) throws notUniqueArgumentException {
        if(userOfAppRepository.findByMail(userOfAppInputDto.getMail())==null && userOfAppRepository.findByUsername(userOfAppInputDto.getUsername())==null){
                UserOfApp user = new UserOfApp();
                user.setUsername(userOfAppInputDto.getUsername());
                user.setIsActive(false);
                user.setUUIDActivation(UUID.randomUUID().toString());
                user.setPassword(passwordEncoder.encode(userOfAppInputDto.getPassword()));
                user.setDescription(userOfAppInputDto.getDescription());
                user.setDiscord(userOfAppInputDto.getDiscord());
                user.setMail(userOfAppInputDto.getMail());
                user.setFacebook(userOfAppInputDto.getFacebook());
                return userOfAppRepository.save(user).getUsername();
            }
        else{
            return "MAIL_EXISTS"; //ToDo to enum
        }

    }

    public void sendPasswordRemainder(String email) throws MessagingException {
        UserOfApp uoa = userOfAppRepository.findByMail(email);
        if(uoa != null){
            uoa.setUUIDActivation(UUID.randomUUID().toString());
            userOfAppRepository.save(uoa);
            emailService.sendPasswordRemainder(uoa.getUsername(), uoa.getMail(), uoa.getUUIDActivation());
        }
        else{
            System.err.println("nie ma takiego maia w bazie");
        }

    }


    public UserOfApp getUserByAuthentication(Authentication auth){
        return this.findByUsername(this.getUsernameFromAuthentication(auth));
    }
  
    public void sendHelloEmail(UserOfApp uapp) throws MessagingException {
        emailService.sendWelcomeMail(uapp.getUsername(), uapp.getMail(), uapp.getPassword(), uapp.getUUIDActivation());
    }

    public Long activateUser(String username, String uuid) {
        UserOfApp user = findByUsername(username);
        if(user != null && user.getUUIDActivation().equals(uuid)){
            user.setIsActive(true);
            userOfAppRepository.save(user);
            return user.getId();
        }
        return -1L;
    }

    public ResponseEntity<?> changePassword(ChangePasswordInputDto changePasswordInputDto) {
        UserOfApp user = findByUsername(changePasswordInputDto.getUsername());

        if (user.getUUIDActivation().equals(changePasswordInputDto.getHashcode())){
            user.setPassword(passwordEncoder.encode(changePasswordInputDto.getNewPassword()));
            userOfAppRepository.save(user);
            return ResponseEntity.ok("ok");
        }
        else return ResponseEntity.badRequest().body("BAD_HASH");
    }
  
}
