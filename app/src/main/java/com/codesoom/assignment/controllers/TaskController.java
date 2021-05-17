package com.codesoom.assignment.controllers;

import com.codesoom.assignment.application.TaskService;
import com.codesoom.assignment.models.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/tasks")
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService =  taskService;
    }

    @GetMapping("")
    public ResponseEntity getTaskList() {
        List<Task> taskList = taskService.getTaskList();

        return new ResponseEntity(taskList, HttpStatus.OK);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity getTask(@PathVariable Long taskId) {
        Task searchTask = taskService.getTask(taskId);

        return new ResponseEntity(searchTask, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity createTask(@RequestBody Task task) {
        Task createdTask = taskService.createTask(task);

        return new ResponseEntity(createdTask, HttpStatus.CREATED);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity updateTask(@PathVariable Long taskId, @RequestBody Task requestTask) {
        Task modifiedTask = taskService.updateTask(taskId, requestTask);

        return new ResponseEntity(modifiedTask, HttpStatus.OK);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity deleteTask(@PathVariable Long taskId) {
        taskService.removeTask(taskId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
