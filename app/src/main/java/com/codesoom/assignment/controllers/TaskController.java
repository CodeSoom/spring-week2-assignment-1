package com.codesoom.assignment.controllers;

import com.codesoom.assignment.TaskRepository;
import com.codesoom.assignment.controllers.dtos.TaskResponseDto;
import com.codesoom.assignment.interfaces.ControllerOutput;
import com.codesoom.assignment.interfaces.DefaultController;

import java.util.List;
import java.util.stream.Collectors;

public class TaskController implements DefaultController {
    private final TaskRepository repository;

    TaskController(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TaskResponseDto> showAll() {
        return repository.tasksAll().stream()
                .map(TaskResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public TaskResponseDto showBy(Long id) {
        return new TaskResponseDto(repository.taskBy(id));
    }

    @Override
    public ControllerOutput output() {
        return null;
    }
}
