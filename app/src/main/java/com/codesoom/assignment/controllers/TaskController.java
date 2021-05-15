package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private List<Task> tasks = new ArrayList<>();
    private Long newId = 0L;

    @GetMapping
    public List<Task> list() {
        return tasks;
    }

    @GetMapping("/{id}")
    public Task item(@PathVariable("id") Long id) {
        Task task = findTask(id);
        return task;
    }

    @PostMapping
    public Task create(@RequestBody Task task) {
        task.setId(generateId());
        tasks.add(task);

        return task;
    }

    @PutMapping("/{id}")
    public Task updateItem(@PathVariable("id") Long id, @RequestBody Task updateItem) {
        return modifyTask(id, updateItem);
    }

    @PatchMapping("/{id}")
    public Task patchItem(@PathVariable("id") Long id, @RequestBody Task patchItem) {
       return modifyTask(id, patchItem);
    }

    @DeleteMapping("/{id}")
    public Task deleteItem(@PathVariable("id") Long id) {
        Task task = findTask(id);
        tasks.remove(task);
        return task;
    }

    private Long generateId() {
        newId += 1;
        return newId;
    }

    private Task findTask(Long id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    private Task modifyTask(Long id, Task modifyItem) {
        Task task = findTask(id);
        task.setTitle(modifyItem.getTitle());
        return task;
    }
}