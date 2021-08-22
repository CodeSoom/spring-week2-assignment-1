package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * HTTP 프로토콜을 통한 Task Entity를 처리하는 컨트롤을 담당합니다.
 */
@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping
    public List<Task> list(){
        return taskService.findAll();
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public Task create(@RequestBody Task task){
        return taskService.create(task);
    }

    @PutMapping("/{id}")
    public Task update(@PathVariable Long id, @RequestBody Task source){
        return taskService.update(id, source);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id){
        return taskService.delete(id);
    }
}
