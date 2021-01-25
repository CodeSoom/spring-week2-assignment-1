package com.codesoom.assignment.controllers;

import com.codesoom.assignment.domain.Task;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private Map<Long, Task> tasks = new HashMap<>();

    @GetMapping
    public List<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    @GetMapping("/{id}")
    public Task getTask(@PathVariable long id) {
        return getTaskById(id);
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        task.setId(0);
        tasks.put(task.getId(), task);
        return task;
    }

    @PutMapping("/{id}")
    public Task putTask(@PathVariable long id, @RequestBody Task newTask) {
        return updateTask(id, newTask);
    }

    @PatchMapping("/{id}")
    public Task patchTask(@PathVariable long id, @RequestBody Task newTask) {
        return updateTask(id, newTask);
    }

    private Task updateTask(long id, Task newTask) {
        Task task = tasks.get(id);
        task.setTitle(newTask.getTitle());
        return task;
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable long id) {
        Task task = getTask(id);
        tasks.remove(task.getId());
    }

    private Task getTaskById(long id) throws IllegalArgumentException {
        return findTaskById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found"));
    }

    private Optional<Task> findTaskById(long id) {
        return Optional.ofNullable(tasks.get(id));
    }
}
