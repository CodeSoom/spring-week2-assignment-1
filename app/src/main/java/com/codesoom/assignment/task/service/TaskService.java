package com.codesoom.assignment.task.service;

import com.codesoom.assignment.task.domain.Task;
import com.codesoom.assignment.task.domain.request.TaskRequestDto;

import java.util.List;

public interface TaskService {
    List<Task> getTasks();

    Task getTaskById(Long id);

    Task createTask(TaskRequestDto taskRequestDto);

    Task updateTask(Long id, TaskRequestDto taskRequestDto);

    void deleteTask(Long id);
}
