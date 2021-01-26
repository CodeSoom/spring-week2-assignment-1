package com.codesoom.assignment.controllers;

import com.codesoom.assignment.NotFoundException;
import com.codesoom.assignment.models.Task;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
    public List<Task> list() {
        return tasks;
    }

    @GetMapping("/{id}")
    public Task get(@PathVariable Long id) throws IOException {
        Task task = findById(id);
        return task;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task create(@RequestBody Task task) {
        task.setId(generateId());
        tasks.add(task);

        return task;
    }

    @PutMapping("/{id}")
    public Task update(@PathVariable Long id, @RequestBody Task newTask) throws IOException {
        Task oldTask = findById(id);
        oldTask.setTitle(newTask.getTitle());
        return newTask;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws IOException {
        Task task = findById(id);
        tasks.remove(task);
    }

    private Long generateId() {
        newId += 1;
        return newId;
    }

    private Task findById(Long id) throws IOException {
        return tasks.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }
}
