package com.codesoom.assignment.controllers;

import com.codesoom.assignment.exceptions.TaskNotFoundException;
import com.codesoom.assignment.models.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {
    private final static List<Task> tasks = new ArrayList<>();
    private static int id = 0;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Task> taskList() {

        return tasks;
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Task task(@PathVariable int id) {
        Optional<Task> findTask = findTask(id);

        if (findTask.isEmpty()) {
            throw new TaskNotFoundException();
        }

        return findTask.get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task addTask(@RequestBody Task task) {
        task.setId(getId());
        tasks.add(task);

        return task;
    }

    @RequestMapping(path = "/{id}", method = {RequestMethod.PATCH, RequestMethod.PUT})
    @ResponseStatus(HttpStatus.OK)
    public Task editTask(@PathVariable int id, @RequestBody Task task) {
        Optional<Task> findTask = findTask(id);

        if (findTask.isEmpty()) {
            throw new TaskNotFoundException();
        }

        Task newTask = findTask.get();
        newTask.setTitle(task.getTitle());

        return newTask;
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable int id) {
        Optional<Task> findTask = findTask(id);

        if (findTask.isEmpty()) {
            throw new TaskNotFoundException();
        }

        tasks.remove(findTask.get()); // get()으로 꼭 가져오기

    }

    private Optional<Task> findTask(int id) {
        Optional<Task> task = tasks.stream().filter(x -> x.getId() == id).findFirst();
        return task;
    }

    private int getId() {
        int newId = id + 1;

        return newId;
    }
}
