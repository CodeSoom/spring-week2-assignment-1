package com.codesoom.assignment.controllers;

import com.codesoom.assignment.App;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping("/")
    public String sayHello() {
        return "Hello, world!!!!!!";
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }
}
