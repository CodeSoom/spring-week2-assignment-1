package com.codesoom.assignment.controllers;

import com.codesoom.assignment.domain.Task;
import com.codesoom.assignment.domain.Tasks;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private Tasks tasks = new Tasks();
    private Long taskId = 0L;

    @GetMapping
    public List<Task> getTasks() {
        return tasks.getTasks();
    }

    @PostMapping
    public Task add(@RequestBody Task task) {
        task.setId(generateId());
        tasks.addTask(task);
        return task;
    }

    private Long generateId() {
        return taskId++;
    }
}
