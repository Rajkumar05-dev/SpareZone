package com.learn.SpareZone;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpareZoneApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpareZoneApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper() ;
	}
	
}
