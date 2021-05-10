package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/tasks")
public class TaskController {

    private List<Task> taskList = new ArrayList<>();
    private Long newTaskId = 0L;

    @GetMapping("")
    public List<Task> getTaskList() {
        return taskList;
    }

    @PostMapping("")
    public Task createTask(@RequestBody Task task) {
        task.setId(generateId());
        taskList.add(task);

        return task;
    }

    private Long generateId() {
        newTaskId = newTaskId + 1;
        return newTaskId;
    }
}
