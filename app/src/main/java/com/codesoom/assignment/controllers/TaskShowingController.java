package com.codesoom.assignment.controllers;

import com.codesoom.assignment.TaskLoadingRepository;
import com.codesoom.assignment.controllers.dtos.TaskResponseDto;
import com.codesoom.assignment.interfaces.DefaultController;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "http://localhost:3000")
public class TaskShowingController implements DefaultController {
    private final TaskLoadingRepository repository;
    private final TaskManipulatingController manipulatingController;

    public TaskShowingController(TaskLoadingRepository repository) {
        this.repository = repository;
        manipulatingController = new TaskManipulatingController(repository);
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
    public TaskManipulatingController manipulator() {
        return manipulatingController;
    }
}

