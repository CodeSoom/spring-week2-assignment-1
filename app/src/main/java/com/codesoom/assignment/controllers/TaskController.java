package com.codesoom.assignment.controllers;

import com.codesoom.assignment.domains.Task;
import com.codesoom.assignment.domains.TaskDto;
import com.codesoom.assignment.services.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
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


/**
 * /tasks 경로에 매핑되는 처리를 합니다.
 */
@CrossOrigin
@RequestMapping("/tasks")
@RestController
public class TaskController {

    private final Logger log = LoggerFactory.getLogger(TaskController.class);

    private final TaskService service;

    public TaskController(TaskService taskService) {
        this.service = taskService;
    }

    @GetMapping
    public List<Task> getTasks() {
        return service.getTasks();
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    public Task addTask(@RequestBody TaskDto taskDto) {
        return service.addTask(taskDto);
    }

    @GetMapping("/{id}")
    public Task getTask(@PathVariable(name = "id") Long id) {
        return service.findTaskById(id);
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public Task updateTask(@PathVariable(name = "id") Long id,
                           @RequestBody TaskDto taskDto) {
        return service.updateTaskById(id, taskDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable(name = "id") Long id) {
        service.deleteTaskById(id);
    }

}
