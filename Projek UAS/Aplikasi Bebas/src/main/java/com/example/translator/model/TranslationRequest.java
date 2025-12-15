package com.example.translator.model;

/**
 * Request Model untuk translation
 * Digunakan untuk menerima input text dari user
 */
public class TranslationRequest {

    private String text;

    // Constructor
    public TranslationRequest() {
    }

    public TranslationRequest(String text) {
        this.text = text;
    }

    // Getter dan Setter
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "TranslationRequest{" +
                "text='" + text + '\'' +
                '}';
    }
}
