package com.codesoom.assignment.application;

import com.codesoom.assignment.models.Task;

import java.util.List;

public interface TaskRepository {

    public List<Task> fetchAll();
    public Task fetchOne(Long id);
    public Task createOne(Task task);
    public Task updateOne(Long id, Task task);
    public void deleteOne(Long id);

    public Long generateId();
}
