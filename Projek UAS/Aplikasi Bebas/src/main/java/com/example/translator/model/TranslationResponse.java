package com.example.translator.model;

/**
 * Response Model untuk translation
 * Digunakan untuk mengirim hasil translation ke user
 */
public class TranslationResponse {

    private String originalText;
    private String translatedText;
    private String sourceLanguage;
    private String targetLanguage;

    // Constructor
    public TranslationResponse() {
    }

    public TranslationResponse(String originalText, String translatedText, 
                              String sourceLanguage, String targetLanguage) {
        this.originalText = originalText;
        this.translatedText = translatedText;
        this.sourceLanguage = sourceLanguage;
        this.targetLanguage = targetLanguage;
    }

    // Getters dan Setters
    public String getOriginalText() {
        return originalText;
    }

    public void setOriginalText(String originalText) {
        this.originalText = originalText;
    }

    public String getTranslatedText() {
        return translatedText;
    }

    public void setTranslatedText(String translatedText) {
        this.translatedText = translatedText;
    }

    public String getSourceLanguage() {
        return sourceLanguage;
    }

    public void setSourceLanguage(String sourceLanguage) {
        this.sourceLanguage = sourceLanguage;
    }

    public String getTargetLanguage() {
        return targetLanguage;
    }

    public void setTargetLanguage(String targetLanguage) {
        this.targetLanguage = targetLanguage;
    }

    @Override
    public String toString() {
        return "TranslationResponse{" +
                "originalText='" + originalText + '\'' +
                ", translatedText='" + translatedText + '\'' +
                ", sourceLanguage='" + sourceLanguage + '\'' +
                ", targetLanguage='" + targetLanguage + '\'' +
                '}';
    }
}
