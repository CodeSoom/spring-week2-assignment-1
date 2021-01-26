package com.codesoom.assignment.service;

import com.codesoom.assignment.entity.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class TaskService {
    private List<Task> tasks = new ArrayList<>();
    private Long lastId = 0L;

    public List<Task> getTasks() {
        return tasks;
    }

    public ResponseEntity<Task> getTask(Long id) {
        Task task = findTask(id);
        if (Objects.isNull(task)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }

    public ResponseEntity<Task> addTask(Task task) {
        task.setId(increaseId());
        tasks.add(task);
        return ResponseEntity.created(URI.create("/tasks")).body(task);
    }

    public ResponseEntity<Task> updateTask(Long id, Task inputTask) {
        Task task = findTask(id);
        if (Objects.isNull(task)) {
            return ResponseEntity.notFound().build();
        }
        task.setTitle(inputTask.getTitle());
        return ResponseEntity.ok(task);
    }

    public ResponseEntity<Task> deleteTask(Long id) {
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
