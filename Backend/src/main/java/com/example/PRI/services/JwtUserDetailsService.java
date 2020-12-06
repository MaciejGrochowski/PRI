package com.example.PRI.services;

import java.util.ArrayList;

import com.example.PRI.entities.UserOfApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {


    @Autowired
    PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserOfAppService userOfAppService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//        String tmp = bCryptPasswordEncoder.encode("Chomik123");
//        System.out.println(tmp);

        UserOfApp userOFApp = userOfAppService.findByUsername(username);

        //$2a$10$T27dmVrQ5.cIh2g/XKWfreuvVtSYNCM3a3chqxztNZUHKLGkCBd1G
        //$2a$10$95WqMmP3oqpIBIlGviFpjOvOitcL42jfyv9l.R2qUb0jUD5H2Qfrm
        //$2a$10$ZL3JqPYkL2Zc8yZE7XX5W.3/9ScBNhTR95FZdy3GdXvPY/uSAHpku

        if (userOFApp !=null) {
//															"$2y$12$f0fVlNPQLy0kHO/H5kXryuK7fteOfr.Z9NaSw.Uz5XZBvtsXA0v8u" Chomik123 = "$2a$10$T27dmVrQ5.cIh2g/XKWfreuvVtSYNCM3a3chqxztNZUHKLGkCBd1G"
            return new User(userOFApp.getUsername(), userOFApp.getPassword(),
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

}
