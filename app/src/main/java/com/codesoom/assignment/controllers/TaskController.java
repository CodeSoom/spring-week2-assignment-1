package com.codesoom.assignment.controllers;

import com.codesoom.assignment.exceptions.TaskNotFoundException;
import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.repository.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin("http://localhost:3000")
public class TaskController {
    TaskRepository taskRepository = new TaskRepository();

    @GetMapping
    public List<Task> list() {
        return taskRepository.findAll();
    }

    @GetMapping("/{id}")
    public Task detail(@PathVariable Long id) {
        if (taskRepository.isExist(id)) {
            return taskRepository.findById(id);
        }
        throw new TaskNotFoundException();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task create(@RequestBody Task task) {
        return taskRepository.save(task);
    }

    @RequestMapping(path = "/{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public Task update(@PathVariable Long id, @RequestBody Task task) {
        if (taskRepository.isExist(id)) {
            task.setId(id);
            return taskRepository.update(task);
        }
        throw new TaskNotFoundException();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        if (taskRepository.isExist(id)) {
            taskRepository.delete(id);
            return;
        }
        throw new TaskNotFoundException();
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<String> handleTaskException(TaskNotFoundException error) {
        return new ResponseEntity<>(error.getMessage(), HttpStatus.NOT_FOUND);
    }
}
