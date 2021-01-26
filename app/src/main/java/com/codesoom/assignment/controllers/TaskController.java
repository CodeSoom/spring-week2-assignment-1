package com.codesoom.assignment.controllers;

import com.codesoom.assignment.application.JsonTask;
import com.codesoom.assignment.application.TaskApplicationService;
import com.codesoom.assignment.application.TaskJsonTransfer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    TaskApplicationService taskApplicationService = new TaskApplicationService();
    TaskJsonTransfer transfer = new TaskJsonTransfer();

    @GetMapping
    public String getAllTasks(){
         return transfer.taskListToJson(taskApplicationService.getAllTasks()).orElseThrow();
    }

    @GetMapping("/{id}")
    public String getSpecificTask(@PathVariable Long id){
        return taskApplicationService.findTask(id).flatMap(it -> transfer.taskToJson(it)).orElseThrow();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createTask(@RequestBody JsonTask jsonTask){
        Long createdTaskId = taskApplicationService.createTask(jsonTask.title);
        return taskApplicationService.findTask(createdTaskId).flatMap(it -> transfer.taskToJson(it)).orElseThrow();
    }
}
