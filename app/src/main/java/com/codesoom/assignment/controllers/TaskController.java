package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {
    private TaskService taskService;

    public TaskController(){
        taskService = new TaskService();
    }

    @GetMapping
    public List<Task> list() {
        return taskService.getTasks();
    }

    @GetMapping("/{id}")
    public Task details(@PathVariable Long id){
        return taskService.findTask(id);
    }

    @PostMapping
    public Task created(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @PutMapping(value = "/{id}")
    public Task update(@PathVariable Long id, @RequestBody Task old_task) {
        return taskService.updateTask(id,old_task);
    }

    @PatchMapping(value = "/{id}")
    public Task patch(@PathVariable Long id, @RequestBody Task old_task) {
        return taskService.updateTask(id,old_task);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}
