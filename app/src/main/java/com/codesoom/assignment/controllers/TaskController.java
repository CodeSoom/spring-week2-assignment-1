package com.codesoom.assignment.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codesoom.assignment.model.Task;
import com.codesoom.assignment.service.TaskService;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> list() {
        return taskService.getTaskList();
    }

    @PostMapping
    public ResponseEntity<Task> create(@RequestBody Task task) {
        if (task.getTitle().isBlank()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Task createdTask = this.taskService.createTask(task);

        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable("id") Long id) {
        Optional<Task> task = taskService.getTask(id);
        return ResponseEntity.of(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> modifyTask(@PathVariable("id") Long id, @RequestBody Task source) {
        Optional<Task> entity = taskService.getTask(id);

        if (entity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        taskService.modifyTask(entity.get(), source.getTitle());
        return ResponseEntity.of(entity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable("id") Long id) {
        Optional<Task> task = taskService.getTask(id);

        if (task.isEmpty()) {
           return ResponseEntity.notFound().build();
        }

        taskService.deleteTask(task.get());

        return ResponseEntity.noContent().build();
    }

}
