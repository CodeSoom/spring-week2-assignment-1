package com.codesoom.assignment.task.service;

import com.codesoom.assignment.IdGenerator;
import com.codesoom.assignment.common.exception.ErrorCode;
import com.codesoom.assignment.common.exception.RestApiException;
import com.codesoom.assignment.task.domain.Task;
import com.codesoom.assignment.task.domain.request.TaskRequestDto;
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
                .orElseThrow(() -> new RestApiException(ErrorCode.NOT_FOUND));
    }

    @Override
    public Task createTask(TaskRequestDto taskRequestDto) {
        Task task = taskRequestDto.toEntity(IdGenerator.createId());
        boolean result = taskRepository.create(task);

        if (!result) {
            throw new RestApiException(ErrorCode.CONFLICT);
        }

        return task;
    }

    @Override
    public Task updateTask(Long id, TaskRequestDto taskRequestDto) {
        Task task = taskRequestDto.toEntity(id);

        return taskRepository.update(task);
    }

    @Override
    public void deleteTask(Long id) {
        boolean result = taskRepository.delete(id);

        if (!result) {
            throw new RestApiException(ErrorCode.CONFLICT);
        }
    }
}
