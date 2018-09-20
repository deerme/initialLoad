package com.euge.initialload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class InitialloadPocApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(InitialloadPocApplication.class, args);
		
	}
}
