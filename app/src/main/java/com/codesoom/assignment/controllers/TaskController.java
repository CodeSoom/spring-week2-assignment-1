package com.codesoom.assignment.controllers;

import com.codesoom.assignment.TaskNotFoundException;
import com.codesoom.assignment.models.Task;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        Task task = findTask(id);
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

    private Task findTask(Long id) throws IOException {
        return tasks.stream()
                .filter(task -> Objects.equals(task.getId(), id))
                .findFirst()
                .orElseThrow(() -> new TaskNotFoundException("There is no task with that id"));
    }
}
