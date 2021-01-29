package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private List<Task> tasks = new ArrayList<>();
    private Long Id = 0L;

    @GetMapping
    public List<Task> list() {
        return tasks;
    }

    @PostMapping
    public Task create(@RequestBody Task task) {
        task.setId(generateId());
        tasks.add(task);
        return task;
    }

    @PutMapping("/{id}")
    public Task update(@PathVariable Long id, @RequestBody Task source)
    {
        Task task = getTask(id);
        task.setTitle(source.getTitle());
        return task;
    }

    private Task getTask(Long id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst().orElse(null);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id, @RequestBody Task task)
    {

        task = getTask(id);
        tasks.remove(task);
        return "";
    }

    private Long generateId() {
        Id++;
        return Id;
    }

}
