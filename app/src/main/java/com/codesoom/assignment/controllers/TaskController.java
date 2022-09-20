package com.codesoom.assignment.controllers;


import com.codesoom.assignment.models.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    //    private List<Task> tasks = new ArrayList<>(1000);
    private ConcurrentHashMap<Long, Task> tasksHash = new ConcurrentHashMap<>();
    private Long newId = 0L;

    @GetMapping
    public List<Task> list() {
        return new ArrayList<>(tasksHash.values());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskId(@PathVariable Long id) {
        Task task = tasksHash.getOrDefault(id, null);

        if (task == null) {
            return new ResponseEntity<>(new Task(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(task, HttpStatus.OK);


//        for (Task task : tasks) {
//            if (task.getId() == id) {
//                return new ResponseEntity<>(task, HttpStatus.OK);
//            }
//        }
//        return new ResponseEntity<>(new Task(), HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public synchronized Task create(@RequestBody Task task) {

        task.setId(generateId());
//        tasks.add(task);
        tasksHash.put(task.getId(), task);

        return task;
    }


    @RequestMapping(value = "{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task body) {
        if (tasksHash.containsKey(id)) {
            Task task = tasksHash.get(id);
            task.setTitle(body.getTitle());

            return new ResponseEntity<>(task, HttpStatus.OK);
        }

        return new ResponseEntity<>(new Task(), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable Long id) {

//        for (Task task : tasks) {
//            if (task.getId() == id) {
//                tasks.remove(task);
//                return new ResponseEntity<>(task, HttpStatus.NO_CONTENT);
//            }
//        }

        if (tasksHash.containsKey(id)) {
            tasksHash.remove(id);
            return new ResponseEntity<>(tasksHash.get(id), HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(new Task(), HttpStatus.NOT_FOUND);


    }

    private synchronized Long generateId() {
        newId += 1;
        return newId;
    }
}
