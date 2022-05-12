package com.codesoom.assignment;

import com.codesoom.assignment.interfaces.DefaultRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class TaskRepository implements DefaultRepository {
    private final Map<Long, Task> tasks = new HashMap<>();
    private final TaskRepositoryOutput repositoryOutput = new TaskRepositoryOutput(tasks);

    public TaskRepository() {
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
    public boolean notPresent(Long id) {
        return tasks.values().stream()
                .noneMatch(task -> Objects.equals(task.id(), id));
    }

    @Override
    public TaskRepositoryOutput output() {
        return repositoryOutput;
    }
}
