package com.codesoom.assignment.controllers;

import com.codesoom.assignment.model.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//TODO
//1. Read Collection - GET/tasks
//2. Read Item - GET/tasks/{id}
//3. Create - POST/tasks
//4. Update - PUT/PATCH /tasks/{id}
//5. Delete - DELETE /tasks/{id}
@RestController
@RequestMapping("/tasks")
public class TaskController {
    private List<Task> tasks = new ArrayList<>();
    private Long newId = 0L;

    @GetMapping
    public List<Task> list(){

        return tasks;
    }

}
