package com.codesoom.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.InetSocketAddress;

@SpringBootApplication
public class App {
    public String getGreeting() {
        return "HTTP START";
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args); // 실행 시 첫번째 인자의 클래스가 들어간다.
    }
}
