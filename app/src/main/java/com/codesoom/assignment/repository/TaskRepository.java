package com.codesoom.assignment.repository;

import com.codesoom.assignment.models.Task;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class TaskRepository {
    private Long sequence = 1L;
    private final Map<Long, Task> taskMap = new HashMap<>();

    public Optional<Task> findById(Long id) {
        return Optional.ofNullable(taskMap.get(id));
    }

    public List<Task> findAll() {
        return new ArrayList<>(taskMap.values());
    }

    public Task save(Task source) {
        Task target = new Task(sequence, source.getTitle());
        taskMap.put(target.getId(), target);

        incrementSequence();
        return target;
    }

    public void delete(Long id) {
        taskMap.remove(id);
    }

    private void incrementSequence() {
        this.sequence += 1;
    }

}
