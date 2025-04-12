package com.bacobogota.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.bacobogota.todo")
public class TodoApplication {
	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
	}
}

