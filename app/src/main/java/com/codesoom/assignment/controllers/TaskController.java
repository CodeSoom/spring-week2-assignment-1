package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.models.TaskRequest;
import com.codesoom.assignment.models.TasksStorage;
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

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {
    private final TasksStorage tasks = new TasksStorage();

    @GetMapping
    public ResponseEntity<Collection<Task>> readAll() {
        return new ResponseEntity<>(tasks.readAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> read(@PathVariable Long id) {
        Optional<Task> task = tasks.read(id);

        if(task.isPresent()) {
            return new ResponseEntity<>(task.orElseThrow(), HttpStatus.OK);
        }

        return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Task> create(@RequestBody TaskRequest taskRequest) {
        Task task = tasks.create(taskRequest.getTitle());

        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody TaskRequest taskRequest) {
        Optional<Task> task = tasks.update(id, taskRequest.getTitle());

        if(task.isPresent()) {
            return new ResponseEntity<>(task.orElseThrow(), HttpStatus.OK);
        }

        return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        Optional<Task> task = tasks.delete(id);

        if(task.isPresent()) {
            return new ResponseEntity<>(task.orElseThrow(), HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
    }
}
