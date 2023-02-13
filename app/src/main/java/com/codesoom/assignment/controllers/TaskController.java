package com.codesoom.assignment.controllers;

import com.codesoom.assignment.model.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/*
TODO
1. READ Collection -> get/tasks
2. read item - get/tasks/{id}
3. create - post/tasks/{id}
4. update - put/patch
 */
@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {
    private List<Task> tasks = new ArrayList<>();
    private Long newId = 0L;

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

    private Long generateId() {
        newId += 1;
        return newId;
    }
}
