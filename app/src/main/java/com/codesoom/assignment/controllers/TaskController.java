package com.codesoom.assignment.controllers;

// TODO
// 1. Read Collection - GET /tasks
// 2. Read Item - GET /tasks/{id}
// 3. Create - POST /tasks
// 4. Update - PUT/PATCH /tasks/{id}
// 5. Delete - DELETE /tasks/{id}

import com.codesoom.assignment.exception.TaskNotFoundException;
import com.codesoom.assignment.models.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {

    private static final ArrayList<Task> tasks = new ArrayList<>();
    private Long newId = 0L;

    @GetMapping
    public List<Task> List(){
        return tasks;
    }

    @GetMapping("/{id}")
    public Task ListById(@PathVariable Long id){
        Optional<Task> selectedTask = tasks.stream().filter(task -> task.getId() == id).findAny();
        return selectedTask.orElse(null);
    }

    @PostMapping
    public List<Task> create(@RequestBody Task task){
        Task newTask = new Task(generateId(), task.getTitle());
        tasks.add(newTask);

        return tasks;
    }

    @PutMapping("/{id}")
    public List<Task> update(@RequestBody Task updatedTask, @PathVariable Long id){

        tasks.stream().forEach(task -> {
            if(task.getId() == id){
                task.updateTitle(updatedTask.getTitle());
            }
        });
        return tasks;
    }

    @DeleteMapping("/{id}")
    public List<Task> delete(@PathVariable Long id){
        Task task = tasks.stream().filter(t -> t.getId() == id).findAny().orElseThrow(()->new TaskNotFoundException(id));
        tasks.remove(task);
        return tasks;
    }

    private Long generateId(){
        newId += 1;
        return newId;
    }


}
