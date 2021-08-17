package com.codesoom.assignment.controllers;

import com.codesoom.assignment.TaskManager;
import com.codesoom.assignment.models.Task;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    TaskManager taskManager = new TaskManager();

    @GetMapping("/**")
    public List<Task> getAll() {
        return taskManager.getAll();
    }

    @GetMapping("/{taskId}")
    public Task getOne(@PathVariable String taskId) {
        return taskManager.getOne(Long.parseLong(taskId));
    }

    @PostMapping("/**")
    public Task create(@RequestBody Task task) {
        String title = task.getTitle();
        taskManager.create(title);
        return taskManager.getLast();
    }

    @PutMapping("/{taskId}")
    @PatchMapping("/{taskId}")
    public Task update(@PathVariable String taskId, @RequestBody Task task) {
        String title = task.getTitle();
        Long id = Long.parseLong(taskId);
        taskManager.update(id, title);
        return taskManager.getOne(id);
    }

    @DeleteMapping("/{taskId}")
    public String delete(@PathVariable String taskId) {
        Long id = Long.parseLong(taskId);
        taskManager.remove(id);
        return "Success Remove Task";
    }
}

