package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private List<Task> tasks = new ArrayList<>();
    private Long newId = 0L;

    @GetMapping
    public List<Task> getTaskList() {
        return tasks;
    }

    @PostMapping
    public Object createTask(@RequestBody Task task, HttpServletResponse response) {
        if(task.getTitle().isBlank()) {
            response.setStatus(400);
            return "";
        }
        else {
            task.setId(generateId());
            tasks.add(task);
            response.setStatus(201);
            return task;
        }
    }

    private Long generateId() {
        newId += 1;
        return newId;
    }
}
