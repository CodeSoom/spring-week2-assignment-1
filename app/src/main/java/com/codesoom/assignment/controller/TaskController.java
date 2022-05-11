package com.codesoom.assignment.controller;

import com.codesoom.assignment.domain.Task;
import com.codesoom.assignment.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {

    TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<Task>> findAll() {
        return new ResponseEntity<>(taskService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Task> create(@RequestBody Task task) {
        return new ResponseEntity<>(taskService.create(task), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Task> findOne(@PathVariable Long id) {
        return new ResponseEntity<>(taskService.findOne(id), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @PutMapping("/{id}")
    public ResponseEntity<Task> update(@RequestBody Task task) {
        return new ResponseEntity<>(taskService.update(task), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        taskService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
