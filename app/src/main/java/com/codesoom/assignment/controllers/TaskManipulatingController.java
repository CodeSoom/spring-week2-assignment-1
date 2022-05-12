package com.codesoom.assignment.controllers;

import com.codesoom.assignment.Task;
import com.codesoom.assignment.TaskLoadingRepository;
import com.codesoom.assignment.controllers.dtos.TaskRequestDto;
import com.codesoom.assignment.controllers.dtos.TaskResponseDto;
import com.codesoom.assignment.interfaces.ManipulatingController;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/tasks")
@Transactional
@CrossOrigin(origins = "http://localhost:3000")
public class TaskManipulatingController implements ManipulatingController {
    private final TaskLoadingRepository repository;

    public TaskManipulatingController(TaskLoadingRepository repository) {
        this.repository = repository;
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponseDto create(@RequestBody TaskRequestDto requestDto) {
        new RequestBodyValidation(requestDto).validate();

        Task task = requestDto.toEntity();
        repository.manipulator().save(task);

        return new TaskResponseDto(repository.manipulator().taskSaved());
    }


    @Override
    @PutMapping("/{id}")
    public TaskResponseDto update(@PathVariable Long id, @RequestBody TaskRequestDto requestDto) {
        new RequestBodyValidation(requestDto).validate();
        new RequestParamValidation(id, repository).validate();

        repository.manipulator().update(id, requestDto.toEntity());
        return new TaskResponseDto(repository.manipulator().taskUpdated());
    }


    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBy(@PathVariable Long id) {
        new RequestParamValidation(id, repository).validate();

        repository.manipulator().deleteBy(id);
    }
}
