package com.codesoom.assignment.repositories;

import com.codesoom.assignment.models.BaseTask;
import com.codesoom.assignment.models.TaskDto;

import java.util.Collection;
import java.util.Optional;

public interface TaskRepository {

    Collection<BaseTask> findAllTasks();

    Optional<BaseTask> findById(Long id);

    BaseTask addTask(TaskDto dto);

    Optional<BaseTask> changeTitle(BaseTask task);

    Optional<BaseTask> deleteById(Long id);
}
