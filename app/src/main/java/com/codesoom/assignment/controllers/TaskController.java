package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.models.TaskStorage;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 1800)
public class TaskController {
    private final TaskStorage taskStorage = new TaskStorage();

    @GetMapping
    public List<Task> list() {
        return taskStorage.list();
    }

    @GetMapping("/{id}")
    public Task read(@PathVariable Long id) {
        return taskStorage.read(id);
    }

    @PostMapping
    public Task create(@RequestBody Task task) {
        return taskStorage.create(task);
    }
}
