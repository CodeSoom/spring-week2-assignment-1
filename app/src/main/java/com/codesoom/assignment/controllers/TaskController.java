package com.codesoom.assignment.controllers;

import com.codesoom.assignment.domain.Task;
import com.codesoom.assignment.service.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/tasks")
public class TaskController {

    private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    private final TodoService todoService = new TodoService();

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Task> list() {
        return todoService.readAllTask();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task create(@RequestBody Task task) {
        todoService.checkParam(task);
        log.info("task:{}", task.getTitle());
        return todoService.createTask(task);
    }


    @RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH}, value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Task update(@RequestBody Task task, @PathVariable Long id) {
        log.info("task:{}", task);
        return todoService.updateTask(task, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        todoService.deleteOneTask(id);
    }

}
