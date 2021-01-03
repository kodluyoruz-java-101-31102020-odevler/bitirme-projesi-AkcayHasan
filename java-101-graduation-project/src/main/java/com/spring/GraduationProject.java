package com.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.spring.entity", "com.spring.controller", "com.spring.service",
		"com.spring.jpa.repository" })
public class GraduationProject {
	public static void main(String[] args) {
		SpringApplication.run(GraduationProject.class, args);
	}

}
