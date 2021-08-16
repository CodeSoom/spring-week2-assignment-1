package com.codesoom.assignment.repository;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.models.TaskIdGenerator;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TaskRepository {
    private Map<Long, Task> taskMap = new ConcurrentHashMap<>();

    public Collection<Task> getTaskList() {
        return taskMap.values();
    }

    public Task getTaskById(Long id) {
        return taskMap.get(id);
    }

    public Task createTask(String title) {
        Task newTask = new Task(TaskIdGenerator.getSequence());
        newTask.setTitle(title);
        taskMap.put(newTask.getId(), newTask);
        return newTask;
    }
}
