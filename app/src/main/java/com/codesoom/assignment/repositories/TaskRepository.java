package com.codesoom.assignment.repositories;

import com.codesoom.assignment.models.Task;

import java.util.List;

public interface TaskRepository {

    public List<Task> fetchAll();
    public Task fetchOne(Long id);
    public List<Task> createOne(Task task);

}
