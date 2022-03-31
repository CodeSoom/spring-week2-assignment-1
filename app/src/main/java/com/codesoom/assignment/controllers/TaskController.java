package com.codesoom.assignment.controllers;

import com.codesoom.assignment.domains.Task;
import com.codesoom.assignment.domains.TaskDto;
import com.codesoom.assignment.services.TaskCreateService;
import com.codesoom.assignment.services.TaskDeleteService;
import com.codesoom.assignment.services.TaskReadService;
import com.codesoom.assignment.services.TaskUpdateService;
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

    private final TaskCreateService createService;
    private final TaskReadService readService;
    private final TaskUpdateService updateService;
    private final TaskDeleteService deleteService;

    public TaskController(TaskCreateService createService, TaskReadService readService,
                          TaskUpdateService updateService, TaskDeleteService deleteService) {
        this.createService = createService;
        this.readService = readService;
        this.updateService = updateService;
        this.deleteService = deleteService;
    }

    @GetMapping
    public List<Task> getTasks() {
        return readService.getTasks();
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    public Task addTask(@RequestBody TaskDto taskDto) {
        return createService.addTask(taskDto);
    }

    @GetMapping("/{id}")
    public Task getTask(@PathVariable(name = "id") Long id) {
        return readService.findTaskById(id);
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public Task updateTask(@PathVariable(name = "id") Long id,
                           @RequestBody TaskDto taskDto) {
        return updateService.updateTaskById(id, taskDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable(name = "id") Long id) {
        deleteService.deleteTaskById(id);
    }

}
