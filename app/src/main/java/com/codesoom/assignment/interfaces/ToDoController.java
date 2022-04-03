package com.codesoom.assignment.interfaces;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ToDoController {
    @RequestMapping("/")
    public String sayHello() {
        return "Hello, world!";
    }
}
