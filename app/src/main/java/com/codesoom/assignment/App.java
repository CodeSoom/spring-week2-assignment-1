package com.codesoom.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
    private static final int PORT = 8080;
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
