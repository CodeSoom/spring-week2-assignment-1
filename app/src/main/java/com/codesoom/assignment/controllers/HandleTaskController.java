package com.codesoom.assignment.controllers;

import com.codesoom.assignment.dto.Task;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class HandleTaskController {
    private List<Task> tasks = new ArrayList<>();

    @GetMapping
    public List<Task> list() {
        Task task = new Task();
        task.setId(1L);
        task.setTitle("hello");

        tasks.add(task);
        return tasks;
    }
}
