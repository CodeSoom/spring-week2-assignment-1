package com.codesoom.assignment.repository;

import com.codesoom.assignment.domain.Task;

import java.util.Collection;

public interface TaskRepository {

    public Task join(Task task);
    public Task findTask(Long taskId);
    public Collection<Task> findAll();
    public Task updateTask(Long taskId, Task task);
    public void deleteTask(Long taskId);

}
