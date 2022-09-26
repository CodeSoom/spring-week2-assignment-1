package com.codesoom.assignment.controllers;


import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.repository.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
 * Task Controller mapping for /tasks
 */
@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * Returns all lists of tasks from the database.
     * @return the List of tasks
     */
    @GetMapping
    public List<Task> list() {
        return taskService.findAllTasks();
    }

    /**
     *
     * Returns ResponseEntity with the found task.
     * @param id from URI parameter
     * @return Search results
     */
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskId(@PathVariable Long id) {
        return taskService.findTaskId(id);
    }

    /**
     * returns a newly created Task.
     * @param task from request body
     * @return result of the created task
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public synchronized Task create(@RequestBody Task task) {
        return taskService.createTask(task).get();
    }

    /**
     * returns ResponseEntity with the updated task in the database.
     * @param id from URI parameter
     * @param body from request body
     * @return result of the updated task
     */
    @RequestMapping(value = "{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task body) {
        return taskService.updateTask(id, body);

    }

    /**
     * returns ResponseEntity with the deleted task in the database.
     * @param id from URI parameter
     * @return result of the deleted task
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable Long id) {
        return taskService.deleteTask(id);
    }


}
