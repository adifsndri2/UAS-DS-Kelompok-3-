package com.example.translator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.translator.model.TranslationRequest;
import com.example.translator.model.TranslationResponse;
import com.example.translator.service.TranslatorService;

/**
 * REST Controller untuk Translation
 * Menangani HTTP requests untuk translation service
 */
@RestController
@CrossOrigin(origins = "*")
public class TranslatorController {

    private final TranslatorService translatorService;

    @Autowired
    public TranslatorController(TranslatorService translatorService) {
        this.translatorService = translatorService;
    }

    /**
     * Root endpoint - Welcome page with UI
     */
    @GetMapping("/")
    public String home() {
        return """
                <!DOCTYPE html>
                <html lang="id">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>🌐 Translator Indonesia - Inggris</title>
                    <style>
                        * {
                            margin: 0;
                            padding: 0;
                            box-sizing: border-box;
                        }
                        
                        body {
                            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                            min-height: 100vh;
                            display: flex;
                            justify-content: center;
                            align-items: center;
                            padding: 20px;
                        }
                        
                        .container {
                            background: white;
                            border-radius: 20px;
                            box-shadow: 0 20px 60px rgba(0,0,0,0.3);
                            max-width: 900px;
                            width: 100%;
                            padding: 40px;
                        }
                        
                        .header {
                            text-align: center;
                            margin-bottom: 30px;
                        }
                        
                        .header h1 {
                            color: #667eea;
                            font-size: 2.5em;
                            margin-bottom: 10px;
                        }
                        
                        .badge {
                            display: inline-block;
                            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                            color: white;
                            padding: 5px 15px;
                            border-radius: 20px;
                            font-size: 0.9em;
                            font-weight: bold;
                        }
                        
                        .translator-box {
                            display: grid;
                            grid-template-columns: 1fr 1fr;
                            gap: 20px;
                            margin-bottom: 20px;
                        }
                        
                        .input-box, .output-box {
                            background: #f8f9fa;
                            border-radius: 15px;
                            padding: 20px;
                        }
                        
                        .box-header {
                            display: flex;
                            align-items: center;
                            gap: 10px;
                            margin-bottom: 15px;
                            font-weight: bold;
                            color: #495057;
                        }
                        
                        .flag {
                            font-size: 1.5em;
                        }
                        
                        textarea {
                            width: 100%;
                            min-height: 200px;
                            border: 2px solid #e9ecef;
                            border-radius: 10px;
                            padding: 15px;
                            font-size: 1em;
                            font-family: inherit;
                            resize: vertical;
                            transition: border-color 0.3s;
                        }
                        
                        textarea:focus {
                            outline: none;
                            border-color: #667eea;
                        }
                        
                        .output-text {
                            min-height: 200px;
                            padding: 15px;
                            background: white;
                            border-radius: 10px;
                            border: 2px solid #e9ecef;
                            font-size: 1em;
                            line-height: 1.6;
                            color: #212529;
                        }
                        
                        .button-group {
                            display: flex;
                            gap: 10px;
                            justify-content: center;
                        }
                        
                        button {
                            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                            color: white;
                            border: none;
                            padding: 15px 40px;
                            border-radius: 10px;
                            font-size: 1.1em;
                            font-weight: bold;
                            cursor: pointer;
                            transition: transform 0.2s, box-shadow 0.2s;
                        }
                        
                        button:hover {
                            transform: translateY(-2px);
                            box-shadow: 0 5px 20px rgba(102, 126, 234, 0.4);
                        }
                        
                        button:active {
                            transform: translateY(0);
                        }
                        
                        button:disabled {
                            opacity: 0.6;
                            cursor: not-allowed;
                        }
                        
                        .clear-btn {
                            background: #6c757d;
                        }
                        
                        .loading {
                            display: none;
                            text-align: center;
                            color: #667eea;
                            font-weight: bold;
                            margin: 10px 0;
                        }
                        
                        .spinner {
                            display: inline-block;
                            width: 20px;
                            height: 20px;
                            border: 3px solid #f3f3f3;
                            border-top: 3px solid #667eea;
                            border-radius: 50%;
                            animation: spin 1s linear infinite;
                        }
                        
                        @keyframes spin {
                            0% { transform: rotate(0deg); }
                            100% { transform: rotate(360deg); }
                        }
                        
                        .info {
                            text-align: center;
                            margin-top: 20px;
                            padding: 15px;
                            background: #e7f3ff;
                            border-radius: 10px;
                            color: #004085;
                        }
                        
                        @media (max-width: 768px) {
                            .translator-box {
                                grid-template-columns: 1fr;
                            }
                            
                            .header h1 {
                                font-size: 1.8em;
                            }
                        }
                    </style>
                </head>
                <body>
                    <div class="container">
                        <div class="header">
                            <h1>🌐 Translator Indonesia - Inggris</h1>
                            <span class="badge">Powered by Java 21 + Spring AI + Ollama</span>
                        </div>
                        
                        <div class="translator-box">
                            <div class="input-box">
                                <div class="box-header">
                                    <span class="flag">🇮🇩</span>
                                    <span>Bahasa Indonesia</span>
                                </div>
                                <textarea id="inputText" placeholder="Ketik teks dalam Bahasa Indonesia di sini..."></textarea>
                            </div>
                            
                            <div class="output-box">
                                <div class="box-header">
                                    <span class="flag">🇬🇧</span>
                                    <span>English Translation</span>
                                </div>
                                <div id="outputText" class="output-text">Hasil terjemahan akan muncul di sini...</div>
                            </div>
                        </div>
                        
                        <div class="loading" id="loading">
                            <div class="spinner"></div>
                            <span>Sedang menerjemahkan...</span>
                        </div>
                        
                        <div class="button-group">
                            <button onclick="doTranslate()">✨ Terjemahkan</button>
                            <button class="clear-btn" onclick="clearText()">🗑️ Hapus</button>
                        </div>
                        
                        <div class="info">
                            💡 <strong>Tips:</strong> Ketik kalimat atau paragraf dalam Bahasa Indonesia, 
                            lalu klik tombol "Terjemahkan" untuk mendapatkan hasil dalam Bahasa Inggris.
                        </div>
                    </div>
                    
                    <script>
                        async function doTranslate() {
                            const inputText = document.getElementById('inputText').value.trim();
                            const outputDiv = document.getElementById('outputText');
                            const loading = document.getElementById('loading');
                            
                            console.log('Translate button clicked');
                            console.log('Input text:', inputText);
                            
                            if (!inputText) {
                                outputDiv.textContent = '⚠️ Silakan masukkan teks yang ingin diterjemahkan!';
                                outputDiv.style.color = '#dc3545';
                                return;
                            }
                            
                            // Show loading
                            loading.style.display = 'block';
                            outputDiv.textContent = 'Menerjemahkan...';
                            outputDiv.style.color = '#6c757d';
                            
                            try {
                                console.log('Sending request to /translate');
                                const response = await fetch('/translate', {
                                    method: 'POST',
                                    headers: {
                                        'Content-Type': 'application/json',
                                    },
                                    body: JSON.stringify({ text: inputText })
                                });
                                
                                console.log('Response status:', response.status);
                                
                                if (!response.ok) {
                                    const errorText = await response.text();
                                    console.error('Response error:', errorText);
                                    throw new Error('Translation failed: ' + response.status);
                                }
                                
                                const data = await response.json();
                                console.log('Response data:', data);
                                
                                if (data.translatedText) {
                                    outputDiv.textContent = data.translatedText;
                                    outputDiv.style.color = '#212529';
                                } else {
                                    outputDiv.textContent = '❌ Terjemahan tidak tersedia';
                                    outputDiv.style.color = '#dc3545';
                                }
                            } catch (error) {
                                console.error('Error:', error);
                                outputDiv.textContent = '❌ Terjadi kesalahan: ' + error.message + '\\n\\nPastikan Ollama sudah berjalan di http://localhost:11434';
                                outputDiv.style.color = '#dc3545';
                            } finally {
                                loading.style.display = 'none';
                            }
                        }
                        
                        function clearText() {
                            document.getElementById('inputText').value = '';
                            document.getElementById('outputText').textContent = 'Hasil terjemahan akan muncul di sini...';
                            document.getElementById('outputText').style.color = '#6c757d';
                        }
                        
                        // Allow Enter key to translate (with Ctrl/Cmd)
                        document.getElementById('inputText').addEventListener('keydown', function(e) {
                            if (e.key === 'Enter' && (e.ctrlKey || e.metaKey)) {
                                e.preventDefault();
                                doTranslate();
                            }
                        });
                    </script>
                </body>
                </html>
                """;
    }

