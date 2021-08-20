package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.models.TaskRequest;
import com.codesoom.assignment.models.TasksStorage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {
    private final TasksStorage tasksStorage = new TasksStorage();

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<Task> readAll() {
        return tasksStorage.readAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Task read(@PathVariable Long id) throws NoSuchElementException {
        return tasksStorage.read(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task create(@RequestBody TaskRequest taskRequest) {
        return  tasksStorage.create(taskRequest.getTitle());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Task update(@PathVariable Long id, @RequestBody TaskRequest taskRequest) throws NoSuchElementException {
        return tasksStorage.update(id, taskRequest.getTitle());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Task delete(@PathVariable Long id) throws NoSuchElementException {
        return tasksStorage.delete(id);
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFound() {
        return "Not Found";
    }
}
