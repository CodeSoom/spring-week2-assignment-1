package com.codesoom.assignment;

import com.sun.net.httpserver.HttpServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.server.reactive.HttpHandler;

import java.io.IOException;
import java.net.InetSocketAddress;

@SpringBootApplication
public class App {
    private static final String LOCAL_HOST = "localhost";
    private static final int PORT = 8000;
    private static final int BACKLOG = 0;


    public String getGreeting() {
        return "Hello, world!";
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
