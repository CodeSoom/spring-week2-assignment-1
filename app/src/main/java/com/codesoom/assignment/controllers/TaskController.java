package com.codesoom.assignment.controllers;

import com.codesoom.assignment.model.Task;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final List<Task> tasks = new ArrayList<>();
    private Long newId = 0L;

    private Long generateId() {
        return ++newId;
    }

    @GetMapping("")
    public List<Task> sayHello() {
        return tasks;
    }

    @PostMapping("")
    public Task postTask(@RequestBody final Task task) {
        task.setId(generateId());
        tasks.add(task);
        return task;
    }
}
