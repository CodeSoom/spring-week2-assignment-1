package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class RequestController {
    private final TaskService taskManager = new TaskService();

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<Task> GetAllTask(){
        return taskManager.getAllTask();
    }

    @GetMapping("/{TaskID}")
    @ResponseStatus(HttpStatus.OK)
    public Task GetTask(@PathVariable("TaskID") Long id) {
        return taskManager.getKeyTask(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task Create(@RequestBody Task task){
        return taskManager.addTask(task);
    }

    @PutMapping("/{TaskID}")
    @PatchMapping("/{TaskID}")
    @ResponseStatus(HttpStatus.OK)
    public Task Revise(@PathVariable("TaskID") Long id, @RequestBody Task task){
        System.out.println(id);
        return taskManager.modifyTask(id, task);
    }

    @DeleteMapping("/{TaskID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void Delete(@PathVariable("TaskID") Long id){
        taskManager.removeTask(id);
    }
}
