// TODO
// 1. Read Collection - GET /tasks [OK]
// 2. Read Item - GET /tasks/{id}
// 3. Create - POST /tasks/{id}
// 4. Update - PUT/PATCH /tasks/{id}
// 5. Delete - DELETE /tasks/{id}

package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.repositories.InMemoryTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("tasks")
@CrossOrigin
public class TaskController {
//    private List<Task> tasks = new ArrayList<>();
    private Long newId = 0L;

    private final InMemoryTaskRepository TaskRepository;

    @Autowired
    public TaskController(InMemoryTaskRepository taskRepository) {
        TaskRepository = taskRepository;
    }

    @GetMapping
    public List<Task> tasks() {
        return TaskRepository.fetchAll();
    }

    @GetMapping("/{id}")
    public Task task(@PathVariable("id") Long id) {
        if(id == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task ID cannot be 0!");
        }

        Task task = TaskRepository.fetchOne(id);
        if (task == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found!");
        }

        return task;
    }

    @PostMapping
    public List<Task> create(@RequestBody Task task) {
        if(task.getTitle().isBlank()) {
            // TODO: Validation error...
        }

        task.setId(generateId());
        return TaskRepository.createOne(task);
    }

    private Long generateId() {
        newId += 1;
        return newId;
    }
}
