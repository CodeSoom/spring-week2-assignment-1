package com.codesoom.assignment.controllers;

import com.codesoom.assignment.domains.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {
    private List<Task> tasks = new ArrayList<>();

    @GetMapping()
    public List<Task> tasks(){
        return tasks;
    }

    @GetMapping("{id}")
    public Task taskDetail(@PathVariable("id") int id){
        return getTask(id);
    }

    @PostMapping()
    public Task createTask(@RequestBody Task requestTask){
        requestTask.setId(getNewId());
        tasks.add(requestTask);
        sortTasks();
        return requestTask;
    }

    @PutMapping("{id}")
    public Task updateTask(@PathVariable("id") int id, @RequestBody Task requestTask){
        Task task = getTask(id);
        if(task != null){
            task.setTitle(requestTask.getTitle());
        }
        return task;
    }

    @PatchMapping("{id}")
    public Task updateTask2(@PathVariable("id") int id, @RequestBody Task requestTask){
        Task task = getTask(id);
        if(task != null){
            task.setTitle(requestTask.getTitle());
        }
        return task;
    }

    @DeleteMapping("{id}")
    public void deleteTask(@PathVariable("id") int id){
        tasks.removeIf(obj -> obj.getId() == id);
        sortTasks();
    }

    public Task getTask(int id){
        return tasks.stream().filter(obj -> obj.getId() == id).findFirst().orElse(null);
    }

    public int getNewId(){
        int newId = 1;
        if(tasks.size() != 0){
            newId = tasks.get(tasks.size()-1).getId()+1;
        }
        return newId;
    }

    public void sortTasks(){
        tasks.sort((o1, o2) -> o1.getId().compareTo(o2.getId()));
    }

}
