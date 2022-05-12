package com.codesoom.assignment.controllers;

import com.codesoom.assignment.TaskRepository;
import com.codesoom.assignment.controllers.dtos.TaskResponseDto;
import com.codesoom.assignment.interfaces.DefaultController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "http://localhost:3000")
public class TaskController implements DefaultController {
    private final TaskRepository repository;
    private final TaskControllerOutput controllerOutput;

    public TaskController(TaskRepository repository) {
        this.repository = repository;
        controllerOutput = new TaskControllerOutput(repository);
    }

    @Override
    @GetMapping
    public List<TaskResponseDto> showAll() {
        return repository.tasksAll().stream()
                .map(TaskResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    @GetMapping("/{id}")
    public TaskResponseDto showBy(@PathVariable Long id) {
        new RequestParamValidation(id, repository).validate();

        return new TaskResponseDto(repository.taskBy(id));
    }

    @Override
    public TaskControllerOutput output() {
        return controllerOutput;
    }
}

