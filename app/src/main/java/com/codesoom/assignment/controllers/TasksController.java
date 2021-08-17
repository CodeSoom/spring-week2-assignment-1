package com.codesoom.assignment.controllers;

import com.codesoom.assignment.TaskManager;
import com.codesoom.assignment.models.Task;
import java.util.Collection;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TasksController {

    private final TaskManager taskManager = TaskManager.getInstance();

    @GetMapping
    public Collection<Task> get() {
        return taskManager.getAllTasks();
    }

    @PostMapping
    public Task post(@RequestBody Task task) {
        return taskManager.createTask(task);
    }
}
