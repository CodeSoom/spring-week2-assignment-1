package com.codesoom.assignment.service;

import com.codesoom.assignment.domain.Task;

import java.util.Map;

public interface TaskService {

    public Map getAll();
    public Task getDetail(String findId);
    public Task create(Task task);
    public Task updateTask(String findId, Task task);
    public void deleteTask(String findId);

}
