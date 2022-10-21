package com.codesoom.assignment.task.service;

import com.codesoom.assignment.exception.DataProcessingFailException;
import com.codesoom.assignment.exception.TaskNotFoundException;
import com.codesoom.assignment.task.controller.dto.request.TaskRequestDto;
import com.codesoom.assignment.task.domain.IdGenerator;
import com.codesoom.assignment.task.domain.Task;
import com.codesoom.assignment.task.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(TaskNotFoundException::new);
    }

    @Override
    public Task createTask(TaskRequestDto taskRequestDto) {
        Task task = taskRequestDto.toEntity(IdGenerator.createId());
        boolean isSuccessCreated = taskRepository.create(task);

        if (!isSuccessCreated) {
            throw new DataProcessingFailException();
        }

        return task;
    }

    @Override
    public Task updateTask(Long id, TaskRequestDto taskRequestDto) {
        Task task = taskRequestDto.toEntity(id);
        Task originTask = taskRepository.findById(id)
                .orElseThrow(TaskNotFoundException::new);

        return taskRepository.update(originTask, task);
    }

    @Override
    public void deleteTask(Long id) {
        Task originTask = taskRepository.findById(id)
                .orElseThrow(TaskNotFoundException::new);

        boolean isSuccessDeleted = taskRepository.delete(originTask);

        if (!isSuccessDeleted) {
            throw new DataProcessingFailException();
        }
    }
}
