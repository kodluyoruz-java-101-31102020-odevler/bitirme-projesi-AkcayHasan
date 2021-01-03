package com.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(basePackages = {
		"com.spring.entity",
		"com.spring.jpa.repository",
		"com.spring.service.impl"
})

public class ApplicationConfig {
	
	

}
