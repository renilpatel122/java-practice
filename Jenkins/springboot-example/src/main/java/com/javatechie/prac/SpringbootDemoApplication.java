package com.javatechie.prac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class SpringbootDemoApplication {

	@GetMapping("/hello")
	public String welcome(){
		return "Hello World!!!";
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootDemoApplication.class, args);
	}

}
