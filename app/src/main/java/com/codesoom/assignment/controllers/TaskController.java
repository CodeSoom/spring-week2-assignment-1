package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {

    private List<Task> tasks = new ArrayList<>();
    private Long newId = 0L;


    @GetMapping
    public List<Task> list() {
        return tasks;
    }

    @PostMapping
    public Task create(@RequestBody Task task) {
        task.setId(generatedId());
        tasks.add(task);
        return task;
    }

    private Long generatedId() {
        newId += 1;
        return newId;

    }

}
