package com.codesoom.assignment.repository;

import com.codesoom.assignment.models.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TaskRepository {
    private List<Task> tasks = new ArrayList<>();

    public List<Task> findAll() {
        return tasks;
    }

    public Optional<Task> findOneById(Long id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst();
    }

    public Task create(String title) {
        Task task = new Task();
        task.setId(generateId());
        task.setTitle(title);
        tasks.add(task);
        return task;
    }

    public Task update(Task task, String title) {
        int taskIndex = tasks.indexOf(task);
        task.setTitle(title);
        tasks.set(taskIndex, task);
        return task;
    }

    public void remove(Task task) {
        tasks.remove(task);
        return;
    }


    private Long generateId() {
        if (tasks.isEmpty()) {
            return 1L;
        }
        return tasks.get(tasks.size() - 1).getId() + 1L;
    }
}
