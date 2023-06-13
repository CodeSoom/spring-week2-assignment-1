package com.codesoom.assignment.services;


import com.codesoom.assignment.models.Task;

import java.util.List;

public interface TaskService
{

    Task createTask(Task task);

    Task getTaskById(Long id);

    List<Task> getTaskList();

    Task updateTask(Task task);

    void deleteTask(Long id);



}
