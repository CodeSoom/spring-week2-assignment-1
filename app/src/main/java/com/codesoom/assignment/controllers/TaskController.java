package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private Map<Long, Task> taskMap = new ConcurrentHashMap<>();

    @GetMapping
    public List<Task> list() {
        return new ArrayList<>(taskMap.values());
    }

}
