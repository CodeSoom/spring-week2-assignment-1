package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final Logger logger = LoggerFactory.getLogger(TaskController.class);
    private Map<Long, Task> tasks = new HashMap();
    private Long newTaskId = 1L;

    @GetMapping
    public ResponseEntity<List<Task>> get() {
        var tasksList = new ArrayList<>(tasks.values());
        return ResponseEntity.ok(tasksList);
    }

    @PostMapping
    public ResponseEntity<Task> create(@RequestBody Task task) {
        if (task.getTitle().isBlank()) {
            logger.debug("task: {}", task);
            return ResponseEntity.badRequest().build();
        }
        task.setId(newTaskId++);
        tasks.put(task.getId(), task);
        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }
}
