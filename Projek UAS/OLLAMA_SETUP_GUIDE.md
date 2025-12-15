# 🚀 Setup Guide - Ollama (Free Local AI)

## ✨ Keuntungan Menggunakan Ollama

✅ **GRATIS 100%** - Tidak perlu API key atau bayar subscription
✅ **Privacy** - Semua data diproses di komputer Anda sendiri
✅ **Offline** - Bisa digunakan tanpa internet setelah model di-download
✅ **Cepat** - Response time yang baik untuk model lokal
✅ **Mudah** - Setup sederhana dan mudah digunakan

---

## 📥 Langkah 1: Install Ollama

### Windows:
1. Download Ollama dari: **https://ollama.com/download/windows**
2. Jalankan installer `OllamaSetup.exe`
3. Tunggu instalasi selesai
4. Ollama akan otomatis berjalan di background

### Verifikasi Instalasi:
```powershell
ollama --version
```

---

## 📦 Langkah 2: Download Model AI

Ollama mendukung berbagai model. Untuk aplikasi ini, saya rekomendasikan **Llama 3.2** (ringan dan cepat):

```powershell
# Download model Llama 3.2 (sekitar 2GB)
ollama pull llama3.2
```

**Model lain yang bisa Anda coba:**
```powershell
# Llama 3.2 3B (lebih kecil, lebih cepat - RECOMMENDED)
ollama pull llama3.2

# Llama 3.1 8B (lebih besar, lebih pintar)
ollama pull llama3.1

# Gemma 2B (sangat ringan dari Google)
ollama pull gemma:2b

# Phi-3 (Microsoft, sangat bagus untuk coding)
ollama pull phi3
```

---

## 🔍 Langkah 3: Cek Model yang Tersedia

```powershell
ollama list
```

Output akan menampilkan model yang sudah di-download:
```
NAME            ID              SIZE    MODIFIED
llama3.2:latest abc123def456    2.0GB   2 minutes ago
```

---

## 🧪 Langkah 4: Test Ollama (Optional)

Test langsung di command line:
```powershell
ollama run llama3.2 "Hello, how are you?"
```

Atau test via API:
```powershell
Invoke-WebRequest -Uri "http://localhost:11434/api/generate" -Method POST -Body '{"model":"llama3.2","prompt":"Hello!","stream":false}' -ContentType "application/json"
```

---

## 🚀 Langkah 5: Rebuild dan Run Aplikasi

### Rebuild kedua aplikasi (untuk download dependency Ollama):

**Terminal 1 - Translator App:**
```powershell
cd "Aplikasi Bebas"
mvn clean install -DskipTests
mvn spring-boot:run
```

**Terminal 2 - Hello World App:**
```powershell
cd "Hello Word"
mvn clean install -DskipTests
mvn spring-boot:run
```

---

## 🎯 Testing Aplikasi

### Test Hello World App:
```powershell
# Health check
Invoke-WebRequest -Uri "http://localhost:8080/api/health" -UseBasicParsing

# Get Hello World dari AI
Invoke-WebRequest -Uri "http://localhost:8080/api/hello" -UseBasicParsing

# Get greeting message
Invoke-WebRequest -Uri "http://localhost:8080/api/greet" -UseBasicParsing
```

### Test Translator App:
```powershell
# Health check
Invoke-WebRequest -Uri "http://localhost:8081/api/translator/health" -UseBasicParsing

# Test translation (GET)
Invoke-WebRequest -Uri "http://localhost:8081/api/translator/translate?text=Selamat pagi" -UseBasicParsing

# Test translation (POST dengan JSON)
$body = @{text="Saya sedang belajar Spring AI"} | ConvertTo-Json
Invoke-WebRequest -Uri "http://localhost:8081/api/translator/translate" -Method POST -Body $body -ContentType "application/json" -UseBasicParsing
```

---

## ⚙️ Konfigurasi Model

Jika ingin menggunakan model lain, edit `application.properties`:

```properties
# Ganti model sesuai yang sudah di-download
spring.ai.ollama.chat.options.model=llama3.2
# atau
spring.ai.ollama.chat.options.model=gemma:2b
# atau
spring.ai.ollama.chat.options.model=phi3
```

---

## 🐛 Troubleshooting

### Error: "Connection refused to localhost:11434"
**Solusi:**
1. Pastikan Ollama sudah running:
   ```powershell
   Get-Process | Where-Object {$_.ProcessName -like "*ollama*"}
   ```
2. Restart Ollama dari Start Menu atau:
   ```powershell
   ollama serve
   ```

### Error: Model tidak ditemukan
**Solusi:**
```powershell
# Lihat model yang tersedia
ollama list

# Download model yang dibutuhkan
ollama pull llama3.2
```

### Response lambat atau error "out of memory"
**Solusi:**
- Gunakan model yang lebih kecil: `gemma:2b` atau `llama3.2:1b`
- Tutup aplikasi lain yang menggunakan banyak RAM

---

## 📊 Perbandingan Model

| Model | Size | Speed | Quality | Use Case |
|-------|------|-------|---------|----------|
| llama3.2 | ~2GB | ⚡⚡⚡ | ⭐⭐⭐ | Recommended untuk general purpose |
| gemma:2b | ~1.5GB | ⚡⚡⚡⚡ | ⭐⭐ | Cepat, cocok untuk laptop lemah |
| llama3.1 | ~4.5GB | ⚡⚡ | ⭐⭐⭐⭐ | Lebih pintar, butuh RAM lebih |
| phi3 | ~2.3GB | ⚡⚡⚡ | ⭐⭐⭐⭐ | Excellent untuk coding tasks |

---

## 💡 Tips

1. **Ollama berjalan sebagai service** - tidak perlu menjalankan manual setiap kali
2. **Model tersimpan lokal** - hanya download sekali, bisa digunakan selamanya
3. **Bisa ganti model on-the-fly** - edit `application.properties` dan restart app
4. **No internet needed** - setelah model di-download, bisa offline
5. **Privacy terjaga** - semua data diproses di komputer Anda

---

## 📚 Resources

- Official Website: https://ollama.com
- Model Library: https://ollama.com/library
- Documentation: https://github.com/ollama/ollama
- Spring AI Ollama Docs: https://docs.spring.io/spring-ai/reference/api/clients/ollama-chat.html

---

**Selamat mencoba! 🎉 Aplikasi Spring AI Anda sekarang menggunakan AI gratis!**
