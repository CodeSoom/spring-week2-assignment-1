package com.codesoom.assignment.task.repository;

import com.codesoom.assignment.common.exception.ErrorCode;
import com.codesoom.assignment.common.exception.RestApiException;
import com.codesoom.assignment.task.domain.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TaskRepository {
    private static final List<Task> tasks = new ArrayList<>();

    public List<Task> findAll() {
        return tasks;
    }

    public Optional<Task> findById(Long id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst();
    }

    public Optional<Task> findByTitle(String title) {
        return tasks.stream()
                .filter(task -> task.getTitle().equals(title))
                .findFirst();
    }

    public boolean create(Task task) {
        return tasks.add(task);
    }

    public Task update(Task task) {
        Optional<Task> originTask = findById(task.getId());

        if (originTask.isEmpty()) {
            throw new RestApiException(ErrorCode.NOT_FOUND);
        }

        tasks.set(tasks.indexOf(originTask.get()), task);

        return task;
    }

    public boolean delete(Long id) {
        Optional<Task> originTask = findById(id);

        if (originTask.isEmpty()) {
            throw new RestApiException(ErrorCode.NOT_FOUND);
        }

        return tasks.remove(originTask.get());
    }
}
