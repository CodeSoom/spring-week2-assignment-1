package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 * 1. Read Collection - GET /tasks
  */


@RestController
@RequestMapping("/tasks")
public class TaskController {
    private List<Task> tasks = new ArrayList<>();
    private Long newId = 0L;

    @GetMapping
    public List<Task> list() {
        return tasks;
    }

    @PostMapping
    public Task created() {
        String title = "test";

        Task task = new Task();
        task.setId(generatedId());
        task.setTitle(title);
        tasks.add(task);

        return task;
    }

    private Long generatedId() {
        newId += 1;
        return newId;
    }
}
