package com.codesoom.assignment.services;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.models.TaskDTO;

import java.util.List;
import java.util.Optional;

public interface TaskServiceInterface {

    List<Task> getAllTask();
    Task getTaskById(Long id);

    /**
     * 과제를 새로 추가합니다
     * @param taskDTO
     * @return null
     */
    void createTask(TaskDTO taskDTO);

    Task updateTask(Long id , TaskDTO taskDTO);
    Task deleteTask(Long id);
}
