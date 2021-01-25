package com.codesoom.assignment.controllers;

import com.codesoom.assignment.exceptions.TaskNotFoundException;
import com.codesoom.assignment.models.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final List<Task> tasks = new ArrayList<>();

    @GetMapping
    public List<Task> getTaskList() {
        return tasks;
    }

    @GetMapping("/{id}")
    public Task getTask(@PathVariable long id) {
        return tasks.stream()
                .filter(i -> i.getId() == id)
                .findFirst()
                .orElseThrow(() -> new TaskNotFoundException("Not Found"));
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        Task newTask = new Task();
        newTask.generateId(tasks.size());
        newTask.setTitle(task.getTitle());

        tasks.add(newTask);

        return newTask;
    }
}
