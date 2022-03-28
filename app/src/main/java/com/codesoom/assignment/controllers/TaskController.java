package com.codesoom.assignment.controllers;

import com.codesoom.assignment.domains.Task;
import com.codesoom.assignment.services.TaskService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public List<Task> readTasks() {
        return taskService.readTasks();
    }

    @GetMapping("/tasks/{taskId}")
    public Task readTask() {
        return taskService.readTask();
    }

    @PostMapping("/tasks")
    public Task addTask() {
        return taskService.addTask();
    }

    @PutMapping("/tasks/{id}")
    public Task editTask() {
        return taskService.editTask();
    }

    @PatchMapping("/tasks/{id}")
    public Task editTaskElement() {
        return taskService.editTask();
    }

    @DeleteMapping("/tasks/{id}")
    public Task deleteTask() {
        return taskService.deleteTask();
    }
}
