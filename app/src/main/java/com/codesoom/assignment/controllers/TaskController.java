package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        return taskService.handleList();
    }

    @GetMapping("{id}")
    public Optional<Task> getItem(@PathVariable("id") Long id) {
        return taskService.handleItem(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Task create(@RequestBody Task task) {
        return taskService.handleCreate(task);
    }

    @PutMapping("/{id}")
    public Optional<Task> update(@PathVariable("id") Long id,
                                 @RequestBody Task source) {
        return taskService.handleUpdate(id, source);
    }

    @PatchMapping("/{id}")
    public Optional<Task> patch(@PathVariable("id") Long id,
                                @RequestBody Task source) {
        return taskService.handleUpdate(id, source);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = {"", "/", "/{id}"})
    public boolean delete(@PathVariable(required = false) Long id) {
        return taskService.handleDelete(id);
    }
}
