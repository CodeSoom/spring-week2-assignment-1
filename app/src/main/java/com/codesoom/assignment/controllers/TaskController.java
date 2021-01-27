package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private List<Task> tasks = new ArrayList<>();
    private Long Id = 0L;

    @GetMapping
    public List<Task> list() {
        return tasks;
    }

    @PostMapping
    public Task create(@RequestBody Task task) {
        task.setId(generateId());
        tasks.add(task);
        return task;
    }
    @PutMapping("/{id}")
    public Task update(@PathVariable Long id, @RequestBody Task task)
    {
        Task updateVar = getTask(id);
        updateVar.setTitle(task.getTitle());
        return task;
    }

    private Task getTask(Long id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst().orElse(null);
    }

    @DeleteMapping("/{id}")
    public Task delete(@PathVariable Long id, @RequestBody Task task)
    {

        task.getId();
        tasks.remove(task);
        return task;
    }

    private Long generateId() {
        Id++;
        return Id;
    }


}
