package com.example.helloworld.service;

import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service Class untuk Hello World
 * Berisi business logic untuk berinteraksi dengan Spring AI
 */
@Service
public class HelloWorldService {

    private final ChatClient chatClient;

    @Autowired
    public HelloWorldService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    /**
     * Generate Hello World message menggunakan AI
     * 
     * @return String pesan Hello World dari AI
     */
    public String generateHelloMessage() {
        try {
            // Membuat prompt untuk AI
            String promptText = "Say 'Hello World' in a creative and friendly way!";
            Prompt prompt = new Prompt(promptText);

            // Memanggil AI dan mendapatkan response
            ChatResponse response = chatClient.call(prompt);

            // Mengambil content dari response
            String aiMessage = response.getResult().getOutput().getContent();

            // Log ke console
            System.out.println("AI Response: " + aiMessage);

            return aiMessage;

        } catch (Exception e) {
            System.err.println("Error calling AI: " + e.getMessage());
            return "Hello World! (Fallback message - AI service unavailable)";
        }
    }

    /**
     * Generate greeting message menggunakan AI
     * 
     * @return String greeting dari AI
     */
    public String generateGreeting() {
        try {
            String promptText = "Generate a warm and inspiring greeting message for someone learning Spring AI. Keep it short and motivating!";
            Prompt prompt = new Prompt(promptText);

            ChatResponse response = chatClient.call(prompt);
            String aiMessage = response.getResult().getOutput().getContent();

            System.out.println("AI Greeting: " + aiMessage);

            return aiMessage;

        } catch (Exception e) {
            System.err.println("Error calling AI: " + e.getMessage());
            return "Welcome to Spring AI! Keep learning and building amazing things!";
        }
    }

    /**
     * Chat dengan AI - menerima pesan dari user dan mengembalikan response
     * 
     * @param userMessage Pesan dari user
     * @return String response dari AI
     */
    public String chat(String userMessage) {
        try {
            System.out.println("User message: " + userMessage);
            
            Prompt prompt = new Prompt(userMessage);
            ChatResponse response = chatClient.call(prompt);
            String aiMessage = response.getResult().getOutput().getContent();

            System.out.println("AI Response: " + aiMessage);

            return aiMessage;

        } catch (Exception e) {
            System.err.println("Error calling AI: " + e.getMessage());
            return "Sorry, I'm having trouble connecting to AI service. Please make sure Ollama is running!";
        }
    }
}
