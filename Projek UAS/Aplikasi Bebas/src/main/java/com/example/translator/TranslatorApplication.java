package com.example.translator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Application Class
 * Entry point untuk Chatbot Translator application
 */
@SpringBootApplication
public class TranslatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(TranslatorApplication.class, args);
        System.out.println("===========================================");
        System.out.println("Chatbot Translator Application Started!");
        System.out.println("Access at: http://localhost:8081");
        System.out.println("===========================================");
    }
}
