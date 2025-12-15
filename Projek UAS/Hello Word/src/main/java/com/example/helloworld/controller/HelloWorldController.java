package com.example.helloworld.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller untuk Hello World
 * Simple Hello World application
 */
@RestController
@CrossOrigin(origins = "*")
public class HelloWorldController {

    /**
     * Homepage - Simple Hello World
     */
    @GetMapping("/")
    public String home() {
        return """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Hello World - Java 21</title>
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
                            border-radius: 30px;
                            box-shadow: 0 30px 80px rgba(0,0,0,0.3);
                            padding: 80px 100px;
                            text-align: center;
                            animation: fadeIn 1s ease-in;
                        }
                        
                        @keyframes fadeIn {
                            from {
                                opacity: 0;
                                transform: translateY(-20px);
                            }
                            to {
                                opacity: 1;
                                transform: translateY(0);
                            }
                        }
                        
                        h1 {
                            font-size: 5em;
                            color: #667eea;
                            margin-bottom: 20px;
                            font-weight: 700;
                            text-shadow: 2px 2px 4px rgba(0,0,0,0.1);
                        }
                        
                        .badge {
                            display: inline-block;
                            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                            color: white;
                            padding: 10px 25px;
                            border-radius: 25px;
                            font-size: 1.1em;
                            font-weight: bold;
                            margin-top: 10px;
                        }
                        
                        .info {
                            margin-top: 30px;
                            color: #666;
                            font-size: 1em;
                        }
                        
                        @media (max-width: 768px) {
                            .container {
                                padding: 50px 30px;
                            }
                            
                            h1 {
                                font-size: 3em;
                            }
                        }
                    </style>
                </head>
                <body>
                    <div class="container">
                        <h1>👋 Hello World!</h1>
                        <span class="badge">Running on Java 21 LTS</span>
                        <div class="info">
                            <p>Spring Boot 3.2.1 + Java 21</p>
                            <p>Port: 8082</p>
                        </div>
                    </div>
                </body>
                </html>
                """;
    }

    /**
     * Simple text endpoint
     */
    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    /**
     * Health check endpoint
     */
    @GetMapping("/health")
    public String healthCheck() {
        return "Application is running on Java 21!";
    }
}
