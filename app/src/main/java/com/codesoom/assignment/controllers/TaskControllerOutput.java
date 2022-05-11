package com.codesoom.assignment.controllers;

import com.codesoom.assignment.Task;
import com.codesoom.assignment.TaskRepository;
import com.codesoom.assignment.controllers.dtos.TaskRequestDto;
import com.codesoom.assignment.controllers.dtos.TaskResponseDto;
import com.codesoom.assignment.interfaces.ControllerOutput;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskControllerOutput implements ControllerOutput {
    private final TaskRepository repository;

    TaskControllerOutput(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponseDto create(@RequestBody TaskRequestDto requestDto) {
        Task task = requestDto.toEntity();
        repository.output().save(task);

        return new TaskResponseDto(repository.output().taskSaved());
    }

    @Override
    @PutMapping("/{id}")
    public TaskResponseDto update(@PathVariable Long id, @RequestBody TaskRequestDto requestDto) {
        repository.output().update(id, requestDto.toEntity());

        return new TaskResponseDto(repository.output().taskUpdated());
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBy(@PathVariable Long id) {
        repository.output().deleteBy(id);
    }

}
