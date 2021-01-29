package com.codesoom.assignment.controllers;

import com.codesoom.assignment.NotFoundTaskIdException;
import com.codesoom.assignment.TaskRepository;
import com.codesoom.assignment.models.Task;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {

    TaskRepository taskRepository = new TaskRepository();

    @GetMapping
    public List<Task> getTaskList() {
        return new ArrayList<>(taskRepository.taskStore.values());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Task getTaskById(@PathVariable Long id) {
        return findTask(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task handleCreate(@RequestBody Task task) {
        task.setId(taskRepository.generateId());
        taskRepository.taskStore.put(task.getId(), task);
        return task;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Task handleUpdate(@PathVariable Long id, @RequestBody Task task) {
        findTask(id);
        Task updateTask = task;
        updateTask.setId(id);
        taskRepository.taskStore.replace(task.getId(), task);
        return task;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void handleDelete(@PathVariable Long id) {
        findTask(id);
        Task task = findTask(id);
        taskRepository.taskStore.remove(id);
    }

    public Task findTask(Long id) {
        if (taskRepository.taskStore.get(id) == null) {
            throw new NotFoundTaskIdException();
        }
        return taskRepository.taskStore.get(id);
    }

}
