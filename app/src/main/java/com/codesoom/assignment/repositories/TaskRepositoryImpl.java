package com.codesoom.assignment.repositories;

import com.codesoom.assignment.models.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskRepositoryImpl implements TaskRepository {

    private final List<Task> tasks;

    public TaskRepositoryImpl() {
        this.tasks = new ArrayList<>();
    }

    @Override
    public List<Task> fetchAll() {
        return tasks;
    }

    @Override
    public Task fetchOne(long id) {
        return tasks.stream()
                .filter((Task task) -> task.getId() == id)
                .findAny()
                .orElse(null);
    }

    @Override
    public List<Task> createOne(Task task) {
        tasks.add(task);
        return tasks;
    }
}
