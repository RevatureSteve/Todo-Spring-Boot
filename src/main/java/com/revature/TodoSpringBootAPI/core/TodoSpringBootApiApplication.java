package com.revature.TodoSpringBootAPI.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
/*
 * Spring Boot will Scan automatically but 
 * 	if it is not properly scanning them you
 * 	can specify where to scan with these annotations
 */
@SpringBootApplication(scanBasePackages="com.revature")
@EntityScan(basePackages="com.revature")
@EnableJpaRepositories("com.revature")
public class TodoSpringBootApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoSpringBootApiApplication.class, args);
	}
}
