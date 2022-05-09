package com.codesoom.assignment.repository;

import com.codesoom.assignment.models.Task;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class TaskRepository {
    private final AtomicLong sequence = new AtomicLong(1L);
    private final Map<Long, Task> taskMap = new ConcurrentHashMap<>();

    public Optional<Task> findById(Long id) {
        return Optional.ofNullable(taskMap.get(id));
    }

    public List<Task> findAll() {
        return new ArrayList<>(taskMap.values());
    }

    public Task save(Task source) {
        Task target = new Task(sequence.get(), source.getTitle());
        taskMap.put(target.getId(), target);

        incrementSequence();
        return target;
    }

    public void delete(Long id) {
        taskMap.remove(id);
    }

    private void incrementSequence() {
        Long next = this.sequence.get() + 1;
        sequence.set(next);
    }

}
