package com.psm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

@SpringBootApplication
public class SpringGeminAiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringGeminAiApplication.class, args);
		System.out.println("Application Started Successfully");
	}

}
