package com.codesoom.assignment.controllers;

import com.codesoom.assignment.domain.Task;
import org.springframework.web.bind.annotation.*;

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
        return tasks.get(id);
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        task.setId(0);
        tasks.put(task.getId(), task);
        return task;
    }
}
