package com.codesoom.assignment.repositories;

import com.codesoom.assignment.models.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    List<Task> findAll();

    Task save(Task task);

    Optional<Task> findOne(Long id);

    void delete(Long id);

    void removeAll();
}
