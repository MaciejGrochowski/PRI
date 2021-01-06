package com.example.PRI.controllers;

import java.util.Objects;

import com.example.PRI.config.JwtTokenUtil;
import com.example.PRI.controllers.annotations.Post;
import com.example.PRI.dtos.users.*;
import com.example.PRI.exceptions.notUniqueArgumentException;
import com.example.PRI.services.EmailService;
import com.example.PRI.services.UserOfAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

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

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = jwtInMemoryUserDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);
        userOfAppService.saveTokenToUser(authenticationRequest.getUsername(), token);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            UsernamePasswordAuthenticationToken tmp = new UsernamePasswordAuthenticationToken(username, password);
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

//    @Post("/logout-user")
    @RequestMapping(value = "/logout-user", method = RequestMethod.POST)
    public void logout(Authentication auth,@RequestBody TokenInputDto tokenInputDto){

        userOfAppService.logoutUser(auth, tokenInputDto.getToken());
    }

    //@RequestMapping(value = "/register", method = RequestMethod.POST)
    @Post("/register")
    public void register(@RequestBody UserOfAppInputDto userOfAppInputDto) throws notUniqueArgumentException {
        userOfAppService.register(userOfAppInputDto);
    }

    @Post("/forgot-password")
    public void forgotPassword(@RequestBody emailInputDto email){ userOfAppService.sendPasswordRemainder(email.getEmail());}
}
