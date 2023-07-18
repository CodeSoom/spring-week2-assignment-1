package com.codesoom.assignment.repository;

import com.codesoom.assignment.models.domain.Task;

import java.util.List;

public interface TaskRepository {

    Task save(Task task);

    List<Task> findAll();

    Task findById(Long id);

    void delete(Task task);

    Task update(Task task);
}
