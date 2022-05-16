package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getList() {
        return taskService.getTaskList();
    }

    @GetMapping("{id}")
    public Task getTask(@PathVariable("id") Long id) {
        return taskService.getTask(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Task create(@RequestBody Task task) {
        return taskService.addTask(task);
    }

    @PutMapping("/{id}")
    public Task update(@PathVariable("id") Long id, @RequestBody Task source) {
        return taskService.updateTask(id, source);
    }

    @PatchMapping("/{id}")
    public Task patch(@PathVariable("id") Long id, @RequestBody Task source) {
        return taskService.updateTask(id, source);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = {"", "/", "/{id}"})
    public boolean delete(@PathVariable(required = false) Long id) {
        return taskService.deleteTask(id);
    }
}
