package com.codesoom.assignment.controllers;

// TODO
// 1. GET /tasks (done)
// 2. GET /tasks/{id}
// 3. POST /tasks (done)
// 4. PUT/PATCH /tasks/{id}
// 5. DELETE /tasks/{id} (done)

import com.codesoom.assignment.models.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private List<Task> tasks = new ArrayList<>();
    private Long newId = 0L;

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

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Task task = findTask(id);
        tasks.remove(task);
    }

    private Task findTask(Long id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    private Long generateId() {
        newId += 1;
        return newId;
    }


}
