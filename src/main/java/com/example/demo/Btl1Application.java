package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.example.demo.entities")
public class Btl1Application {

	public static void main(String[] args) {
		SpringApplication.run(Btl1Application.class, args);
	}

}
