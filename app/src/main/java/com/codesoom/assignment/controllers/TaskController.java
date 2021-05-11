package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final Logger logger = LoggerFactory.getLogger(TaskController.class);
    private final Map<Long, Task> tasks = new HashMap<>();
    private Long newTaskId = 1L;

    @GetMapping
    public ResponseEntity<List<Task>> list() {
        final var tasksList = new ArrayList<>(tasks.values());
        return new ResponseEntity<>(tasksList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> get(@PathVariable("id") final Long id) {
        final var task = tasks.get(id);
        if (task == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Task> create(@RequestBody Task task) {
        if (task.getTitle().isBlank()) {
            logger.debug("task: {}", task);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        task.setId(newTaskId++);
        tasks.put(task.getId(), task);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> update(@PathVariable("id") final Long id, @RequestBody final Task newTask) {
        if (id == null || newTask.getTitle().isBlank()) {
            logger.debug("id={}, task={}", id, newTask);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        var oldTask = tasks.get(id);
        if (oldTask == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        oldTask.setTitle(newTask.getTitle());
        return new ResponseEntity<>(oldTask, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") final Long id) {
        final var deletedTask = tasks.remove(id);
        if (deletedTask == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
