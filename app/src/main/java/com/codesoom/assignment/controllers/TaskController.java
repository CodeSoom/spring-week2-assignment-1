package com.codesoom.assignment.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @GetMapping
    public String list()
    {
        return "LIST";
    }

    @PostMapping
    public String create()
    {
        return "Created";
    }


}
