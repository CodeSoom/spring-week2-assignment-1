package com.codesoom.assignment.controllers;

import com.codesoom.assignment.exceptions.TaskNotFoundException;
import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.repositories.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {

    private TaskRepository taskRepository;


    public TaskController() {
        taskRepository = new TaskRepository();
    }

    @GetMapping
    public List<Task> list() {

        return taskRepository.getTasks();
    }

    @GetMapping("{id}")
    public Task detail(@PathVariable Long id) {
        return taskRepository.getTask(id);
    }


    @PostMapping
    public Task create(@RequestBody Task task) {
        return taskRepository.createTask(task);
    }


    @PatchMapping("{id}")
    public Task patch(@PathVariable Long id, @RequestBody Task source) {
        return taskRepository.updateTask(id, source);
    }


    @PutMapping("{id}")
    public Task update(@PathVariable Long id, @RequestBody Task source) {
        return taskRepository.updateTask(id, source);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws IOException {
        taskRepository.deleteTask(id);
    }

}
