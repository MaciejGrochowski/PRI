package com.example.PRI.controllers;

import java.util.Objects;

import com.example.PRI.config.JwtTokenUtil;
import com.example.PRI.controllers.annotations.Get;
import com.example.PRI.controllers.annotations.Post;
import com.example.PRI.dtos.users.*;
import com.example.PRI.entities.UserOfApp;
import com.example.PRI.exceptions.notUniqueArgumentException;
import com.example.PRI.services.EmailService;
import com.example.PRI.services.UserOfAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.mail.MessagingException;
import javax.validation.Valid;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService jwtInMemoryUserDetailsService;

    @Autowired
    UserOfAppService userOfAppService;

    @Autowired
    EmailService emailService;

    //@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    @Post("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
            throws Exception {

        try{
            authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new JwtResponse(e.getMessage()));
        }

        final UserDetails userDetails = jwtInMemoryUserDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);
        userOfAppService.saveTokenToUser(authenticationRequest.getUsername(), token);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    @Get("/activate-user/{username}/{uuid}")
    public ResponseEntity<?> activateUser(@PathVariable String username, @PathVariable String uuid){
        Long userId = userOfAppService.activateUser(username, uuid);
        if(userId == -1) return ResponseEntity.badRequest().body("BAD_REQUEST");
        return ResponseEntity.ok(new JwtResponse(userId.toString()));

    }

    private void authenticate(String username, String password) throws Exception {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {

            UserOfApp user = userOfAppService.findByUsername(username);
            if(user==null) throw new Exception("INVALID_CREDENTIALS", new BadCredentialsException(""));
            if(!user.getIsActive()) throw new Exception("USER_NOT_ACTIVE", new BadCredentialsException(""));

            UsernamePasswordAuthenticationToken tmp = new UsernamePasswordAuthenticationToken(username, password);
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception(e.getMessage(), e);
        }
    }

//    @Post("/logout-user")
    @RequestMapping(value = "/logout-user", method = RequestMethod.POST)
    public void logout(Authentication auth,@RequestBody @Valid TokenInputDto tokenInputDto){
        userOfAppService.logoutUser(auth, tokenInputDto.getToken());
    }

    //@RequestMapping(value = "/register", method = RequestMethod.POST)
    @Post("/register")
    public ResponseEntity<String> register(@RequestBody @Valid UserOfAppInputDto userOfAppInputDto) throws notUniqueArgumentException, MessagingException {
        String usernameOrError = userOfAppService.register(userOfAppInputDto);
        UserOfApp uapp = userOfAppService.findByUsername(userOfAppInputDto.getUsername());
        if(usernameOrError.equals(userOfAppInputDto.getUsername())) {
            userOfAppService.sendHelloEmail(uapp);
            return ResponseEntity.ok(usernameOrError);
        }
        else return ResponseEntity.badRequest().body(usernameOrError);
    }

    @Post("/forgot-password")
    public void forgotPassword(@RequestBody emailInputDto email) throws MessagingException {
        userOfAppService.sendPasswordRemainder(email.getEmail());
    }


    @Post("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody @Valid ChangePasswordInputDto changePasswordInputDto){
        return userOfAppService.changePassword(changePasswordInputDto);
    }

}
