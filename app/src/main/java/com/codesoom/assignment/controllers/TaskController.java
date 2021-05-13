// TODO
// 1. Read Collection - GET /tasks [OK]
// 2. Read Item - GET /tasks/{id}
// 3. Create - POST /tasks/{id}
// 4. Update - PUT/PATCH /tasks/{id}
// 5. Delete - DELETE /tasks/{id}

package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.repositories.TaskRepositoryImpl;
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

    private final TaskRepositoryImpl TaskRepository;

    @Autowired
    public TaskController(TaskRepositoryImpl taskRepository) {
        TaskRepository = taskRepository;
    }

    @GetMapping
    public List<Task> tasks() {
        return TaskRepository.fetchAll();
    }

    @GetMapping("/{id}")
    public Task task(@PathVariable("id") long id) {
        if(id == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task ID cannot be 0!");
        }

        Task fetchedTask = TaskRepository.fetchOne(id);
        if (fetchedTask == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found!");
        }

        return fetchedTask;
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
