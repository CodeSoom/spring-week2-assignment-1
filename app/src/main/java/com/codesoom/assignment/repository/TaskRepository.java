package com.codesoom.assignment.repository;

import com.codesoom.assignment.entity.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TaskRepository {
    private Map<Long, Task> tasks = new HashMap<>();

    public List<Task> findAll() {
        return new ArrayList<>(tasks.values());
    }

    public Task findById(Long id) {
        return tasks.get(id);
    }

    public ResponseEntity<Task> save(Task task) {
        if (tasks.containsValue(task)) {
            return ResponseEntity.ok(task);
        }
        tasks.put(task.getId(), task);
        return ResponseEntity.created(URI.create("/tasks")).body(task);
    }

    public boolean existsById(Long id) {
        return tasks.containsKey(id);
    }

    public void deleteById(Long id) {
        tasks.remove(id);
    }
}
