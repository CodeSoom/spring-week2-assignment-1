package com.codesoom.assignment.services;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.models.TaskDto;

import java.util.Collection;

public interface TaskService {

    Collection<TaskDto> getAllTasks();

    TaskDto getTask(Long id);

    TaskDto createNewTask(TaskDto dto);

    TaskDto changeTitle(TaskDto dto);

    TaskDto deleteTask(Long id);

    default Task dtoToTask(TaskDto dto) {
        return new Task(dto);
    }

    default TaskDto taskToDto(Task task) {
        return new TaskDto(task);
    }
}
