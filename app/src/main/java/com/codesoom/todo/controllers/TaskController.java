package com.codesoom.todo.controllers;

import com.codesoom.todo.domain.Task;
import com.codesoom.todo.services.TaskService;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskController {
    TaskService taskService = new TaskService();

    // TODO: fill skeleton mapper
    @GetMapping("/tasks")
    public List<Task> showTasks() {
        return taskService.showTasks();
    }

    @GetMapping("/tasks/{id}")
    public Task showTask(@PathVariable("id") Long taskId) {
        return taskService.showTask(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));
    }

    @PostMapping("/tasks")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Task createTask(@RequestBody Task task) {
        return taskService.addTask(task);
    }

    @PutMapping("/tasks/{id}")
    @PatchMapping("/tasks/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Task editTitle(@PathVariable("id") Long taskId, @RequestBody @NotNull Task task) {
        task.setId(taskId);
        return taskService.editTaskTitle(task)
                .orElseThrow(() -> new TaskNotFoundException(taskId));
    }

    @DeleteMapping("/tasks/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable("id") Long taskId) {
        taskService.removeTask(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));
    }


}
