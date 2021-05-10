package com.codesoom.assignment.controllers;

import com.codesoom.assignment.dto.Task;
import com.codesoom.assignment.exceptions.DoesNotExistException;
import com.codesoom.assignment.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
@RequestMapping(path = "/tasks")
public class TaskController {

    private final TaskRepository taskRepository;
    private Long newId = 0L;

    @Autowired
    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return this.taskRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody Task task) {
        task.setId(generateId());
        return this.taskRepository.addTask(task);
    }

    @GetMapping(path = "/{id}")
    public Task getTask(@PathVariable Long id) {
        return this.taskRepository.getOneTask(id);
    }

    @PutMapping(path = "/{id}")
    public Task setTask(@PathVariable Long id, @RequestBody Task task) {
        task.setId(id);
        return this.taskRepository.setOneTask(task);
    }

    private Long generateId() {
        this.newId += 1;
        return this.newId;
    }
}
