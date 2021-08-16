package com.codesoom.assignment.service;

import com.codesoom.assignment.domain.Task;
import com.codesoom.assignment.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TaskServiceImpl implements TaskService {

    @Autowired
    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Map getAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task getDetail(String findId) {
        return taskRepository.findTask(findId);
    }

    @Override
    public Task create(Task task) {
        return taskRepository.join(task);
    }

    @Override
    public Task updateTask(String findId, Task task) {
       return taskRepository.updateTask(findId, task);
    }

    @Override
    public void deleteTask(String findId) {
        taskRepository.deleteTask(findId);
    }


}

