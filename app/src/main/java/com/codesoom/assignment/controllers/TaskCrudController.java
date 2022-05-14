package com.codesoom.assignment.controllers;

import com.codesoom.assignment.DefaultTask;
import com.codesoom.assignment.TaskLoadingRepository;
import com.codesoom.assignment.controllers.dtos.TaskRequestDto;
import com.codesoom.assignment.controllers.dtos.TaskResponseDto;
import com.codesoom.assignment.controllers.validations.RequestBodyValidation;
import com.codesoom.assignment.interfaces.TaskController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "http://localhost:3000")
public class TaskCrudController implements TaskController {
    private final TaskLoadingRepository repository;

    public TaskCrudController(TaskLoadingRepository repository) {
        this.repository = repository;
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
    public TaskResponseDto showBy(@PathVariable final Long id) {
        return new TaskResponseDto(repository.taskBy(id));
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponseDto create(@RequestBody final TaskRequestDto requestDto) {
        new RequestBodyValidation(requestDto).validate();

        final DefaultTask task = requestDto.toEntity();

        return new TaskResponseDto(repository.manipulator().save(task));
    }

    @Override
    @RequestMapping(path = "/{id}", method = {RequestMethod.PATCH, RequestMethod.PUT})
    public TaskResponseDto update(@PathVariable final Long id, @RequestBody final TaskRequestDto requestDto) {
        new RequestBodyValidation(requestDto).validate();

        final DefaultTask taskUpdating = new DefaultTask(id, requestDto.getTitle());

        return new TaskResponseDto(repository.manipulator().update(taskUpdating));
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBy(@PathVariable final Long id) {
        repository.manipulator().deleteBy(id);
    }
}

