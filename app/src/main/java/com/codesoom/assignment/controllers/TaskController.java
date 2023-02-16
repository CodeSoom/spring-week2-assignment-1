package com.codesoom.assignment.controllers;

import com.codesoom.assignment.domain.Task;
import com.codesoom.assignment.dto.TaskDto;
import com.codesoom.assignment.service.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TodoService todoService = new TodoService();

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Task> list() {
        return todoService.readAllTask();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task create(@RequestBody TaskDto task) {
        todoService.checkParam(task.getTask());
        log.info("task:{}", task.getTask().getTitle());
        return todoService.createTask(task.getTask());
    }


    @RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH}, value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Task update(@RequestBody TaskDto task, @PathVariable TaskDto id) {
        log.info("task:{}", task);
        return todoService.updateTask(task.getTask(), id.getId());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable TaskDto id) {
        todoService.deleteOneTask(id.getId());
    }

}
