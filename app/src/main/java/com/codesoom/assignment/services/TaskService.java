package com.codesoom.assignment.services;

import com.codesoom.assignment.models.Task;

import java.util.List;

public interface TaskService {

    List<Task> getTasks();

    Task addTask(Task task);

    Task getTask(Long id);

    Task updateTask(Long id, Task newTask);

    void deleteTask(Long id);
}
