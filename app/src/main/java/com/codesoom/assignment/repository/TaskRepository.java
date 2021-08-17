package com.codesoom.assignment.repository;

import com.codesoom.assignment.domain.Task;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public interface TaskRepository {

    public Task join(Task task);
    public Optional<Task> findTask(Long taskId);
    public Collection<Task> findAll();
    public Optional<Task> updateTask(Long taskId, Task task);
    public boolean deleteTask(Long taskId);

}
