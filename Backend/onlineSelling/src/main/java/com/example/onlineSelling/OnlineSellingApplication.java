package com.example.onlineSelling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OnlineSellingApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineSellingApplication.class, args);
	}

}
