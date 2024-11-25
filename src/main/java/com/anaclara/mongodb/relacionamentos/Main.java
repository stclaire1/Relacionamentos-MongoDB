package com.anaclara.mongodb.relacionamentos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}