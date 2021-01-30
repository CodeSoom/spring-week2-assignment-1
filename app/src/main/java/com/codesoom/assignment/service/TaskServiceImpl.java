package com.codesoom.assignment.service;

import com.codesoom.assignment.TaskNotFoundException;
import com.codesoom.assignment.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private List<Task> tasks = new ArrayList<>();
    private Long newId = 0L;

    @Override
    public List<Task> getTaskList() {
        return tasks;
    }

    @Override
    public Task createTask(Task task) {
        task.setId(generateId());
        tasks.add(task);
        return task;
    }

    @Override
    public Task getTask(Long id) {
        Task task = findTask(id);
        return task;
    }

    @Override
    public Task modifyTask(Task source, Long id) {
        Task task = findTask(id);
        task.setTitle(source.getTitle());
        return task;
    }

    @Override
    public void deleteTask(Long id) {
        Task task = findTask(id);
        tasks.remove(task);
    }

    private Task findTask(Long id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    private Long generateId() {
        newId += 1;
        return newId;
    }
}
