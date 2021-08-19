package com.codesoom.assignment.controllers;

import com.codesoom.assignment.dtos.TaskDTO;
import com.codesoom.assignment.exceptions.TaskNotFoundException;
import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.repositories.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;

@CrossOrigin
@RestController
@RequestMapping("/tasks")
public final class TaskController {
    private static final String ID_PATH = "/{id}";
    private static final String ID = "id";

    @GetMapping
    public List<Task> getTasks() {
        return TaskRepository.getTasks();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task postTask(@RequestBody final TaskDTO taskDTO) {
        return TaskRepository.createTask(taskDTO);
    }

    @GetMapping(ID_PATH)
    public Task getTask(@PathVariable(ID) final Long id) {
        final OptionalInt optionalInt = TaskRepository.findTaskIndex(id);
        if (optionalInt.isEmpty()) {
            throw new TaskNotFoundException();
        }
        return TaskRepository.getTask(optionalInt.getAsInt());
    }

    @RequestMapping(value = ID_PATH, method = {RequestMethod.PUT, RequestMethod.PATCH})
    public Task updateTask(@PathVariable(ID) final Long id, @RequestBody final TaskDTO taskDTO) {
        final OptionalInt optionalInt = TaskRepository.findTaskIndex(id);
        if (optionalInt.isEmpty()) {
            throw new TaskNotFoundException();
        }
        return TaskRepository.updateTask(optionalInt.getAsInt(), taskDTO);
    }

    @DeleteMapping(ID_PATH)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable(ID) final Long id) {
        final OptionalInt optionalInt = TaskRepository.findTaskIndex(id);
        if (optionalInt.isEmpty()) {
            throw new TaskNotFoundException();
        }
        TaskRepository.deleteTask(optionalInt.getAsInt());
    }
}
