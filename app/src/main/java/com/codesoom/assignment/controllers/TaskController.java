package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.models.Tasks;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.PATCH;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 1800)
public class TaskController {
    private final Tasks taskBookKeeper = new Tasks();

    @GetMapping
    public List<Task> list() {
        return taskBookKeeper.readAll();
    }

    @GetMapping("/{id}")
    public Task read(@PathVariable Long id) {
        return taskBookKeeper.read(id);
    }

    @PostMapping
    public Task create(@RequestBody Task task) {
        return taskBookKeeper.create(task);
    }

    @RequestMapping(value = "/{id}", method = { PUT, PATCH })
    public Task update(@PathVariable Long id, @RequestBody Task task) {
        return taskBookKeeper.update(id, task.getTitle());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        taskBookKeeper.delete(id);
    }
}
