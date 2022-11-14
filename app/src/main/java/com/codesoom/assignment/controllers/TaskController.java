package com.codesoom.assignment.controllers;

import com.codesoom.assignment.model.Task;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    private Long id = 0L;

    /**
     * GET /tasks
     * @return tasks
     */
    @GetMapping
    public List<Task> getLists() {
        return tasks;
    }

    @PostMapping
    public Task createList(@RequestBody Task task) {
        task.setTitle(task.getTitle());
        task.setId(generateId());
        tasks.add(task);

        return task;
    }

    /**
     * id 1씩 증가
     * @return 1 증가된 id
     */
    private Long generateId() {
        id += 1;
        return id;
    }
}
