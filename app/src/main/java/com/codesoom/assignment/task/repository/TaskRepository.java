package com.codesoom.assignment.task.repository;

import com.codesoom.assignment.common.exception.ErrorCode;
import com.codesoom.assignment.common.exception.RestApiException;
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
            throw new RestApiException(ErrorCode.NOT_FOUND);
        }

        tasks.set(tasks.indexOf(originTask), task);

        return task;
    }

    public boolean delete(Long id) {
        Task originTask = findById(id);

        if (originTask == null) {
            throw new RestApiException(ErrorCode.NOT_FOUND);
        }

        return tasks.remove(originTask);
    }
}
