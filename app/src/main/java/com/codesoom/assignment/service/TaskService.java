package com.codesoom.assignment.service;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    // Task all
    public List<Task> getTasks() {
        return taskRepository.getTasks();
    }

    // Task one
    public String findOneTask(Long id) {
        return taskRepository.findOneTask(id);
    }

    // Task Insert
    public Task addTask(Task task) {
        return taskRepository.addTask(task);
    }

    // Task Update
    public String taskModify(Long id, Task task) {
        return taskRepository.taskModify(id, task);
    }

    // Task Delete
    public String deleteTask(Long id) {
        return taskRepository.taskDelete(id);
    }
}
