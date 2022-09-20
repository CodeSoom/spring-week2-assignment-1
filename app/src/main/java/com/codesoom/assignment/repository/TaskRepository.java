package com.codesoom.assignment.repository;

import com.codesoom.assignment.models.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public interface TaskRepository {

    public List<Task> findAll();
    public Long generateId();
    public Task taskFindId(Long id);
    public Task createTask(Task task);
    public Task updateTask(Long id, Task task);
    public void deleteTask(Long id);



}
