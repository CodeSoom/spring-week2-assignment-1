package com.codesoom.assignment.controllers;

import com.codesoom.assignment.NotFoundException;
import com.codesoom.assignment.application.TaskService;
import com.codesoom.assignment.models.Task;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {
    private TaskService taskService;

    public TaskController() {
        taskService = new TaskService();
    }

    @GetMapping
    public List<Task> list() {
        return taskService.getTasks();
    }

    @GetMapping("/{id}")
    public Task get(@PathVariable Long id) throws IOException {
        return taskService.getTask(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task create(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @PutMapping("/{id}")
    public Task update(@PathVariable Long id, @RequestBody Task newTask) throws IOException {
        return taskService.updateTask(id, newTask);
    }

    @PatchMapping("/{id}")
    public Task patch(@PathVariable Long id, @RequestBody Task newTask) throws IOException {
        return taskService.updateTask(id, newTask);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws IOException {
        taskService.deleteTask(id);
    }
}
