package com.codesoom.assignment.controllers;

import com.codesoom.assignment.domain.Task;
import com.codesoom.assignment.domain.Tasks;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private Tasks tasks = new Tasks();
    private Long taskId = 0L;

    @GetMapping
    public List<Task> getTasks() {
        return tasks.getTasks();
    }

    @GetMapping("{id}")
    public Task getTask(@PathVariable("id") Long id) {
        return tasks.findTask(id).orElse(null);
    }

    @PostMapping
    public Task addTask(@RequestBody Task task) {
        task.setId(generateId());
        tasks.addTask(task);
        return task;
    }

    @PutMapping("{id}")
    public Task updateTask(@PathVariable("id") Long id, @RequestBody Task source) {
        Task task = tasks.findTask(id).orElse(null);
        if (task == null) {
            return null;
        }
        task.setTitle(source.getTitle());
        return task;
    }

    private Long generateId() {
        return taskId++;
    }
}
