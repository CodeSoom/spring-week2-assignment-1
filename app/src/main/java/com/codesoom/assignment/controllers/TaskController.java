package com.codesoom.assignment.controllers;

// 1. Read Collection - GET /tasks ==> 완료
// 2. Read Item - GET /tasks/{id}
// 3. Create - POST /tasks ==> 완료
// 4. update - PUT/PATCH /tasks{id}
// 5. delete - DELETE /tasks{id}

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
    public  Task create(@RequestBody Task task){
        if (task.getTitle().isBlank()){
            // TODO: validation error...
        }
        task.setId(generateId());
        tasks.add(task);

        return task;
    }

    private Long generateId() {
        newId += 1;
        return newId;
    }
}

