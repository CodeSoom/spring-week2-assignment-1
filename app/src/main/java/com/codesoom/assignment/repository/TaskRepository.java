package com.codesoom.assignment.repository;

import com.codesoom.assignment.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    Task save(Task task);

    List<Task> findAll();

    Optional<Task> findById(Long id);

    Task update(Task oldTask);

    Task delete(Long id);
}
