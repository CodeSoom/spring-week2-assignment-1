package com.codesoom.assignment.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {
    @GetMapping
    public String list(){
        return "hello";
    }

    @PostMapping
    public String create(){
        return "post hello";
    }

}
