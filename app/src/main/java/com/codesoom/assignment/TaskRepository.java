package com.codesoom.assignment;

import com.codesoom.assignment.interfaces.DefaultRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class TaskRepository implements DefaultRepository {
    static private Long sequence = 1L;
    static private Map<Long, Task> tasks;

    TaskRepository() {
        tasks = new HashMap<>();
    }

    @Override
    public List<Task> tasksAll() {
        return tasks.values().stream()
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public Task taskBy(Long id) {
        return tasks.values().stream()
                .filter(task -> Objects.equals(task.id(), id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("taskId(" + id + ")에 해당하는 Task를 Repository에서 찾을 수 없습니다"));
    }

    @Override
    public RepositoryOutput output() {
        return null;
    }
}
