package com.codesoom.assignment.task.service;

import com.codesoom.assignment.task.domain.Task;
import com.codesoom.assignment.task.domain.request.TaskSearchDto;

import java.util.List;

public interface TaskService {
    List<Task> gets();

    Task getById(TaskSearchDto taskSearchDto);
}
