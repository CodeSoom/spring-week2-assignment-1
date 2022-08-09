package com.codesoom.assignment.services;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.models.TaskDTO;

import java.util.List;

public interface TaskServiceInterface {

    List<Task> getAllTask();
    Task getTaskById(Long id);
    void createTask(TaskDTO taskDTO);
    void updateTask(Long id , TaskDTO taskDTO);
    void deleteTask(Long id);
}
