package com.codesoom.assignment.controllers;
// 1. READ Collection - GET / tasks => 완료
// 2. READ Item - GET / tasks/{id} => 완료
// 3. CREATE POST/tasks => 완료
// 4. UPDATE PUT/PATCH/tasks/{id} =>
// 5. DELETE DELETE/tasks/{id}

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

    @GetMapping("/{id}")
    public Task getTask(@PathVariable(value = "id") Long id) {
        return tasks.stream().filter(task -> task.getId().equals(id)).findFirst().orElse(null);
    }

    @PostMapping
    public Task create(@RequestBody Task task) {
        task.setId(generateId());
        tasks.add(task);
        return task;
    }

    @PutMapping("/{id}")
    public Task update(@PathVariable(value = "id") Long id, @RequestBody Task task) {
        Task originTask = tasks.stream().filter(t -> t.getId().equals(id)).findFirst().orElse(null);
        if (originTask != null) {
            originTask.setTitle(task.getTitle());
            return originTask;
        }
        return null;
    }

    private Long generateId(){
        newId += 1;
        return newId;
    }
}
