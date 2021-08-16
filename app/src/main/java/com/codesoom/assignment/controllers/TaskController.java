package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.repository.TaskRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;

@RestController
@CrossOrigin
public class TaskController {

    TaskRepository taskRepository = new TaskRepository();

    @GetMapping("/tasks")
    public Collection<Task> getTaskList() {
        return taskRepository.getTaskList();
    }

    @GetMapping("/tasks/{id}")
    public Optional<Task> getTaskById(@PathVariable Long id) {
        return Optional.ofNullable(taskRepository.getTaskById(id));
    }

    @PostMapping("/tasks")
    public Task createTask(@RequestBody Task task) {
        return taskRepository.createTask(task);
    }

    @PutMapping("/tasks")
    public Task putTask(@RequestBody Task task) {
        return taskRepository.putTask(task);
    }
}
