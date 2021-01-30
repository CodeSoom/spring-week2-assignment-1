package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.models.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Task API.
 *
 * @see Task
 */
@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TasksController {
    TaskRepository taskRepository = TaskRepository.instance();

    /**
     * Returns all tasks.
     */
    @GetMapping
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    /**
     * Returns one task.
     * If not exists, throw 404 status.
     *
     * @param id is target id.
     * @return target task.
     */
    @GetMapping("{id}")
    public Task findOne(@PathVariable int id) {
        return taskRepository.findOne(id);
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
        return taskRepository.insertOne(task.title());
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
    public Task modifyOne(@PathVariable int id, @RequestBody Task task) {
        return taskRepository.modifyOne(id, task.title());
    }

    /**
     * Delete a task.
     *
     * @param id is target id.
     * @return deleted task.
     */
    @DeleteMapping("{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Task deleteOne(@PathVariable int id) {
        return taskRepository.deleteOne(id);
    }
}
