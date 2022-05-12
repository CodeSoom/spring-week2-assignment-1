package com.codesoom.assignment.repository;

import com.codesoom.assignment.models.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class TaskRepository {
    private AtomicLong sequence = new AtomicLong(1L);
    private final Map<Long, Task> taskMap = new ConcurrentHashMap<>();

    public Optional<Task> findById(Long id) {
        return Optional.ofNullable(taskMap.get(id));
    }

    public List<Task> findAll() {
        return new ArrayList<>(taskMap.values());
    }

    public Task save(Task source) {
        Task newTask = new Task(sequence.getAndIncrement(), source.getTitle());
        taskMap.put(newTask.getId(), newTask);
        return newTask;
    }

    public void delete(Long id) {
        taskMap.remove(id);
    }
}
