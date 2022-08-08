package com.codesoom.assignment.controllers;


import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.services.TodoService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/tasks")
public class TodoController {

    private final TodoService todoService = new TodoService();

    @GetMapping("")
    public List<Task> list() {
        return todoService.findTasks();
    }

    @PostMapping("")
    public Task create(@RequestBody Task task) {
        return todoService.create(task);
    }

    @GetMapping("/{userId}")
    public Task findOne(@PathVariable("userId") Long id) {
        return todoService.findTask(id);

    }

    @PutMapping("/{userId}")
    public Task updatePut(@RequestBody Task task, @PathVariable("userId") Long id) {
        task.setId(id);
        return todoService.updateTask(task);
    }
    @PatchMapping("/{userId}")
    public Task updatePatch(@RequestBody Task task, @PathVariable("userId") Long id) {
        task.setId(id);
        return todoService.updateTask(task);
    }

    @DeleteMapping("/{userId}")
    public Task delete(@PathVariable("userId") Long id) {

        return todoService.deleteTask(id);
    }
}
