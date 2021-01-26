package com.codesoom.assignment.controllers;

import com.codesoom.assignment.controllers.models.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    List<Task> tasks = new ArrayList<>();
    Long id = 0L;

    @GetMapping("")
    public List<Task> lists() {
        return tasks;
    }

    @GetMapping("/{id}")
    public Task list(@PathVariable Long id) {
        return getTask(id);
    }

    @PostMapping
    public Task create(@RequestBody Task task) {
        Task newTask = new Task();
        newTask.setTitle(task.getTitle());
        newTask.setId(generateId());
        tasks.add(newTask);

        return newTask;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        Task task = getTask(id);
        tasks.remove(task);

        return "";
    }

    @PutMapping("/{id}")
    public Task putUpdate(@PathVariable Long id, @RequestBody Task task) {
        Task updateTask = getTask(id);
        updateTask.setTitle(task.getTitle());
        return updateTask;
    }

    @PatchMapping("/{id}")
    public String patchUpdate(@PathVariable Long id, @RequestBody Task task) {
        Task updateTask = getTask(id);
        updateTask.setTitle(task.getTitle());
        return "Updated";
    }

    public Task getTask(Long id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst().orElse(null);
    }

    public Long generateId(){
        id += 1;
        return id;
    }
}
