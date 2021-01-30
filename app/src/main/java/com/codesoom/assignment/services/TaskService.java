package com.codesoom.assignment.services;

import com.codesoom.assignment.exceptions.ResourceNotFoundException;
import com.codesoom.assignment.models.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    private final List<Task> tasks = new ArrayList<>();
    private int taskId = 0;

    public List<Task> getTaskList() {
        return tasks;
    }

    public Task getTask(long id) {
        return findTaskById(id);
    }

    public Task createTask(Task task) {
        task.setId(generateId(taskId));
        tasks.add(task);
        return task;
    }

    public Task updateTask(long id, Task sourceTask) {
        Task task = findTaskById(id);
        task.setTitle(sourceTask.getTitle());
        return task;
    }

    public boolean deleteTask(long id) {
        Task task = findTaskById(id);
        return tasks.remove(task);
    }

    private Task findTaskById(long id) {
        return tasks.stream()
                .filter(i -> i.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Not Found with Id " + id));
    }

    private long generateId(int id) {
        taskId = id + 1;
        return taskId;
    }
}
