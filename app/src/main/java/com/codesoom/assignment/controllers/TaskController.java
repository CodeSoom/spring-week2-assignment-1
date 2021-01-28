package com.codesoom.assignment.controllers;

import com.codesoom.assignment.Task;
import com.codesoom.assignment.TaskList;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Handle requests about task.
 */
@RestController
@RequestMapping("/tasks")
public class TaskController {
    TaskList taskList = new TaskList();
    JsonParser jsonParser = new JsonParser();

    @GetMapping
    public List<Task> list() {
        return taskList.getTaskList();
    }

    @GetMapping("/{id:[0-9]+}")
    public Task detail(@PathVariable Long id) {
        try {
            return taskList.getTask(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Not found task"
            );
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task create(@RequestBody String title) {
        try {
            return taskList.add(title);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Unknown error"
            );
        }
    }

    @RequestMapping(path = "/{id:[0-9]+}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public String update(@PathVariable Long id, @RequestBody String title) {
        try {
            Task task = taskList.modify(id, title);
            return task.getTitle();
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Not found task"
            );
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Unknown error"
            );
        }
    }

    @DeleteMapping("/{id:[0-9]+}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String delete(@PathVariable Long id) {
        if (taskList.remove(id)) {
            return "Deleted";
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Fail Deleted"
            );
        }
    }
}
