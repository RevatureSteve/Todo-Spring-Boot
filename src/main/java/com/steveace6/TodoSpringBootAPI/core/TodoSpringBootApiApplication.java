package com.steveace6.TodoSpringBootAPI.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages="com.steveace6")
@EntityScan(basePackages="com.steveace6")
@EnableJpaRepositories("com.steveace6")
public class TodoSpringBootApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoSpringBootApiApplication.class, args);
	}
}
