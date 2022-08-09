package com.codesoom.assignment.application;

import com.codesoom.assignment.models.Task;
import java.util.List;

public interface ITaskService {

    Task getTask(Long id);

    List<Task> getAllTask();

    Task createTask(Task task);

    Task updateTask(Long oldTaskId, Task task);

    void deleteTask(Long id);
}
