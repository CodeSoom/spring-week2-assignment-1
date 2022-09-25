package com.codesoom.assignment.controllers;

import com.codesoom.assignment.dto.TaskDto;
import com.codesoom.assignment.model.TaskInfo;
import com.codesoom.assignment.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public List<TaskInfo> list() {
        return taskService.findAll();
    }

    @GetMapping("/tasks/{id}")
    public TaskInfo findTask(@PathVariable("id") Long id) {
        return taskService.findById(id);
    }

    @PostMapping("/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskInfo insertTask(@RequestBody TaskDto.InsertReq request) {
        return taskService.insertTask(new TaskDto.TaskParam(request));
    }

    @RequestMapping(path = "/tasks/{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public TaskInfo updateTask(@PathVariable("id") Long id, @RequestBody TaskDto.UpdateReq request) {
        return taskService.updateTask(new TaskDto.TaskParam(id, request));
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<TaskInfo> deleteTask(@PathVariable("id") Long id) {
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
