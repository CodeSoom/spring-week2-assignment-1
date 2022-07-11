package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class RequestController {
    private final TaskController taskManager = new TaskController();

    @GetMapping
    public List<Task> GetAllTask(){
        return taskManager.getAllTask();
    }

    @GetMapping("/{TaskID}")
    public Task GetTask(@PathVariable("TaskID") Long id) {
        return taskManager.getKeyTask(id);
    }

    @PostMapping
    public Task Create(@RequestBody Task task){
        return taskManager.addTask(task);
    }

    @PutMapping("/{TaskID}")
    public Task Revise(@PathVariable("TaskID") Long id, @RequestBody Task task){
        return taskManager.modifyTask(id, task);
    }

    @DeleteMapping("/{TaskID}")
    public void Delete(@PathVariable("TaskID") Long id){
        taskManager.removeTask(id);
    }
}
