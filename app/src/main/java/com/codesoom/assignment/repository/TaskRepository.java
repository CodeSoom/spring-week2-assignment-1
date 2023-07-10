package com.codesoom.assignment.repository;

import com.codesoom.assignment.models.domain.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {

    Task save(Task task);

    List<Task> findAll();

    Optional<Task> findById(Long id);

    void delete(Task task);

    Task update(Task task);
}
