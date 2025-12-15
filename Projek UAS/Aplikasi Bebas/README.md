# Chatbot Translator - Indonesian to English

Aplikasi translator sederhana menggunakan Spring AI yang dapat menerjemahkan teks dari Bahasa Indonesia ke Bahasa Inggris menggunakan OpenAI API.

## 📋 Deskripsi

Aplikasi ini adalah chatbot translator yang memanfaatkan kekuatan AI untuk menerjemahkan teks dari Bahasa Indonesia ke Bahasa Inggris. Aplikasi menyediakan REST API yang mudah digunakan untuk melakukan translation secara real-time.

## 🛠️ Teknologi yang Digunakan

- **Spring Boot 3.2.1** - Framework aplikasi
- **Spring AI 0.8.1** - Library untuk integrasi AI
- **OpenAI GPT-3.5-turbo** - Model AI untuk translation
- **Maven** - Build tool
- **Java 17** - Programming language

## 📁 Struktur Proyek

```
Aplikasi Bebas/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── example/
│       │           └── translator/
│       │               ├── TranslatorApplication.java         (Main class)
│       │               ├── controller/
│       │               │   └── TranslatorController.java      (REST endpoints)
│       │               ├── service/
│       │               │   └── TranslatorService.java         (Translation logic)
│       │               └── model/
│       │                   ├── TranslationRequest.java        (Request DTO)
│       │                   └── TranslationResponse.java       (Response DTO)
│       └── resources/
│           └── application.properties                          (Configuration)
├── pom.xml                                                     (Maven dependencies)
└── README.md                                                   (Dokumentasi ini)
```

## 🔧 Penjelasan Class/Method

### 1. TranslatorApplication.java
- **Class utama** yang menjalankan Spring Boot application
- Entry point untuk translator service

### 2. TranslatorController.java
- **REST Controller** yang menangani HTTP requests untuk translation
- **Endpoints:**
  - `POST /api/translator/translate` - Translate dengan JSON body
  - `GET /api/translator/translate?text=...` - Translate dengan query parameter
  - `GET /api/translator/health` - Health check
  - `GET /api/translator/test` - Test endpoint dengan contoh translation

### 3. TranslatorService.java
- **Service layer** yang berisi business logic untuk translation
- Method `translateToEnglish()` - Menerjemahkan text Indonesian ke English
- Method `translateWithContext()` - Translate dengan context tambahan
- Menggunakan `ChatClient` untuk komunikasi dengan OpenAI

### 4. TranslationRequest.java
- **DTO (Data Transfer Object)** untuk menerima input dari user
- Properties: `text` (text yang akan ditranslate)

### 5. TranslationResponse.java
- **DTO** untuk mengirim hasil translation ke user
- Properties:
  - `originalText` - Text asli dalam Indonesian
  - `translatedText` - Hasil translation dalam English
  - `sourceLanguage` - Bahasa sumber (Indonesian)
  - `targetLanguage` - Bahasa target (English)

## ⚙️ Setup dan Konfigurasi

### Prerequisites

1. **Java 17 atau lebih tinggi**
   ```bash
   java -version
   ```

2. **Maven 3.6 atau lebih tinggi**
   ```bash
   mvn -version
   ```

