package com.codesoom.assignment.repository;

import com.codesoom.assignment.models.Task;
import java.util.List;

public interface TaskRepository {

    Task findById(Long id);

    List<Task> findAll();

    Task save(Task task);

    void delete(Long id);
}
