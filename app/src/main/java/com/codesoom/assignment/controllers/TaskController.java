package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import org.checkerframework.checker.units.qual.A;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {

    private List<Task> tasks = new ArrayList<>();

    private HashMap<Long, Task> taskHashMap = new HashMap<Long, Task>();

    private Long newId = 0L;

    @GetMapping
    public List<Task> getTasks(){

        List<Task> taskList = new ArrayList<>(taskHashMap.values());

        return taskList;
    }

    @GetMapping("/taskId")
    public Task getTask(@PathVariable Long taskId){

        return taskHashMap.get(taskId);
    }


    @PostMapping
    public Task addTask(@RequestBody Task task){

        Long taskId = generateId();

        task.setId(taskId);

        taskHashMap.put(taskId, task);

        return task;
    }

    @PatchMapping("/{taskId}")
    public void modifyTask(@RequestBody Task task, @PathVariable Long taskId){
        taskHashMap.get(taskId).setTitle(task.getTitle());
    }

    @DeleteMapping("/{taskId}")
    public void deleteTask(@PathVariable Long taskId){
        taskHashMap.remove(taskId);
    }

    private Long generateId() {
        return newId++;
    }
}
