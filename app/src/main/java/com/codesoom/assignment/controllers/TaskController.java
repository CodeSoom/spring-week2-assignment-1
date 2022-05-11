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
        if (repository.notPresent(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task Id로 해당 Task를 찾을 수 없습니다");
        }

        return new TaskResponseDto(repository.taskBy(id));
    }

    @Override
    public TaskControllerOutput output() {
        return controllerOutput;
    }
}

