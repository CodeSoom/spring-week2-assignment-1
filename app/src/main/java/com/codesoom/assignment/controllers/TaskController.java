//TODO
//1. Read Collection - GET /tasks
//2. Read Item - GET /tasks/{id}
//3. Create - POST /tasks
//4. Update - PUT/PATCH /tasks/{id}
//5. Delete - DELETE/tasks/{id}

package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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

    @PostMapping
    public Task create(@RequestBody Task task) {
        task.setId(generateId());
        tasks.add(task);

        return task;
    }

    @PutMapping
    public Task update(int index, String title) {
        Task task = tasks.get(index);
        task.setTitle(title);

        return task;
    }

    private Long generateId() {
        newId += 1;
        return newId;
    }
}
