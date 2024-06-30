package com.example.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class for the Vehicle Routing Service application.
 */
@SpringBootApplication
public class BackendApplication {
	/**
	 * Main method to start the Vehicle Routing Service application.
	 *
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}
