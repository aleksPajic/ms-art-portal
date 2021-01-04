package com.portal.art.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Arrays;

@SpringBootApplication
@EnableMongoRepositories("com.portal.art.app.repositories")
public class MsArtPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsArtPortalApplication.class, args);
	}
}
