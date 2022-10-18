package com.codesoom.assignment.task.service;

import com.codesoom.assignment.IdGenerator;
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
        Task task = taskRepository.findById(id);

        if (task == null) {
            // Not Found 에러
        }

        return task;
    }

    @Override
    public Task createTask(TaskRequestDto taskRequestDto) {
        Task task = taskRequestDto.toEntity(IdGenerator.createId());
        boolean result = taskRepository.create(task);

        if (!result) {
            // 실패 에러
        }

        return task;
    }

    @Override
    public Task updateTask(Long id, TaskRequestDto taskRequestDto) {
        Task task = taskRequestDto.toEntity(id);

        return taskRepository.update(task);
    }
}
