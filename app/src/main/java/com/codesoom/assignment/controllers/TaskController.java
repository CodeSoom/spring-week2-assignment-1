package com.codesoom.assignment.controllers;

import com.codesoom.assignment.exception.TaskNotFoundException;
import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.repository.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {

    TaskRepository taskRepository = new TaskRepository();

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getTaskList() {
        return taskRepository.getTasks();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Task getTask(@PathVariable Long id) {
        Optional<Task> task = taskRepository.findTaskById(id);
        if (!task.isPresent()) {
            throw new TaskNotFoundException(Long.toString(id));
        }
        return task.get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody Task task) {
        taskRepository.createNewTaskId();
        Task newTask = new Task(taskRepository.getNewId(), task.getTitle());
        taskRepository.addTask(newTask);

        return newTask;
    }

    @RequestMapping(value = "{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    @ResponseStatus(HttpStatus.OK)
    public Task updateTask(@PathVariable Long id, @RequestBody Task requestTask) {
        Optional<Task> task = taskRepository.findTaskById(id);
        if (!task.isPresent()) {
            throw new TaskNotFoundException(Long.toString(id));
        }

        Task updateTask = new Task(task.get().getId(), requestTask.getTitle());
        taskRepository.removeTask(task.get());
        taskRepository.addTask(updateTask);
        return updateTask;
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void completeTask(@PathVariable Long id) {
        Optional<Task> task = taskRepository.findTaskById(id);
        if (!task.isPresent()) {
            throw new TaskNotFoundException(Long.toString(id));
        }
        taskRepository.removeTask(task.get());
    }

}