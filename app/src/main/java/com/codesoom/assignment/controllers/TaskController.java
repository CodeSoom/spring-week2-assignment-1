package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.repositories.TaskRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping
    public List<Task> list() {
        return taskRepository.findAll();
    }

    @PostMapping
    public Task create(@RequestBody Task task) {
        return taskRepository.save(task);
    }

}
