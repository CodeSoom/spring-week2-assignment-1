package com.codesoom.assignment.controllers;

import com.codesoom.assignment.application.TaskService;
import com.codesoom.assignment.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {

    @Autowired
    private TaskService taskService;

    /**
     * GET /tasks
     * @return tasks
     */
    @GetMapping
    public List<Task> getLists() {
        return taskService.getTasks();
    }

    /**
     * GET /tasks/{id}
     * @param id
     * @return task
     */
    @GetMapping("/{id}")
    public Task getTask(@PathVariable(name = "id") Long id) {
        return taskService.getTask(id);
    }

    /**
     * POST /tasks
     * @param task
     * @return task
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createList(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    /**
     * PUT/PATCH /tasks/{id}
     * @param id
     * @param task
     * @return task
     */
    @RequestMapping(value = "/{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public Task updateTask(@PathVariable(name = "id") Long id, @RequestBody Task task) {
        return taskService.updateTask(id, task);
    }

    /**
     * TODO HttpStatus NO_CONTENT(204)에 대해 알아보기
     * DELETE /tasks/{id}
     * @param id
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable(name = "id") Long id) {
        taskService.deleteTask(id);
    }
}
