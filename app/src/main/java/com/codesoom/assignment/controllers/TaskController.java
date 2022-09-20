package com.codesoom.assignment.controllers;

import com.codesoom.assignment.error.IdEmptyException;
import com.codesoom.assignment.models.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {
    private List<Task> tasks = new ArrayList<>();

    private Long newId = 0L;

    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    public void idEmpty(){
        throw new IdEmptyException();
    }
    @GetMapping
    public List<Task> read() {
        return tasks;
    }

    @GetMapping("/{id}")
    public Task read(@PathVariable Long id, Exception e) {
        return taskFindId(id);
    }

    @PostMapping
    public Task create(@RequestBody Task task) {
        task.setId(generateId());
        tasks.add(task);
        return task;
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.PATCH, RequestMethod.PUT})
    public void update(@PathVariable Long id, @RequestBody Task task) {
        Task findTask = taskFindId(id);
        findTask.setTitle(task.getTitle());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        tasks.remove(taskFindId(id));
    }


    private Task taskFindId(Long id) {
        Task task =tasks.stream().filter(i-> Objects.equals(i.getId(), id))
                .findFirst()
                .orElseThrow(null);
        return task;
    }


    private Long generateId() {
        newId += 1;
        return newId;
    }






}
