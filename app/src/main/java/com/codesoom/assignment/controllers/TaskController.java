package com.codesoom.assignment.controllers;

import com.codesoom.assignment.NotFoundException;
import com.codesoom.assignment.models.Task;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public Task get(@PathVariable Long id) throws IOException {
        Task task = findById(id);
        return task;
    }

    @PostMapping
    public Task create(@RequestBody Task task) {
        task.setId(generateId());
        tasks.add(task);

        return task;
    }

    private Long generateId() {
        newId += 1;
        return newId;
    }

    private Task findById(Long id) throws IOException {
        Optional<Task> task = tasks.stream().filter(i -> i.getId().equals(id)).findFirst();

        if (task.isEmpty()) throw new NotFoundException();
        return task.get();
    }
}
