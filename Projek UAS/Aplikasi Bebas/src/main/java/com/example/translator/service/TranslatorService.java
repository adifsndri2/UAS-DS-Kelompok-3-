package com.example.translator.service;

import com.example.translator.model.TranslationResponse;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service Class untuk Translation
 * Berisi business logic untuk translate text menggunakan Spring AI
 */
@Service
public class TranslatorService {

    private final ChatClient chatClient;

    @Autowired
    public TranslatorService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    /**
     * Translate text dari Indonesian ke English menggunakan AI
     * 
     * @param indonesianText Text dalam bahasa Indonesia
     * @return TranslationResponse berisi hasil translation
     */
    public TranslationResponse translateToEnglish(String indonesianText) {
        try {
            // Membuat prompt untuk AI dengan instruksi yang jelas
            String promptText = String.format(
                "Translate the following Indonesian text to English. " +
                "Only provide the translation, no explanations or additional text.\n\n" +
                "Indonesian text: %s\n\n" +
                "English translation:",
                indonesianText
            );

            Prompt prompt = new Prompt(promptText);

            // Memanggil AI dan mendapatkan response
            ChatResponse response = chatClient.call(prompt);

            // Mengambil content dari response
            String translatedText = response.getResult().getOutput().getContent().trim();

            // Log ke console
            System.out.println("Original (ID): " + indonesianText);
            System.out.println("Translated (EN): " + translatedText);

            // Membuat response object
            return new TranslationResponse(
                indonesianText,
                translatedText,
                "Indonesian",
                "English"
            );

        } catch (Exception e) {
            System.err.println("Error during translation: " + e.getMessage());
            e.printStackTrace();
            
            // Return error response
            return new TranslationResponse(
                indonesianText,
                "Error: Unable to translate. Please check your OpenAI API configuration.",
                "Indonesian",
                "English"
            );
        }
    }

    /**
     * Translate dengan context tambahan untuk accuracy yang lebih baik
     * 
     * @param indonesianText Text dalam bahasa Indonesia
     * @param context Context tambahan untuk translation
     * @return TranslationResponse berisi hasil translation
     */
    public TranslationResponse translateWithContext(String indonesianText, String context) {
        try {
            String promptText = String.format(
                "You are a professional translator. Translate the following Indonesian text to English. " +
                "Context: %s\n\n" +
                "Indonesian text: %s\n\n" +
                "Provide only the English translation:",
                context,
                indonesianText
            );

            Prompt prompt = new Prompt(promptText);
            ChatResponse response = chatClient.call(prompt);
            String translatedText = response.getResult().getOutput().getContent().trim();

            System.out.println("Context: " + context);
            System.out.println("Original (ID): " + indonesianText);
            System.out.println("Translated (EN): " + translatedText);

            return new TranslationResponse(
                indonesianText,
                translatedText,
                "Indonesian",
                "English"
            );

        } catch (Exception e) {
            System.err.println("Error during translation with context: " + e.getMessage());
            
            return new TranslationResponse(
                indonesianText,
                "Error: Unable to translate. Please check your OpenAI API configuration.",
                "Indonesian",
                "English"
            );
        }
    }
}
