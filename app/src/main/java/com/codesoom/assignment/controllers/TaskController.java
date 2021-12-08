package com.codesoom.assignment.controllers;

// TODO
// 1. GET /tasks (done)
// 2. GET /tasks/{id}
// 3. POST /tasks
// 4. PUT/PATCH /tasks/{id}
// 5. DELETE /tasks/{id}

import com.codesoom.assignment.models.Task;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private List<Task> tasks = new ArrayList<>();

    @GetMapping
    public List<Task> list() {
        return tasks;
    }

}