    /**
     * Endpoint untuk translate text dari Indonesian ke English
     * 
     * @param request TranslationRequest berisi text yang akan ditranslate
     * @return TranslationResponse berisi hasil translation
     */
    @PostMapping("/translate")
    public TranslationResponse translateText(@RequestBody TranslationRequest request) {
        System.out.println("Received translation request: " + request.getText());
        
        // Validasi input
        if (request.getText() == null || request.getText().trim().isEmpty()) {
            return new TranslationResponse(
                request.getText(),
                "Error: Input text cannot be empty",
                "Indonesian",
                "English"
            );
        }

        // Call service untuk translate
        return translatorService.translateToEnglish(request.getText());
    }

    /**
     * Endpoint simple untuk translate via GET request
     * 
     * @param text Text yang akan ditranslate (query parameter)
     * @return String hasil translation
     */
    @GetMapping("/translate")
    public String translateSimple(@RequestParam String text) {
        System.out.println("Received simple translation request: " + text);
        
        if (text == null || text.trim().isEmpty()) {
            return "Error: Input text cannot be empty";
        }

        TranslationResponse response = translatorService.translateToEnglish(text);
        return response.getTranslatedText();
    }

    /**
     * Health check endpoint
     * 
     * @return String status
     */
    @GetMapping("/health")
    public String healthCheck() {
        return "Translator Service is running! Ready to translate Indonesian to English.";
    }

    /**
     * Endpoint untuk test translation
     * 
     * @return TranslationResponse contoh hasil translation
     */
    @GetMapping("/test")
    public TranslationResponse testTranslation() {
        return translatorService.translateToEnglish("Halo, apa kabar? Saya sedang belajar Spring AI.");
    }
}
