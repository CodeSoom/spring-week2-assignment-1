package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import org.checkerframework.checker.units.qual.A;
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
    public List<Task> getTasks(){
        return tasks;
    }

    @PostMapping
    public Task addTask(@RequestBody Task task){

        task.setId(generateId());
        tasks.add(task);

        return task;
    }

    private Long generateId() {
        return newId++;
    }
}
