package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.HashMap;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private HashMap<Long, String> tasks = new HashMap<>();
    private Long curTaskID = 0L;

    @GetMapping
    public HashMap<Long, String> list(){
        return tasks;
    }

    @GetMapping("/{TaskID}")
    public Task GetTask(@PathVariable("TaskID") Long id) throws IOException {

        if(tasks.containsKey(id)){
            Task task = new Task();
            task.setId(id);
            task.setTitle(tasks.get(id));
            return task;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid URL");
    }

    @PostMapping
    public Task create(@RequestBody Task task){
        task.setId(generateID());
        tasks.put(task.getId(), task.getTitle());
        return task;
    }

    private Long generateID(){
        curTaskID += 1;
        return curTaskID;
    }
    @PutMapping("/{TaskID}")
    public Task revised(@PathVariable("TaskID") Long id, @RequestBody Task task){

        if(tasks.containsKey(id)){
            task.setId(id);
            tasks.replace(id, task.getTitle());
            return task;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid URL");
    }
    @DeleteMapping("/{TaskID}")
    public void delete(@PathVariable("TaskID") Long id){
        if(tasks.containsKey(id)){
            tasks.remove(id);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid URL");
    }
}

