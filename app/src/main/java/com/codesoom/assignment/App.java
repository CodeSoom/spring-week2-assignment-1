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
        /*
        try {
            final String LOCAL_HOST = "localhost";
            final int PORT = 8000;
            InetSocketAddress address = new InetSocketAddress(LOCAL_HOST, PORT);
            HttpServer httpServer = HttpServer.create(address, 0);
            // 404, 500 error를 방지하기 위한 로직
            HttpHandler handler = new DemoHttpHandler();
            httpServer.createContext("/", handler);
            // 서버 시작
            httpServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

         */

    }
}
