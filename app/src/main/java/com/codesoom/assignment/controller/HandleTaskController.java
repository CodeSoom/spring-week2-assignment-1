package com.codesoom.assignment.controller;

import com.codesoom.assignment.domain.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class HandleTaskController {
    private List<Task> tasks = new ArrayList<>();

    @GetMapping
    public List<Task> list() {
        return tasks;
    }

    @PostMapping
    public Task save(@RequestBody Task source) {
        Task target = new Task(source.getTitle());
        tasks.add(target);
        return target;
    }
}
