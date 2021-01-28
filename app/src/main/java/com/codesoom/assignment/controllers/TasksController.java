package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.models.TaskManager;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Task API.
 */
@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TasksController {
    TaskManager taskManager = TaskManager.getInstance();

    /**
     * Returns all tasks.
     */
    @GetMapping
    public List<Task> findAll() {
        return taskManager.findAll();
    }

    /**
     * Returns one task.
     * If not exists, throw 404 status.
     *
     * @param id is target id.
     * @return target task.
     */
    @GetMapping("{id}")
    public Task findOne(@PathVariable long id) {
        return taskManager.findOne(id);
    }

    /**
     * Insert a task.
     *
     * @param task is want to insert task.
     * @return inserted task.
     */
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Task insertOne(@RequestBody Task task) {
        return taskManager.insertOne(task.title());
    }

    /**
     * Modify a task.
     *
     * @param id   is target task id.
     * @param task is want to modify values has task.
     * @return modified task.
     */
    @RequestMapping(
            value = "{id}",
            method = {RequestMethod.PUT, RequestMethod.PATCH}
    )
    public Task modifyOne(@PathVariable long id, @RequestBody Task task) {
        return taskManager.modifyOne(id, task.title());
    }

    /**
     * Delete a task.
     *
     * @param id is target id.
     * @return deleted task.
     */
    @DeleteMapping("{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Task deleteOne(@PathVariable long id) {
        return taskManager.deleteOne(id);
    }
}
