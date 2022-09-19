package com.codesoom.assignment.repository;

import com.codesoom.assignment.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    Task save(Task task);

    List<Task> findAll();

    Optional<Task> findById(Long id);

    Optional<Task> update(Long id, Task oldTask);

    Optional<Task> delete(Long id);
}
