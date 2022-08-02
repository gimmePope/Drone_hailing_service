package com.pocosoft.drone.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@SpringBootApplication
@EnableWebMvc
public class DroneHailingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DroneHailingServiceApplication.class, args);
	}
	
	

}
