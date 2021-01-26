package com.codesoom.assignment.controllers;

import com.codesoom.assignment.application.TaskService;
import com.codesoom.assignment.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    TaskService taskService = new TaskService();

    @GetMapping
    public List<Task> getTasks() {
        return taskService.getTasks();
    }

    @GetMapping("/{id}")
    public Task getTask(@PathVariable long id) {
        return taskService.getTask(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody Task task) {
        return taskService.createNewTask(task.getTitle());
    }

    @PutMapping("/{id}")
    public Task putTask(@PathVariable long id, @RequestBody Task task) {
        return taskService.updateTask(id, task.getTitle());
    }

    @PatchMapping("/{id}")
    public Task patchTask(@PathVariable long id, @RequestBody Task task) {
        return taskService.updateTask(id, task.getTitle());
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable long id) {
        taskService.deleteTask(id);
    }
}
