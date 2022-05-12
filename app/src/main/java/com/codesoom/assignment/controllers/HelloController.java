package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.repository.TaskRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/tasks")
public class HelloController {
    private TaskRepository taskRepository = TaskRepository.getInstance();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final List<Task> tasks = new ArrayList<>();

    @GetMapping
    public List<Task> getTasks() {
        List<Task> findTasks = taskRepository.findAll();
        return findTasks;
    }

    @GetMapping("/{id}")
    public Task getTask(@PathVariable Long id) {
        Task findTask = taskRepository.findById(id);
        return findTask;
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        Task savedTask = taskRepository.save(task);
        return savedTask;
    }

    @PatchMapping("/{id}")
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task task) {
        Task changedTask = taskRepository.update(id, task);
        return changedTask;
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskRepository.delete(id);
    }
}
