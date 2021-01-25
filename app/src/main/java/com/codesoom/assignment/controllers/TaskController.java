package com.codesoom.assignment.controllers;


import com.codesoom.assignment.models.Task;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private List<Task> tasks = new ArrayList<>();


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
