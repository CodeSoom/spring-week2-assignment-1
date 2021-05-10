package com.codesoom.assignment.controllers;
// 1. READ Collection - GET / tasks => 완료
// 2. READ Item - GET / tasks/{id}
// 3. CREATE POST/tasks => 완
// 4. UPDATE PUT/PATCH/tasks/{id}
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
    public int getTask(@PathVariable(value = "id") int id) {
        for (Task task : tasks) {
            System.out.println(task);
        }
        return id;
    }

    @PostMapping
    public Task create(@RequestBody Task task) {
        task.setId(generateId());
        tasks.add(task);
        return task;
    }

    private Long generateId(){
        newId += 1;
        return newId;
    }
}
