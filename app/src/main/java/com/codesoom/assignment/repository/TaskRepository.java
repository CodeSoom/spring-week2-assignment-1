package com.codesoom.assignment.repository;

import com.codesoom.assignment.domain.Task;

import java.util.Map;

public interface TaskRepository {

    public Task join(Task task);
    public Task findTask(String findId);
    public Map findAll();
    public Task updateTask(String findId, Task task);
    public void deleteTask(String findId);

}
