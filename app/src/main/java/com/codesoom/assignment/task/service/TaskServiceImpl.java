package com.codesoom.assignment.task.service;

import com.codesoom.assignment.task.domain.Task;
import com.codesoom.assignment.task.domain.request.TaskSearchDto;
import com.codesoom.assignment.task.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{
    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> gets() {
        return taskRepository.getTasks();
    }

    @Override
    public Task getById(TaskSearchDto taskSearchDto) {
        return taskRepository.getTaskById(taskSearchDto);
    }
}