3. **OpenAI API Key**
   - Daftar di [OpenAI Platform](https://platform.openai.com/)
   - Buat API Key di [API Keys page](https://platform.openai.com/api-keys)

### Langkah Setup

1. **Set OpenAI API Key**
   
   **Opsi 1: Environment Variable (Recommended)**
   ```bash
   # Windows (PowerShell)
   $env:OPENAI_API_KEY="sk-your-api-key-here"
   
   # Windows (CMD)
   set OPENAI_API_KEY=sk-your-api-key-here
   
   # Linux/Mac
   export OPENAI_API_KEY="sk-your-api-key-here"
   ```

   **Opsi 2: Edit application.properties**
   ```properties
   spring.ai.openai.api-key=sk-your-api-key-here
   ```

2. **Navigate ke folder project**
   ```bash
   cd "Aplikasi Bebas"
   ```

3. **Install dependencies**
   ```bash
   mvn clean install
   ```

## 🚀 Cara Menjalankan Aplikasi

### Opsi 1: Menggunakan Maven
```bash
mvn spring-boot:run
```

### Opsi 2: Menggunakan JAR file
```bash
# Build JAR
mvn clean package

# Run JAR
java -jar target/chatbot-translator-1.0.0.jar
```

Aplikasi akan berjalan di: `http://localhost:8081`

## 📡 Testing Translation

### 1. Health Check
```bash
curl http://localhost:8081/api/translator/health
```

**Output:**
```
Translator Service is running! Ready to translate Indonesian to English.
```

### 2. Test Translation (GET)
```bash
curl "http://localhost:8081/api/translator/translate?text=Selamat pagi, apa kabar?"
```

**Output:**
```
Good morning, how are you?
```

### 3. Translation dengan POST (JSON)
```bash
curl -X POST http://localhost:8081/api/translator/translate ^
  -H "Content-Type: application/json" ^
  -d "{\"text\":\"Saya sedang belajar Spring AI dan ini sangat menarik!\"}"
```

**Output:**
```json
{
  "originalText": "Saya sedang belajar Spring AI dan ini sangat menarik!",
  "translatedText": "I am learning Spring AI and this is very interesting!",
  "sourceLanguage": "Indonesian",
  "targetLanguage": "English"
}
```

### 4. Test Endpoint
```bash
curl http://localhost:8081/api/translator/test
```

**Output:**
```json
{
  "originalText": "Halo, apa kabar? Saya sedang belajar Spring AI.",
  "translatedText": "Hello, how are you? I am learning Spring AI.",
  "sourceLanguage": "Indonesian",
  "targetLanguage": "English"
}
```

## 🌐 Testing dengan Browser atau Postman

### Menggunakan Browser (untuk GET requests)
1. **Health Check:**
   ```
   http://localhost:8081/api/translator/health
   ```

2. **Simple Translation:**
   ```
   http://localhost:8081/api/translator/translate?text=Selamat datang di aplikasi translator
   ```

3. **Test Endpoint:**
   ```
   http://localhost:8081/api/translator/test
   ```

### Menggunakan Postman (untuk POST requests)

1. **Buat New Request:**
   - Method: `POST`
   - URL: `http://localhost:8081/api/translator/translate`

2. **Headers:**
   ```
   Content-Type: application/json
   ```

3. **Body (raw JSON):**
   ```json
   {
     "text": "Saya suka belajar pemrograman dan teknologi baru"
   }
   ```

4. **Klik Send**

## 📊 Console Output

Saat aplikasi berjalan, Anda akan melihat log di console:
```
===========================================
Chatbot Translator Application Started!
Access at: http://localhost:8081
===========================================

Received translation request: Selamat pagi
Original (ID): Selamat pagi
Translated (EN): Good morning
```

## 💡 Contoh Penggunaan

### Contoh 1: Terjemahkan kalimat sederhana
**Input:**
```
Selamat pagi, nama saya Adi
```
**Output:**
```
Good morning, my name is Adi
```

### Contoh 2: Terjemahkan kalimat panjang
**Input:**
```
Saya sedang mengembangkan aplikasi web menggunakan Spring Boot dan Spring AI untuk membuat sistem translator otomatis
```
**Output:**
```
I am developing a web application using Spring Boot and Spring AI to create an automatic translator system
```

### Contoh 3: Terjemahkan pertanyaan
**Input:**
```
Bagaimana cara menginstall Spring AI di project Maven?
```
**Output:**
```
How to install Spring AI in a Maven project?
```

## 🐛 Troubleshooting

### Error: "401 Unauthorized"
- **Penyebab:** API Key tidak valid atau tidak ter-set
- **Solusi:** Periksa OpenAI API Key Anda dan pastikan sudah ter-set dengan benar

### Error: "Input text cannot be empty"
- **Penyebab:** Request body kosong atau text field kosong
- **Solusi:** Pastikan mengirim JSON dengan field "text" yang tidak kosong

### Error: "Connection refused"
- **Penyebab:** Tidak ada koneksi internet atau OpenAI service down
- **Solusi:** Periksa koneksi internet Anda

### Error: "Port 8081 already in use"
- **Solusi:** Ubah port di `application.properties`:
  ```properties
  server.port=8082
  ```

## 📝 Notes

- **Temperature di-set 0.3** untuk menghasilkan translation yang lebih akurat dan konsisten
- Aplikasi menggunakan **GPT-3.5-turbo** yang cepat dan cost-effective
- Error handling sudah diimplementasikan untuk menangani kegagalan API call
- Semua translation di-log ke console untuk monitoring

## 🎯 Fitur yang Bisa Ditambahkan

1. **Reverse Translation** - English ke Indonesian
2. **Multiple Language Support** - Tambah bahasa lain
3. **Translation History** - Simpan history translation ke database
4. **Batch Translation** - Translate multiple texts sekaligus
5. **Web UI** - Frontend untuk input dan display translation
6. **Voice Input** - Integrasi dengan speech-to-text
7. **Translation Quality Score** - Rating kualitas translation

## 🔄 Cara Extend Aplikasi

### Menambah Language Support
Edit `TranslatorService.java`:
```java
public TranslationResponse translateToSpanish(String text) {
    String promptText = String.format(
        "Translate the following text to Spanish: %s", text
    );
    // ... rest of the code
}
```

### Menambah Context untuk Translation
Gunakan method `translateWithContext()`:
```java
translatorService.translateWithContext(
    "Saya mau pesan nasi goreng", 
    "Restaurant context"
);
```

## 📚 Referensi

- [Spring AI Documentation](https://docs.spring.io/spring-ai/reference/)
- [OpenAI API Documentation](https://platform.openai.com/docs/)
- [Spring Boot REST API Guide](https://spring.io/guides/gs/rest-service/)
- [Maven Documentation](https://maven.apache.org/guides/)

## 🤝 Tips Penggunaan

1. **Untuk hasil translation terbaik:**
   - Gunakan kalimat yang jelas dan grammatically correct
   - Hindari slang atau bahasa gaul yang sangat lokal
   - Gunakan tanda baca yang tepat

2. **Monitoring & Debugging:**
   - Cek console untuk melihat original text dan hasil translation
   - Enable DEBUG logging untuk troubleshooting
   - Gunakan health check endpoint untuk verify service status

3. **Production Ready:**
   - Set API key sebagai environment variable (jangan hardcode)
   - Implement rate limiting untuk production
   - Add caching untuk translation yang sering digunakan
   - Implement proper error handling dan logging

---

**Dibuat untuk pembelajaran Spring AI - Translation Service** 🌐🤖
