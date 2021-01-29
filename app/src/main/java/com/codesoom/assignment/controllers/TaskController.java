package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
class TaskController {
    private List<Task> tasks = new ArrayList<>();
    private Long newId = 0L;

    @GetMapping()
    public List<Task> getTasks() {
        return tasks;
    }

    @GetMapping("{taskId}")
    public ResponseEntity getTask(@PathVariable long taskId) {
        for(Task storedTask : tasks){
            if(storedTask.getId() == taskId){
                return new ResponseEntity(storedTask, HttpStatus.OK);
            }
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    public ResponseEntity create(@RequestBody Task task) {
        task.setId(generateId());
        tasks.add(task);

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity update(@PathVariable long taskId, @RequestBody Task task) {
        for(Task storedTask : tasks){
            if(storedTask.getId() == taskId){
                storedTask.setTitle(task.getTitle());
                return new ResponseEntity(HttpStatus.OK);
            }
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity delete(@PathVariable long taskId) {
        for(Task storedTask : tasks){
            if(storedTask.getId() == taskId){
                tasks.remove(storedTask);
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);

    }

    private long generateId() {
        newId += 1;
        return newId;
    }
}