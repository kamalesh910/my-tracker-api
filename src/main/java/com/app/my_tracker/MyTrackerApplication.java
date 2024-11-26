package com.app.my_tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.app.my_tracker.repository")
public class MyTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyTrackerApplication.class, args);
		System.out.println("Application started up and running");
	}

}
