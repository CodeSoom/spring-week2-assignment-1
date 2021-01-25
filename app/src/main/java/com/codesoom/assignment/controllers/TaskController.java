package com.codesoom.assignment.controllers;

// 1. Read Collection - GET /tasks ==> 완료
// 2. Read Item - GET /tasks/{id}
// 3. Create - POST /tasks ==> 완료
// 4. update - PUT/PATCH /tasks{id}
// 5. delete - DELETE /tasks{id}

import com.codesoom.assignment.NotFoundException;
import com.codesoom.assignment.models.Task;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private List<Task> tasks = new ArrayList<>();
    private Long newId = 0L;
    NotFoundException notFoundException = new NotFoundException();

    @GetMapping
    public List<Task> getTaskList() {
        return tasks;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Task getTaskById(@PathVariable Long id){
        if(findTask(id) == null){
            throw notFoundException;
        }
        return findTask(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task handleCreate(@RequestBody Task task) {
        task.setId(generateId());
        tasks.add(task);
        return task;
    }

    private Long generateId() {
        newId += 1;
        return newId;
    }

    public Task findTask(Long id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

}

