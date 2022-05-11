package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.repository.TaskRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public String sayHello() {
        return "Hello, world!";
    }

    @GetMapping("/")
    public List<Task> getTasks() {
        List<Task> findTasks = taskRepository.findAll();
        return findTasks;
    }
}

/*
    GET  /tasks
    GET  /tasks/{id}
    POST /tasks
    PUT/PATCH /tasks/{id}
    DELETE /tests/{id}
 */