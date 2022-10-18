package com.codesoom.assignment.task.repository;

import com.codesoom.assignment.task.domain.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskRepository {
    private static final List<Task> tasks = new ArrayList<>();

    public List<Task> findAll() {
        return tasks;
    }

    public Task findById(Long id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Task findByTitle(String title) {
        return tasks.stream()
                .filter(task -> task.getTitle().equals(title))
                .findFirst()
                .orElse(null);
    }

    public boolean create(Task task) {
        return tasks.add(task);
    }

    public Task update(Task task) {
        Task originTask = findById(task.getId());

        if (originTask == null) {
            // Not Found 에러
        }

        tasks.set(tasks.indexOf(originTask), task);

        return task;
    }
}
