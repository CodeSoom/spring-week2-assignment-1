package com.codesoom.assignment.controllers;

import com.codesoom.assignment.exceptions.ResourceNotFoundException;
import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getTaskList() {
        return taskService.getTaskList();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Task getTask(@PathVariable long id) {
        return taskService.getTask(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @PutMapping("/{id}")
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Task updateTask(
            @PathVariable long id,
            @RequestBody Task sourceTask) {
        return taskService.updateTask(id, sourceTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTask(@PathVariable long id) {

        boolean deleted = taskService.deleteTask(id);
        if (!deleted) {
            throw new ResourceNotFoundException(String.format("Task Id = %d has not been found", id));
        }
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
