package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public List<Task> listTask() {
        return tasks;
    }

    @GetMapping("/{id}")
    public Task findTask(@PathVariable Long id) {
        Optional<Task> task = Optional.ofNullable(findbyId(id));

        if (task.isEmpty()) {
            return null;
        }

        return task.get();
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.PUT, RequestMethod.POST})
    public Task updateTask(@RequestBody Task everything, @PathVariable Long id) {
        Task task = findbyId(id);
        //if절로 NullPointerException 예외 처리
        if (task == null) {
            System.out.println("java.lang.NullPointerException");
        }

        if (task != null) {
            task.setTitle(everything.getTitle());
        }

        return task;
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        task.setId(generateId());
        tasks.add(task);

        return task;
    }

    @DeleteMapping("/{id}")
    public Task deleteTask(@PathVariable Long id) {
        Task task = findbyId(id);
        tasks.remove(task);

        return task;
    }

    private Long generateId() {
        newId += 1;

        return newId;
    }

    private Task findbyId(Long id) {
        Optional <Task> task = Optional.ofNullable(tasks.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst()
                .orElse(null));
        return null;
    }
}
