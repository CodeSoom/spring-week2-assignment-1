package com.codesoom.assignment.services;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.models.TaskDto;
import com.codesoom.assignment.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private final TaskRepository repository;

    public TaskServiceImpl(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public Collection<TaskDto> getAllTasks() {
        return repository.findAllTasks().stream()
                .map(this::taskToDto)
                .collect(Collectors.toList());
    }

    @Override
    public TaskDto getTask(Long id) {
        Task task = repository.findById(id);
        return taskToDto(task);
    }

    @Override
    public TaskDto createNewTask(TaskDto dto) {
        Task task = repository.addTask(dtoToTask(dto));
        return taskToDto(task);
    }

    @Override
    public TaskDto changeTitle(TaskDto dto) {
        Task task = dtoToTask(dto);
        Task changed = repository.changeTitle(task.getId(), task);
        return taskToDto(changed);
    }

    @Override
    public TaskDto deleteTask(Long id) {
        Task task = repository.deleteById(id);
        return taskToDto(task);
    }
}
