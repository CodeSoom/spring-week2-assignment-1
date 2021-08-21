package com.codesoom.assignment.controllers;

import com.codesoom.assignment.exception.TaskNotFoundException;
import com.codesoom.assignment.models.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {

    private List<Task> tasks = new ArrayList<>();
    private Long newId = 0L;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getTaskList() {
        return tasks;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Task getTask(@PathVariable Long id) {
        Optional<Task> task = findTaskById(id);
        if (!task.isPresent()) {
            throw new TaskNotFoundException(Long.toString(id));
        }
        return task.get();
    }

    @PostMapping
    public ResponseEntity<Void> createTask(@RequestBody Task task) {
        generateTaskId();
        task.setId(newId);
        tasks.add(task);
        return new ResponseEntity(task, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    @ResponseStatus(HttpStatus.OK)
    public Task updateTask(@PathVariable Long id, @RequestBody Task requestTask) {
        Optional<Task> task = findTaskById(id);
        if (!task.isPresent()) {
            throw new TaskNotFoundException(Long.toString(id));
        }
        task.get().setTitle(requestTask.getTitle());
        return task.get();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void completeTask(@PathVariable Long id) {
        Optional<Task> task = findTaskById(id);
        if (!task.isPresent()) {
            throw new TaskNotFoundException(Long.toString(id));
        }
        tasks.remove(task.get());
    }

    public void generateTaskId() {
        newId += 1L;
    }

    private Optional<Task> findTaskById(Long id) {
        return tasks
                .stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();
    }
}