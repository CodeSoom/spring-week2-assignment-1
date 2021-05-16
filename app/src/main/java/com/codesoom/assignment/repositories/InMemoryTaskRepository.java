package com.codesoom.assignment.repositories;

import com.codesoom.assignment.models.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryTaskRepository implements TaskRepository {

    private final List<Task> tasks;

    public InMemoryTaskRepository() {
        this.tasks = new ArrayList<>();
    }

    @Override
    public List<Task> fetchAll() {
        return tasks;
    }

    @Override
    public Optional<Task> fetchOne(Long id) {
        return tasks.stream()
                .filter((Task task) -> task.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Task> createOne(Task task) {
        tasks.add(task);
        return tasks;
    }

    @Override
    public void deleteOne(Task task) {
        tasks.remove(task);
    }
}
