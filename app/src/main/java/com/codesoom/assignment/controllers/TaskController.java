package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @GetMapping
    public List<Task> getTasks(@RequestParam Long id){
        return null;
        //return task;
    }
}
