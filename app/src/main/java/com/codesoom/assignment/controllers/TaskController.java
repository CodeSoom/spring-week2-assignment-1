package com.codesoom.assignment.controllers;

import com.codesoom.assignment.exceptions.TaskNotFoundException;
import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.repository.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {
    private final TaskRepository taskRepository = TaskRepository.getInstance();
    private final static List<Task> tasks = new ArrayList<>();

    @GetMapping
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    @GetMapping("/{id}")
    public Task getTask(@PathVariable Long id) {
        Optional<Task> task = findTask(id);

        if (task.isEmpty()) {
            throw new TaskNotFoundException();
        }

        return taskRepository.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody Task task) {
        return taskRepository.save(task);
    }

    @RequestMapping(value = "/{id}",
            method = {RequestMethod.PUT, RequestMethod.PATCH})
    public Task updateTask(@PathVariable Long id, @RequestBody Task task) {
        Optional<Task> tasks = findTask(id);

        if (tasks.isEmpty()) {
            throw new TaskNotFoundException();
        }

        return taskRepository.update(id, task);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long id) {
        Optional<Task> task = findTask(id);

        if (task.isEmpty()) {
            throw new TaskNotFoundException();
        }

        taskRepository.delete(id);
    }

    private Optional<Task> findTask(Long id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst();
    }

}
