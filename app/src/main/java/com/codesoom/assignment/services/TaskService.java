package com.codesoom.assignment.services;

import com.codesoom.assignment.exceptions.NotFoundException;
import com.codesoom.assignment.models.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    private List<Task> tasks = new ArrayList<>();
    private Long newId = 0L;

    public List<Task> getTaskList() {
        return tasks;
    }

    public Task getTask(Long id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFoundException(id));
    }

    public Task addTask(Task task) {
        task.checkTitle(task.getTitle());

        task.setId(generateId());
        tasks.add(task);

        return task;
    }

    public Task updateTask(Long id, Task source) {
        source.checkTitle(source.getTitle());

        Task task = getTask(id);
        task.setTitle(source.getTitle());
        return task;
    }

    public boolean deleteTask(Long id) {
        Task task = getTask(id);
        return tasks.remove(task);
    }

    private Long generateId() {
        newId += 1;
        return newId;
    }
}
