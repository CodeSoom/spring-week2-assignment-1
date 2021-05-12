package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.nio.charset.StandardCharsets;
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
    private Long newTaskId = 0L;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Task> list() {
        return new ArrayList<>(tasks.values());
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") final Long id) {
        final var task = tasks.get(id);
        if (task == null) {
            throw HttpClientErrorException.create(HttpStatus.NOT_FOUND, "Task Not Found", HttpHeaders.EMPTY, null, StandardCharsets.UTF_8);
        }
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Task> create(@RequestBody Task task) {
        if (task.getTitle().isBlank()) {
            throw HttpClientErrorException.create(HttpStatus.BAD_REQUEST, "Task's title must not be blank", HttpHeaders.EMPTY, null, StandardCharsets.UTF_8);
        }
        newTaskId += 1;
        task.setId(newTaskId);
        tasks.put(task.getId(), task);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> update(@PathVariable("id") final Long id, @RequestBody final Task newTask) {
        if (newTask.getTitle().isBlank()) {
            logger.debug("task={}", newTask.getTitle());
            throw HttpClientErrorException.create(HttpStatus.BAD_REQUEST, "Task's title must not be blank", HttpHeaders.EMPTY, null, StandardCharsets.UTF_8);
        }

        var oldTask = tasks.get(id);
        if (oldTask == null) {
            throw HttpClientErrorException.create(HttpStatus.NOT_FOUND, "Task Not Found", HttpHeaders.EMPTY, null, StandardCharsets.UTF_8);
        }

        oldTask.setTitle(newTask.getTitle());
        return new ResponseEntity<>(oldTask, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") final Long id) {
        final var deletedTask = tasks.remove(id);
        if (deletedTask == null) {
            throw HttpClientErrorException.create(HttpStatus.NOT_FOUND, "Task Not Found", HttpHeaders.EMPTY, null, StandardCharsets.UTF_8);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
