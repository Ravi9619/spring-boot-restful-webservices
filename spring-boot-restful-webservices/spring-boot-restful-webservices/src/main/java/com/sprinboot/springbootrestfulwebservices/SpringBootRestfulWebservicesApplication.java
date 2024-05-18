package com.sprinboot.springbootrestfulwebservices;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Spring Boot Rest Api Documentation",
				description = "Spring Boot Rest Api Documentation",
				version = "v1.0",
				contact = @Contact(
						name = "Ravi",
						email = "ravidubey@gmail.com"
						),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.javadev.net/license"
						)
				)
		)
public class SpringBootRestfulWebservicesApplication {
	
	
	//Configure ModelMapper as Spring Beans
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestfulWebservicesApplication.class, args);
	}

}
