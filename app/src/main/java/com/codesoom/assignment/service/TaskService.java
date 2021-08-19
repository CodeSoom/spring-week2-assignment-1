package com.codesoom.assignment.service;

import com.codesoom.assignment.domain.Task;
import com.codesoom.assignment.model.TaskResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private final Map<Long, Task> tasks;

    public TaskService() {
        this.tasks = new ConcurrentHashMap<>();
    }

    public List<TaskResponse> taskList() {
        return tasks.values().stream()
                .map(task -> new TaskResponse(task.id(), task.title()))
                .collect(Collectors.toList());
    }
}
