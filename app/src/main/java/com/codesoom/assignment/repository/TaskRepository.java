package com.codesoom.assignment.repository;

import com.codesoom.assignment.domain.Task;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class TaskRepository {

    private long sequenceId = 0;

    private final Map<Long, Task> tasks = new HashMap<>();

    public void save(final Task task) {
        Long taskId = generateId();
        task.setId(taskId);
        tasks.put(taskId, task);
    }

    private Long generateId() {
        synchronized (this) {
            sequenceId = sequenceId + 1;
            return sequenceId;
        }
    }

    public Map<Long, Task> findAll() {
        return tasks;
    }

    public Task findById(final Long id) {
        return tasks.get(id);
    }

    public void deleteById(final Long id) {
        tasks.remove(id);
    }
}
