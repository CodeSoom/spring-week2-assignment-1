package com.codesoom.assignment.controllers;

import com.codesoom.assignment.Task;
import com.codesoom.assignment.TaskRepository;
import com.codesoom.assignment.controllers.dtos.TaskRequestDtoCreating;
import com.codesoom.assignment.controllers.dtos.TaskRequestDtoUpdating;
import com.codesoom.assignment.controllers.dtos.TaskResponseDto;
import com.codesoom.assignment.interfaces.ControllerOutput;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskControllerOutput implements ControllerOutput {
    private final TaskRepository repository;

    TaskControllerOutput(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    @PostMapping
    public void create(@RequestBody TaskRequestDtoCreating requestDto) {
        Task task = requestDto.toEntity();
        System.out.println(task.toString());
        repository.output().save(task);
    }

    @Override
    public void update(TaskRequestDtoUpdating requestDto) {
        repository.output().update(requestDto.id(), requestDto.toEntity());
    }

    @Override
    public void deleteBy(Long id) {
        repository.output().deleteBy(id);
    }

    @Override
    public TaskResponseDto responseDtoCreated() {
        return null;
    }

    @Override
    public TaskResponseDto responseDtoUpdated() {
        return null;
    }
}
