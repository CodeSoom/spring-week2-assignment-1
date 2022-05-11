package com.codesoom.assignment.controllers;

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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {
    private final static List<Task> tasks = new ArrayList<>();
    private static int id = 0;

    @GetMapping
    public ResponseEntity<List<Task>> taskList() {

        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Task> task(@PathVariable int id) {
        Optional<Task> findTask = tasks.stream().filter(x -> x.getId() == id).findFirst();

        if (findTask.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity<>(findTask.get(), HttpStatus.OK);
    }

    @PostMapping
    public Task addTask(@RequestBody Task task) {
        task.setId(getId());
        tasks.add(task);

        return task;
    }

    @RequestMapping(path = "/{id}", method = {RequestMethod.PATCH, RequestMethod.PUT})
    public ResponseEntity<Task> editTask(@PathVariable int id, @RequestBody Task task) {
        Optional<Task> findTask = tasks.stream().filter(x -> x.getId() == id).findFirst();

        if (findTask.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Task newTask = findTask.get();
        newTask.setTitle(task.getTitle());

        return new ResponseEntity<>(newTask, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteTask(@PathVariable int id) {
        Optional<Task> findTask = tasks.stream().filter(x -> x.getId() == id).findFirst();

        if (findTask.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        tasks.remove(findTask.get()); // get()으로 꼭 가져오기

        return ResponseEntity.noContent().build();
    }

    private int getId() {
        return ++id;
    }
}
