package com.codesoom.assignment.controllers;

import com.codesoom.assignment.TaskManager;
import com.codesoom.assignment.models.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {
    TaskManager taskManager = new TaskManager();

    @GetMapping("/**")
    public ResponseEntity<List<Task>> getAll() {
        return new ResponseEntity<>(taskManager.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<?> getOne(@PathVariable Long taskId) {
        Task task;

        try {
            task = taskManager.getOne(taskId);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PostMapping("/**")
    public ResponseEntity<Task> create(@RequestBody Task task) {
        String title = task.getTitle();

        taskManager.create(title);

        return new ResponseEntity<>(taskManager.getLast(), HttpStatus.CREATED);
    }

    @PutMapping("/{taskId}")
    @PatchMapping("/{taskId}")
    public ResponseEntity<?> update(@PathVariable Long taskId, @RequestBody Task task) {
        Task updatedTask;

        try {
            String title = task.getTitle();
            taskManager.update(taskId, title);
            updatedTask = taskManager.getOne(taskId);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<?> delete(@PathVariable Long taskId) {
        try {
            taskManager.remove(taskId);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Success Remove Task", HttpStatus.NO_CONTENT);
    }
}

