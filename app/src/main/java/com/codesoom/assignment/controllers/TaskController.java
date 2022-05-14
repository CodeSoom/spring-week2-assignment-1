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
        return taskService.getTaskList();
    }

    @GetMapping("{id}")
    public Optional<Task> getTaskItem(@PathVariable("id") Long id) {
        return taskService.getTaskItem(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Task create(@RequestBody Task task) {
        return taskService.addTask(task);
    }

    @PutMapping("/{id}")
    public Optional<Task> update(@PathVariable("id") Long id,
                                 @RequestBody Task source) {
        return taskService.updateTask(id, source);
    }

    @PatchMapping("/{id}")
    public Optional<Task> patch(@PathVariable("id") Long id,
                                @RequestBody Task source) {
        return taskService.updateTask(id, source);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = {"", "/", "/{id}"})
    public boolean delete(@PathVariable(required = false) Long id) {
        return taskService.deleteTask(id);
    }
}
