package com.codesoom.assignment.controllers;

import com.codesoom.assignment.NotFoundException;
import com.codesoom.assignment.TaskRepository;
import com.codesoom.assignment.models.Task;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {

    TaskRepository taskRepository = new TaskRepository();
    NotFoundException notFoundException = new NotFoundException();

    @GetMapping
    public List<Task> getTaskList() {
        return new ArrayList<>(taskRepository.taskStore.values());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Task> getTaskById(@PathVariable Long id) {
        if (findTask(id).isEmpty()) {
            throw notFoundException;
        }
        return findTask(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task handleCreate(@RequestBody Task task) {
        task.setId(taskRepository.generateId());
        taskRepository.taskStore.put(task.getId(), task);
        return task;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Task handleUpdate(@PathVariable Long id, @RequestBody Task task) {
        if (findTask(id).isEmpty()) {
            throw notFoundException;
        }
        Task updateTask = task;
        updateTask.setId(id);
        taskRepository.taskStore.replace(task.getId(), task);
        return task;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void handleDelete(@PathVariable Long id) {
        if (findTask(id).isEmpty()) {
            throw notFoundException;
        }
        Optional<Task> task = findTask(id);
        taskRepository.taskStore.remove(id);
    }

    public Optional<Task> findTask(Long id) {
        return Optional.ofNullable(taskRepository.taskStore.get(id));
    }

}
