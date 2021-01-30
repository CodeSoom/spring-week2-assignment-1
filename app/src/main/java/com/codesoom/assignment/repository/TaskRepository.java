package com.codesoom.assignment.repository;

import com.codesoom.assignment.entity.Task;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class TaskRepository {

    private Long lastId = 0L;
    private final Map<Long, Task> tasks = new LinkedHashMap<>();

    public List<Task> findAll() {
        return new ArrayList<>(tasks.values());
    }

    public Optional<Task> findById(Long id) {
        return Optional.ofNullable(tasks.get(id));
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

    public Long increaseId() {
        lastId += 1;
        return lastId;
    }
}
