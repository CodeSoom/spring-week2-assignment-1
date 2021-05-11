package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 * 1. Read Collection - GET /tasks
  */


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
    public Task details(@PathVariable String id){
        Task task = tasks.get(Integer.parseInt(id) - 1);
        return task;
    }

    @PostMapping
    public Task created(@RequestBody Task task) {
        task.setId(generatedId());
        task.setTitle(task.getTitle());
        tasks.add(task);

        return task;
    }

    @PutMapping("/{id}")
    public Task update(@PathVariable String id, @RequestBody Task old_task) {
        tasks.get(Integer.parseInt(id) - 1).setTitle(old_task.getTitle());
        Task updated_task = tasks.get(Integer.parseInt(id) - 1);
        return updated_task;
    }

    @DeleteMapping
    public List<Task> delete(@PathVariable String id, @RequestBody Task old_task) {
        tasks.remove(Integer.parseInt(id) - 1);
        return tasks;
    }

    private Long generatedId() {
        newId += 1;
        return newId;
    }
}
