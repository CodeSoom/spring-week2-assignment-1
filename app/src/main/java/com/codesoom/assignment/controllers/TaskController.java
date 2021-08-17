package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.repository.TaskRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {

    TaskRepository taskRepository = new TaskRepository();

    @GetMapping
    public Collection<Task> getTaskList() {
        return taskRepository.getTaskList();
    }

    @GetMapping("/{id}")
    public Optional<Task> getTaskById(@PathVariable Long id) {
        return Optional.ofNullable(taskRepository.getTaskById(id));
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskRepository.createTask(task);
    }

    @PutMapping("/{id}")
    public Task putTask(@PathVariable Long id, @RequestBody Task task) {
        return taskRepository.putTask(id, task);
    }

    @PatchMapping("/{id}")
    public Optional<Task> patchTask(@PathVariable Long id, @RequestBody Task task) {
        return taskRepository.patchTask(id, task);
    }

    @DeleteMapping("/{id}")
    public Optional<Task> deleteTask(@PathVariable Long id) {
        return taskRepository.deleteTask(id);
    }
}
