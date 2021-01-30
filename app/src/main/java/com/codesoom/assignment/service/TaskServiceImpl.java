package com.codesoom.assignment.service;

import com.codesoom.assignment.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public Optional<Task> getTask(Long id) {
        Optional<Task> task = findTask(id);
        return task;
    }

    @Override
    public Task modifyTask(Task task, String title) {
        task.setTitle(title);
        return task;
    }

    @Override
    public void deleteTask(Task task) {
        tasks.remove(task);
    }

    private Optional<Task> findTask(Long id) {
        return tasks.stream()
            .filter(task -> task.getId().equals(id))
            .findFirst();
    }

    private Long generateId() {
        newId += 1;
        return newId;
    }
}
