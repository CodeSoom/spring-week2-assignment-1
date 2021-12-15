package com.codesoom.assignment.services;

import com.codesoom.assignment.models.Task;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskService {
    private List<Task> tasks = new ArrayList<>();
    private Long newId = 0L;

    public List<Task> getTasks() {
        return tasks;
    }

    public Optional<Task> getTask(Long id) {
        Optional<Task> entity = tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst();
        return entity;
    }

    public Task createTask(Task task) {
        task.setId(generateId());
        tasks.add(task);
        return task;
    }

    public ResponseEntity<Task> updateTask(Long id, Task source) {
        Optional<Task> entity = getTask(id);

        if(entity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Task task = entity.get();
        task.setTitle(source.getTitle());

        return ResponseEntity.of(entity);
    }

    public ResponseEntity<Task> deleteTask(Long id) {
        Optional<Task> entity = getTask(id);

        if(entity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Task task = entity.get();
        tasks.remove(task);

        return ResponseEntity.noContent().build();
    }

    private synchronized Long generateId() {
        newId += 1;
        return newId;
    }
}
