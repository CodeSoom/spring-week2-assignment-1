package com.codesoom.assignment.controllers;

import com.codesoom.assignment.dtos.TaskDTO;
import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.repositories.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/tasks")
public final class TaskController {
    private static final String ID_PATH = "/{id}";

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
    public Task getTask(@RequestAttribute("taskIndex") final int taskIndex) {
        return TaskRepository.getTask(taskIndex);
    }

    @RequestMapping(value = ID_PATH, method = {RequestMethod.PUT, RequestMethod.PATCH})
    public Task updateTask(
        @RequestAttribute("taskIndex") final int taskIndex, @RequestBody final TaskDTO taskDTO
        ) {
        return TaskRepository.updateTask(taskIndex, taskDTO);
    }

    @DeleteMapping(ID_PATH)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@RequestAttribute("taskIndex") final int taskIndex) {
        TaskRepository.deleteTask(taskIndex);
    }
}
