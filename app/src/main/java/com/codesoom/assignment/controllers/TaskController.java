package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private HashMap<Long, String> tasks = new HashMap<>();
    
    @GetMapping
    public HashMap<Long, String> list(){
        return tasks;
    }

}

