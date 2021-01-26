package com.codesoom.assignment.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class HelloController {
    @RequestMapping("/")
    public String sayHello() {
        return "Hello, world!";
    }
}
