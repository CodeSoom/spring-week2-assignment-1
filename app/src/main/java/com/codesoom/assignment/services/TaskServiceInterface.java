package com.codesoom.assignment.services;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.models.TaskDTO;

import java.util.List;
import java.util.Optional;

public interface TaskServiceInterface {

    List<Task> getAllTask();
    Optional<Task> getTaskById(Long id);
    void createTask(TaskDTO taskDTO);
    Optional<Task> updateTask(Long id , TaskDTO taskDTO);
    Optional<Task> deleteTask(Long id);
}
