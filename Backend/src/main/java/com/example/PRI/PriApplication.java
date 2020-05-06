
package com.example.PRI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.lang.*;

@SpringBootApplication
public class PriApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(PriApplication.class, args);
        System.out.println("Działa");


    }

}