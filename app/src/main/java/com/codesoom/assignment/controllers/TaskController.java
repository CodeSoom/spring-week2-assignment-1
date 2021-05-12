package com.codesoom.assignment.controllers;

import com.codesoom.assignment.dto.Task;
import com.codesoom.assignment.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * A Controller map requests to proper method of Repository.
 */
@RestController
@RequestMapping(path = "/tasks")
public class TaskController {

    private final TaskRepository taskRepository;
    private Long currentTaskIdx = 0L;

    @Autowired
    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * Returns all tasks in TaskRepository.
     *
     * @return the list of Task instances.
     */
    @GetMapping
    public List<Task> getAllTasks() {
        return this.taskRepository.findAll();
    }

    /**
     * Returns a created Task instance.
     *
     * @param task A Task instance is consist of request's content.
     * @return the Task instance which is created by TaskRepository.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody Task task) {
        task.setId(generateId());
        return this.taskRepository.addTask(task);
    }

    /**
     *  Returns a Task instance which is pointed by id.
     *
     * @param id An id of a Task instance which client wants to get.
     * @return the Task instance which is pointed.
     */
    @GetMapping(path = "/{id}")
    public Task getTask(@PathVariable Long id) {
        return this.taskRepository.getOneTask(id);
    }

    /**
     * Sets the whole properties of a Task instance which is pointed by id.
     *
     * @param id An id of a Task instance which client wants to set.
     * @param task A Task instance is consist of request's content.
     * @return the set Task instance.
     */
    @PutMapping(path = "/{id}")
    public Task setTask(@PathVariable Long id, @RequestBody Task task) {
        task.setId(id);
        return this.taskRepository.setOneTask(task);
    }

    /**
     * Updates partial information of a Task instance which is pointed by id.
     *
     * @param id An id of a Task instance which client wants to update.
     * @param task A Task instance is consist of request's content.
     * @return the updated Task instance.
     */
    @PatchMapping(path = "/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task task) {
        task.setId(id);
        return this.taskRepository.updateOneTask(task);
    }

    /**
     * Deletes a Task instance in TaskRepository.
     *
     * @param id An id of a Task instance which client wants to delete.
     */
    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeTask(@PathVariable Long id) {
        this.taskRepository.removeOneTask(id);
    }

    private synchronized Long generateId() {
        this.currentTaskIdx += 1;
        return this.currentTaskIdx;
    }
}
