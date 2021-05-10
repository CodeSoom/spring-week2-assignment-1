package com.codesoom.assignment.controllers;

import com.codesoom.assignment.dto.Task;
import com.codesoom.assignment.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Task createTask(@RequestBody Task task) {
        task.setId(generateId());
        return this.taskRepository.addTask(task);
    }

    private Long generateId() {
        this.newId += 1;
        return this.newId;
    }
}
