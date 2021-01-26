package com.codesoom.assignment.controllers;

import com.codesoom.assignment.NotFoundException;
import com.codesoom.assignment.models.Task;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private static Map<Long, Task> taskStore = new HashMap<>();
    private Long newId = 0L;
    NotFoundException notFoundException = new NotFoundException();

    @GetMapping
    public List<Task> getTaskList() {
        return new ArrayList<>(taskStore.values());
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
        task.setId(generateId());
        taskStore.put(task.getId(), task);
        return task;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Task handleUpdate(@PathVariable Long id, @RequestBody Task task) {
        if (findTask(id).isEmpty()) {
            throw notFoundException;
        }
        taskStore.replace(task.getId(), task);
        return task;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void handleDelete(@PathVariable Long id) {
        if (findTask(id).isEmpty()) {
            throw notFoundException;
        }
        Optional<Task> task = findTask(id);
        taskStore.remove(id);
    }

    private Long generateId() {
        newId += 1;
        return newId;
    }

    public Optional<Task> findTask(Long id) {
        return Optional.ofNullable(taskStore.get(id));
    }

}
