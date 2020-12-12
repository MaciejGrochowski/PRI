package com.example.PRI.controllers;

import java.util.Objects;

import com.example.PRI.config.JwtTokenUtil;
import com.example.PRI.controllers.annotations.Post;
import com.example.PRI.dtos.users.JwtRequest;
import com.example.PRI.dtos.users.JwtResponse;
import com.example.PRI.dtos.users.UserOfAppInputDto;
import com.example.PRI.entities.UserOfApp;
import com.example.PRI.exceptions.notUniqueArgumentException;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @Post("/logout-user")
    //@RequestMapping(value = "/logout-user", method = RequestMethod.POST)
    private void logout(Authentication auth){
     userOfAppService.logoutUser(auth);
    }

    //@RequestMapping(value = "/register", method = RequestMethod.POST)
    @Post("/register")
    public void register(@RequestBody UserOfAppInputDto userOfAppInputDto) throws notUniqueArgumentException {
        userOfAppService.register(userOfAppInputDto);
    }

}
