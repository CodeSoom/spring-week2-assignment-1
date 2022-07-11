package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private HashMap<Long, String> tasks = new HashMap<>();
    private Long curTaskID = 0L;

    @GetMapping
    public HashMap<Long, String> list(){
        return tasks;
    }

    @PostMapping
    public Task create(@RequestBody Task task){
        task.setId(generateID());
        tasks.put(task.getId(), task.getTitle());
        return task;
    }

    private Long generateID(){
        curTaskID += 1;
        return curTaskID;
    }
}

