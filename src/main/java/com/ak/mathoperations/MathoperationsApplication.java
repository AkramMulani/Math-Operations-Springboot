package com.ak.mathoperations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(origins = {"https://localhost:3000/operations","http://192.168.57.82:3000"})
public class MathoperationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MathoperationsApplication.class, args);
	}

}
