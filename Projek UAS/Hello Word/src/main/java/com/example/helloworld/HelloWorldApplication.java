package com.example.helloworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Application Class
 * Entry point untuk Spring Boot application
 */
@SpringBootApplication
public class HelloWorldApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloWorldApplication.class, args);
        System.out.println("===========================================");
        System.out.println("Hello World Spring AI Application Started!");
        System.out.println("Access at: http://localhost:8080");
        System.out.println("===========================================");
    }
}
