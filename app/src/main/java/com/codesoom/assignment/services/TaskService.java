package com.codesoom.assignment.services;

import com.codesoom.assignment.models.TaskDto;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    List<TaskDto> getAllTasks();

    Optional<TaskDto> getTask(Long id);

    TaskDto createNewTask(TaskDto dto);

    Optional<TaskDto> changeTitle(TaskDto dto);

    Optional<TaskDto> deleteTask(Long id);
}
