package com.diaz.tennis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.diaz.tennis.model")
@EnableJpaRepositories(basePackages = "com.diaz.tennis.repository")
public class TennisApplication {
	
    public static void main(String[] args) {
        SpringApplication.run(TennisApplication.class, args);
    }
    
}
