package com.codesoom.assignment.service;

import com.codesoom.assignment.domain.Task;

import java.util.Collection;
import java.util.Optional;

public interface TaskService {

    public Collection<Task> getAll();
    public Task getDetails(Long taskId);
    public Task create(Task task);
    public Task updateTask(Long taskId, Task task);
    public void deleteTask(Long taskId);

}
