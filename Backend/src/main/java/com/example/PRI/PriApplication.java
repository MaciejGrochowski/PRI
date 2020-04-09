package com.example.PRI;

import com.example.PRI.entity.Chomik;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.*;

@SpringBootApplication
public class PriApplication {

	public static void main(String[] args) {
		SpringApplication.run(PriApplication.class, args);
	}
	Chomik chomik2 = new Chomik();
	Chomik chomik = new Chomik("maciek", 4, true, false, chomik2);


}
