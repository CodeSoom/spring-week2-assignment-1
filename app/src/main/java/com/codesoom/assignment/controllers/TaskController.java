package com.codesoom.assignment.controllers;

import com.codesoom.assignment.TaskRepository;
import com.codesoom.assignment.models.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskRepository repository = new TaskRepository();
    private Long newId = 0L;

    @GetMapping
    public List<Task> list() {
        return repository.getTasks();
    }

    @GetMapping(path="/{taskId}")
    public ResponseEntity<Task> getTask(@PathVariable Long taskId) {
        return repository.getTaskById(taskId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task create(@RequestBody Task task) {
        task.setId(generateId());
        repository.addTask(task);

        return task;
    }

    @PutMapping(path="/{taskId}")
    @PatchMapping(path="/{taskId}")
    public void edit(@PathVariable Long taskId, @RequestParam String title) {
        
    }

    private Long generateId() {
        newId += 1;
        return newId;
    }
}
