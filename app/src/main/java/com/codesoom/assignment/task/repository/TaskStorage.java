package com.codesoom.assignment.task.repository;

import com.codesoom.assignment.task.exception.TaskNotFoundException;
import com.codesoom.assignment.task.model.domain.Task;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class TaskStorage implements TaskRepository {

    private final Set<Task> tasks = new HashSet<>();

    @Override
    public Task findById(final long id) {
        return tasks.stream()
                .filter(task -> task.getId() == id)
                .findFirst()
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    @Override
    public Set<Task> findAll() {
        return tasks;
    }

    @Override
    public Task save(final Task task) {
        tasks.add(task);
        return task;
    }

    @Override
    public Task updateById(final long id, final Task task) {
        Task taskToRemove = findById(id);
        tasks.remove(taskToRemove);

        tasks.add(task);
        return task;
    }

    @Override
    public void deleteById(final long id) {
        Task taskToRemove = findById(id);;
        tasks.remove(taskToRemove);
    }

}
