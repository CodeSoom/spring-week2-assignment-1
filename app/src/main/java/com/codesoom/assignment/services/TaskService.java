package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;


public class TaskController {
    private final HashMap<Long, Task> tasks = new HashMap<>();
    private Long curTaskID = 0L;

    public Collection<Task> getAllTask(){
        List<Task> AllTasks = new ArrayList<>();

        for(Task item : tasks.values()){
            AllTasks.add(item);
        }
        return tasks.values();
        //return AllTasks;
    }

    public Task getKeyTask(Long id){
        if(tasks.containsKey(id)){
            return tasks.get(id);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid URL");
    }

    public Task addTask(Task task){
        task.setId(generateID());
        tasks.put(task.getId(), task);
        return task;
    }

    private Long generateID(){
        curTaskID += 1;
        return curTaskID;
    }

    public Task modifyTask(Long id, Task task){
        if(tasks.containsKey(id)) {
            task.setId(id);
            tasks.get(id).setTitle(task.getTitle());
            return task;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid URL");
    }

    public void removeTask(Long id){
        if(tasks.containsKey(id)){
            tasks.remove(id);
            return ;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid URL");
    }
}

