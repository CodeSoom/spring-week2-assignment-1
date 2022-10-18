package com.codesoom.assignment.task.service;

import com.codesoom.assignment.task.domain.Task;

import java.util.List;

public interface TaskService {
    List<Task> getTasks();

    Task getTaskById(Long id);
}
