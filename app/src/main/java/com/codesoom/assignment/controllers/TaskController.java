package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private List<Task> tasks = new ArrayList<>();
    private Long newId = 0L;

    @GetMapping
    public List<Task> list(){
        return tasks;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> oneId(@PathVariable("id") Long id){
        Task task = findTask(id);
        if(task == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(task, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Task> create(@RequestBody Task task){
        task.setId(generateId());
        tasks.add(task);

        return new ResponseEntity(task, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Task> patchUpdate(@PathVariable("id") Long id, @RequestBody Task task){
        Task findedtask = findTask(id);
        if(findedtask == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        findedtask.setTitle(task.getTitle());

        return new ResponseEntity(findedtask, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> putUpdate(@PathVariable("id") Long id, @RequestBody Task task){
        Task findedtask = findTask(id);
        if(findedtask == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        findedtask.setTitle(task.getTitle());

        return new ResponseEntity(findedtask, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        Task findedtask = findTask(id);
        if(findedtask == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        tasks.remove(findedtask);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    private Long generateId() {
        newId += 1;
        return newId;
    }

    private Task findTask(Long id){
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
