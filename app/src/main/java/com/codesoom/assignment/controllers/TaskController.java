package com.codesoom.assignment.controllers;

import com.codesoom.assignment.domain.Task;
import com.codesoom.assignment.service.TodoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
TODO
1. READ Collection -> get/tasks
2. read item - get/tasks/{id}
3. create - post/tasks/{id}
4. update - put/patch
 */
@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {

    private final TodoService todoService = new TodoService();

    @GetMapping
    public List<Task> list() {
        return todoService.readAllTask();
    }

    @PostMapping
    public Task create(@RequestBody Task task) {
        return todoService.createTask(task);
    }

    @PutMapping
    public void update() {
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        todoService.deleteOneTask(id);
    }

}

