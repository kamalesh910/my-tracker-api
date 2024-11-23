package com.app.my_tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyTrackerApplication.class, args);
		System.out.println("Application started up and running");
	}

}
