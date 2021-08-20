package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {
    private List<Task> tasks = new ArrayList<>();
    private Long newId = 0L;

    @GetMapping
    public List<Task> list(){
        return tasks;
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public Task create(@RequestBody Task task){
        task.setId(generateId());
        tasks.add(task);
        return task;
    }

    private Long generateId(){
        newId += 1;
        return newId;
    }

    @PutMapping("/{id}")
    public Task modify(@PathVariable Long id, @RequestBody Task source){
        Task task = findTaskById(id);
        task.setTitle(source.getTitle());
        return task;
    }

    private Task findTaskById(Long id){
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    //Todo
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){

    }
}
