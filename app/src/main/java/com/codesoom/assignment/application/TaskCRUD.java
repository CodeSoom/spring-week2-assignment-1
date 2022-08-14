package com.codesoom.assignment.application;

import com.codesoom.assignment.models.Task;
import java.util.List;

public interface TaskCRUD {

    Task getTask(Long id);

    List<Task> getAllTask();

    Task createTask(String title);

    Task updateTask(Long oldTaskId, Task task);

    void deleteTask(Long id);
}
