package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {
    private Long taskId = 0L;
    private List<Task> tasks = new ArrayList<>();

    @GetMapping
    public List<Task> list() {
        return tasks;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Task create(@RequestBody Task task) {
        task.setId(generateId());
        tasks.add(task);
        return task;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> read(@PathVariable Long id) {
        Optional<Task> task = getTask(id);
        if (task.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(task.get());
    }

    @RequestMapping(path = "/{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<Task> update(@PathVariable Long id, @RequestBody Task sourceTask) {
        Optional<Task> task = getTask(id);
        if (task.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        task.get().setTitle(sourceTask.getTitle());
        return ResponseEntity.ok(task.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        Optional<Task> task = getTask(id);
        if (task.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        tasks.remove(task.get());
        return ResponseEntity.noContent().build();
    }

    private synchronized Long generateId() {
        taskId += 1;
        return taskId;
    }

    private Optional<Task> getTask(Long id) {
        return tasks.stream().filter(item -> item.getId().equals(id))
                .findFirst();
    }
}
