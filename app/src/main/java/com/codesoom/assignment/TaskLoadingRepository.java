package com.codesoom.assignment;

import com.codesoom.assignment.interfaces.LoadingRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class TaskLoadingRepository implements LoadingRepository {
    private final Map<Long, DefaultTask> tasks = new HashMap<>();
    private final TaskManipulatingRepository repositoryOutput = new TaskManipulatingRepository(this, tasks);

    public TaskLoadingRepository() {
    }

    @Override
    public List<DefaultTask> tasksAll() {
        return tasks.values().stream()
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public DefaultTask taskBy(final Long id) {
        return tasks.values().stream()
                .filter(task -> Objects.equals(task.id(), id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("taskId(" + id + ")에 해당하는 Task를 Repository에서 찾을 수 없습니다"));
    }

    @Override
    public boolean notPresent(final Long id) {
        return tasks.values().stream()
                .noneMatch(task -> Objects.equals(task.id(), id));
    }

    @Override
    public TaskManipulatingRepository manipulator() {
        return repositoryOutput;
    }
}
