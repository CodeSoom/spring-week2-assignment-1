package com.codesoom.assignment.repositories;

import com.codesoom.assignment.models.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {

    public List<Task> fetchAll();
    public Optional<Task> fetchOne(Long id);
    public List<Task> createOne(Task task);

}
