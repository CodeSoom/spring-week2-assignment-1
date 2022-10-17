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
        final Task task = repository.findById(id);
        if (task == null) {
            return null;
        }

        return taskToDto(task);
    }

    @Override
    public TaskDto createNewTask(TaskDto dto) {
        final Task task = repository.addTask(dtoToTask(dto));
        return taskToDto(task);
    }

    @Override
    public TaskDto changeTitle(TaskDto dto) {
        final Task changed = repository.changeTitle(dto.getId(), dto.getTitle());
        if (changed == null) {
            return null;
        }

        return taskToDto(changed);
    }

    @Override
    public TaskDto deleteTask(Long id) {
        final Task task = repository.deleteById(id);
        if (task == null) {
            return null;
        }
        return taskToDto(task);
    }
}
