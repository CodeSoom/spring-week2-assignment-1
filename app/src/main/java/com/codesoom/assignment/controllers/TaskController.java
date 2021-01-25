package com.codesoom.assignment.controllers;

import com.codesoom.assignment.domain.Task;
import com.codesoom.assignment.domain.Tasks;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private Tasks tasks = new Tasks();
    private Long id = 0L;

    @GetMapping
    public List<Task> getTasks() {
        return tasks.getTasks();
    }
}
