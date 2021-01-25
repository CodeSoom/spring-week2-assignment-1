package com.codesoom.assignment.controllers;

import com.codesoom.assignment.domain.Task;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @GetMapping
    public List<Task> getTasks() {
        return Collections.EMPTY_LIST;
    }
}
