package com.codesoom.assignment.application;

import com.codesoom.assignment.models.Task;
import java.util.List;

public interface ITaskService {

    Task createTask(Task task);

    Task getTask(Long id);

    List<Task> getAllTask();

    Task updateTask(Task task);

    void deleteTask(Long id);
}
