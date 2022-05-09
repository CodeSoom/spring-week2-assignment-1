package com.codesoom.assignment.service;

import com.codesoom.assignment.domain.TaskManager;
import com.codesoom.assignment.domain.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HandleTaskService {

    private TaskManager taskManager = new TaskManager();

    public List<Task> findAll() {
        return taskManager.findAll();
    }

    public Task create(Task task) {
        return taskManager.create(task);
    }

    public Task update(Task task) {
        return taskManager.update(task);
    }

    public void delete(Task id) {
        taskManager.delete(id);
    }
}
