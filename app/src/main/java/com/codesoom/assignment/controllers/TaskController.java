//todo
//1.Read Collection - GET /tasks => 완료
//2.Read Item - GET /tasks/{id} => 완료
//3.Create - POST /tasks => 완료
//4.Update - PUT/PATCH /tasks/{id} => 완료
//5.Delete - DELETE /tasks/{id} => 완료


package com.codesoom.assignment.controllers;
import com.codesoom.assignment.TaskNotFoundException;
import com.codesoom.assignment.application.TaskService;
import com.codesoom.assignment.models.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {
    private TaskService taskService;

    public TaskController(){
        taskService = new TaskService();
    }

    @GetMapping
    public List<Task> list(){

        return taskService.getTasks();
    }

    @GetMapping("{id}")
    public Task detail(@PathVariable Long id){
       return taskService.getTask(id);
    }

    @PostMapping
    public Task create(@RequestBody Task task) {
        return taskService.createTask(task);

    }

    @PatchMapping("{id}")
    public Task patch(@PathVariable Long id,@RequestBody Task source){
        return taskService.updateTask(id,source);
    }

    @PutMapping("{id}")
    public Task update(@PathVariable Long id,@RequestBody Task source){
        return taskService.updateTask(id,source);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        taskService.deleteTask(id);

    }

}
