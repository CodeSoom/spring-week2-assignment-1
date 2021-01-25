package com.codesoom.assignment.controllers;

import com.codesoom.assignment.exceptions.ResourceNotFoundException;
import com.codesoom.assignment.models.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final List<Task> tasks = new ArrayList<>();

    @GetMapping
    public List<Task> getTaskList() {
        return tasks;
    }

    @GetMapping("/{id}")
    public ResponseEntity getTask(@PathVariable long id) {
        Task task = findTaskById(id);
        return ResponseEntity
                .ok(task);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity createTask(@RequestBody Task task) {
        Task newTask = new Task();
        newTask.generateId(tasks.size());
        newTask.setTitle(task.getTitle());

        tasks.add(newTask);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(newTask);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity updateTask(
            @PathVariable long id,
            @RequestBody Task task) {

        Task sourceTask = findTaskById(id);
        sourceTask.setTitle(task.getTitle());

        return ResponseEntity.ok(sourceTask);
    }

    @PatchMapping ("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity updateTaskDetail(
            @PathVariable long id,
            @RequestBody Task task) {

        Task sourceTask = findTaskById(id);
        sourceTask.setTitle(task.getTitle());

        return ResponseEntity.ok(sourceTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTask(@PathVariable long id) {

        Task task = findTaskById(id);
        tasks.remove(task);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    private Task findTaskById(long id) {
        return tasks.stream()
                .filter(i -> i.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Not Found"));
    }
}
