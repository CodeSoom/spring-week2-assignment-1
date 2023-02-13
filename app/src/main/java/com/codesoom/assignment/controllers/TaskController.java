package com.codesoom.assignment.controllers;

import com.codesoom.assignment.model.Task;
import com.codesoom.assignment.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 1. 목록 얻기 - GET /tasks
 * 2. 상세 조회 얻기 - GET /tasks/{id}
 * 3. 생성하기 - POST /tasks
 * 4. 제목 수정하기 - PUT/PATCH /tasks/{id}
 * 5. 삭제하기 - DELETE /tasks/{id}
 * */
@RestController
@CrossOrigin
@RequestMapping("/tasks")
public class TaskController {
    private TaskService taskService;

    @GetMapping
    public List<Task> getTasks(){
        return taskService.getTasks();
    }

    @GetMapping("/{id}")
    public Task getTask(@PathVariable Long id){
        return taskService.getTask(id);
    }

    @PostMapping
    public Task createTask(@RequestBody Task task){
        return taskService.createTask(task);
    }

    @RequestMapping(path = "/{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public Task updateTask(@PathVariable Long id, @RequestBody Task task){
        task.setId(id);
        return taskService.updateTask(task);
    }

    @DeleteMapping("/{id}")
    public Task deleteTask(@PathVariable Long id){
        return taskService.deleteTask(id);
    }
}
