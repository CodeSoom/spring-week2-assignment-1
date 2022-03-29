package com.codesoom.assignment.services;

import com.codesoom.assignment.domains.Task;
import com.codesoom.assignment.domains.TaskDto;

import java.util.List;

public interface TaskService {

    List<Task> getTasks();

    Task addTask(TaskDto taskDto);

    Task findTaskById(Long id);

    Task updateTaskById(Long id, TaskDto taskDto);

    void deleteTaskById(Long id);

}
