package com.codesoom.assignment.repository;

import com.codesoom.assignment.models.Task;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class TaskRepository {
    private static final Map<Long, Task> tasks = new HashMap<>();
    private static Long sequence = 1L;

    public TaskRepository() {
    }

    public List<Task> findAll() {
        return new ArrayList<>(tasks.values());
    }

    public Optional<Task> findById(Long id) {
        return Optional.ofNullable(tasks.get(id));
    }

    public Task save(Task task) {
        task.setId(sequence);
        tasks.put(task.getId(), task);

        sequence += 1;
        return task;
    }

    public Task update(Long id, Task newTask) {
        newTask.setId(id);
        tasks.put(newTask.getId(), newTask);
        return newTask;
    }

    public void delete(Long id) {
        tasks.remove(id);
    }
}
