// TODO
// 1. Read Collection - GET /tasks [OK]
// 2. Read Item - GET /tasks/{id}
// 3. Create - POST /tasks/{id}
// 4. Update - PUT/PATCH /tasks/{id}
// 5. Delete - DELETE /tasks/{id}

package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("tasks")
@CrossOrigin
public class TaskController {
    private List<Task> tasks = new ArrayList<>();
    private Long newId = 0L;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Task> list() {
        return tasks;
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public List<Task> create(@RequestBody Task task) {
        if(task.getTitle().isBlank()) {
            // TODO: Validation error...
        }

        task.setId(generateId());
        tasks.add(task);

        return tasks;
    }

    private Long generateId() {
        newId += 1;
        return newId;
    }
}
