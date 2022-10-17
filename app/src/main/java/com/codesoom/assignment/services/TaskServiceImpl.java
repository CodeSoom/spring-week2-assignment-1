package com.codesoom.assignment.services;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.models.TaskDto;
import com.codesoom.assignment.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;

    @Autowired
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
    public Optional<TaskDto> getTask(Long id) {
        final Optional<Task> optionalTask = repository.findById(id);
        if (optionalTask.isEmpty()) {
            return Optional.empty();
        }

        return optionalTask.map(this::taskToDto);
    }

    @Override
    public TaskDto createNewTask(TaskDto dto) {
        final Task task = repository.addTask(dtoToTask(dto));
        return taskToDto(task);
    }

    @Override
    public Optional<TaskDto> changeTitle(TaskDto dto) {
        final Optional<Task> optionalTask = repository.changeTitle(dto.getId(), dto.getTitle());
        if (optionalTask.isEmpty()) {
            return Optional.empty();
        }

        return optionalTask.map(this::taskToDto);
    }

    @Override
    public Optional<TaskDto> deleteTask(Long id) {
        final Optional<Task> optionalTask = repository.deleteById(id);
        if (optionalTask.isEmpty()) {
            return Optional.empty();
        }

        return optionalTask.map(this::taskToDto);
    }
}
