package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.services.TaskService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }


    @PostMapping
    public Task postTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }


    @GetMapping("/{id}")
    public Task getTask(@PathVariable Long id){
        return taskService.getTaskById(id);
    }


    @GetMapping
    public List<Task> getTaskList(){
        return taskService.getTaskList();
    }


    @PutMapping("/{id}")
    public Task putTask(@PathVariable Long id, @RequestBody Task task){
        task.setId(id);
        return taskService.updateTask(task);
    }


    @PatchMapping("/{id}")
    public Task patchTask(@PathVariable Long id, @RequestBody Task task){
        task.setId(id);
        return taskService.updateTask(task);
    }


    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
    }



}


