package com.codesoom.assignment.services;

import com.codesoom.assignment.models.BaseTask;
import com.codesoom.assignment.models.TaskDto;

import java.util.Collection;
import java.util.Optional;

public interface TaskService {

    Collection<BaseTask> getAllTasks();

    Optional<BaseTask> getTask(Long id);

    BaseTask createNewTask(TaskDto dto);

    Optional<BaseTask> changeTitle(TaskDto dto);

    Optional<BaseTask> deleteTask(Long id);
}
