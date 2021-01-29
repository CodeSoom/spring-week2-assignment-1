package com.codesoom.assignment.repository;

import com.codesoom.assignment.entity.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TaskRepository {
    private final Map<Long, Task> tasks = new LinkedHashMap<>();

    public List<Task> findAll() {
        return new ArrayList<>(tasks.values());
    }

    public Task findById(Long id) {
        return tasks.get(id);
    }

    public Task save(Task task) {
        tasks.put(task.getId(), task);
        return task;
    }

    public boolean existsById(Long id) {
        return tasks.containsKey(id);
    }

    public void deleteById(Long id) {
        tasks.remove(id);
    }
}
