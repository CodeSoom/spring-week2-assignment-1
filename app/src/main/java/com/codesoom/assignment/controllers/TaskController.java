package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.models.Tasks;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.PATCH;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 1800)
public class TaskController {
    private final Tasks tasks = new Tasks();

    @GetMapping
    @ResponseStatus(OK)
    public List<Task> list() {
        return tasks.readAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public Task read(@PathVariable Long id) {
        return tasks.read(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Task create(@RequestBody Task task) {
        return tasks.create(task);
    }

    @RequestMapping(value = "/{id}", method = { PUT, PATCH })
    @ResponseStatus(OK)
    public Task update(@PathVariable Long id, @RequestBody Task task) {
        return tasks.update(id, task.getTitle());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Long id) {
        tasks.delete(id);
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(NOT_FOUND)
    public String handleNotFound() {
        return "Not Found";
    }
}
