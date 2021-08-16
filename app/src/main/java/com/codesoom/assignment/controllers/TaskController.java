package com.codesoom.assignment.controllers;

import com.codesoom.assignment.domain.Task;
import com.codesoom.assignment.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;


    @GetMapping("")
    public Map getAll() {
       return taskService.getAll();
    }

    @GetMapping("/{findId}")
    public Task getDetail(@PathVariable("findId") String findId)
    {
        return taskService.getDetail(findId);
    }

    @PostMapping("")
    public Task create(@RequestBody Task task) {
        return taskService.create(task);
    }

    @PutMapping("/{findId}")
    public Task updateTask(@PathVariable("findId") String findId, @RequestBody Task task){
       return  taskService.updateTask(findId, task);
    }

    @DeleteMapping("/{findId}")
    public void deleteTask(@PathVariable("findId") String findId){
        taskService.deleteTask(findId);
    }

}
