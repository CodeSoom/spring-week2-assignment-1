package com.codesoom.assignment.controllers;

import com.codesoom.assignment.model.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//TODO
//1. Read Collection - GET/tasks
//2. Read Item - GET/tasks/{id}
//3. Create - POST/tasks
//4. Update - PUT/PATCH /tasks/{id}
//5. Delete - DELETE /tasks/{id}
@RestController
@RequestMapping("/tasks")
public class TaskController {
    private List<Task> tasks = new ArrayList<>();
    private Long newId = 0L;

    @GetMapping
    public List<Task> list(){

        return tasks;
    }
    @PostMapping
    public Task create(@RequestBody Task task){
        task.setId(generateId());
        tasks.add(task);

        return task;
    }
    @GetMapping("/{id}")
    public Task view(@PathVariable Long id){
        Task task = findTask(id);

        return task;
    }

    @RequestMapping(method = {RequestMethod.PUT,RequestMethod.PATCH}, value = "/{id}")
    public Task update(@RequestBody Task body, @PathVariable Long id) {
        Task task = findTask(id);
        task.setTitle(body.getTitle());
        return task;
    }

    private Long generateId(){
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
