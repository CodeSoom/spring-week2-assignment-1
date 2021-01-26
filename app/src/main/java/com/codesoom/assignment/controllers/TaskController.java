package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.services.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping("")
    public List<Task> lists() {
        return taskService.readTasks();
    }

    @GetMapping("/{id}")
    public Task list(@PathVariable Long id) {
        return taskService.readTask(id);
    }

    @PostMapping
    public Task create(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        taskService.deleteTask(id);

        return "";
    }

    @PutMapping("/{id}")
    public Task putUpdate(@PathVariable Long id, @RequestBody Task task) {
        return taskService.putOrPatchTask(id, task);
    }

    @PatchMapping("/{id}")
    public Task patchUpdate(@PathVariable Long id, @RequestBody Task task) {
        return taskService.putOrPatchTask(id, task);
    }
}
