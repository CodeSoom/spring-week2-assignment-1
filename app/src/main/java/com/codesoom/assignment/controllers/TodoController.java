package com.codesoom.assignment.controllers;

import com.codesoom.assignment.entity.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class TodoController {
    private List<Task> tasks = new ArrayList<>();
    private Long lastId = 0L;
    private static final String TASKS_URL = "/tasks";

    @GetMapping(TASKS_URL)
    public List<Task> getTaskList() {
        return tasks;
    }

    @GetMapping(TASKS_URL+"/{id}")
    public ResponseEntity<Task> getTask(@PathVariable Long id) {
        Task task = findTask(id);
        if (Objects.isNull(task)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }

    @PostMapping(TASKS_URL)
    public ResponseEntity<Task> postTask(@RequestBody Task task) {
        task.setId(increaseId());
        tasks.add(task);
        return ResponseEntity.created(URI.create("/tasks")).body(task);
    }

    @PutMapping(TASKS_URL+"/{id}")
    public ResponseEntity<Task> putTask(@PathVariable Long id, @RequestBody Task inputTask) {
        Task task = findTask(id);
        if (Objects.isNull(task)) {
            return ResponseEntity.notFound().build();
        }

        task.setTitle(inputTask.getTitle());
        return ResponseEntity.ok(task);
    }

    @PatchMapping(TASKS_URL+"/{id}")
    public ResponseEntity<Task> patchTask(@PathVariable Long id, @RequestBody Task inputTask) {
        Task task = findTask(id);
        if (Objects.isNull(task)) {
            return ResponseEntity.notFound().build();
        }
        task.setTitle(inputTask.getTitle());
        return ResponseEntity.ok(task);
    }

    @DeleteMapping(TASKS_URL+"/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable Long id) {
        Task removeTask = findTask(id);
        if (tasks.contains(removeTask)) {
            tasks.remove(removeTask);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


    private Task findTask(Long id) {
        return tasks.stream().
                filter(task -> task.getId().equals(id)).
                findFirst().
                orElse(null);
    }

    private Long increaseId() {
        lastId += 1;
        return lastId;
    }
}
