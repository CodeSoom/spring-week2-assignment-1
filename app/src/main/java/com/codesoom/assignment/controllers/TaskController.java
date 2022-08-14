package com.codesoom.assignment.controllers;

import com.codesoom.assignment.exceptions.DataNotFoundException;
import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.models.TaskDTO;
import com.codesoom.assignment.interfaces.CRUDInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import java.util.Collection;
import java.util.logging.Logger;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "http://localhost:3000")
public class TaskController {
    Logger logger = Logger.getLogger("TaskController");

    private final CRUDInterface<TaskDTO , Task , Long> service;

    public TaskController(CRUDInterface<TaskDTO , Task , Long> service){
        this.service = service;
    }

    @GetMapping
    public Collection<Task> searchTasks(){
        logger.info("[GET] 목록 얻기");
        return service.selectAll();
    }

    @GetMapping("/{taskId}")
    public Task searchTask(@PathVariable long taskId){
        logger.info("[GET] 상세 조회 : " + taskId);
        Task task = service.selectById(taskId);
        if(task == null){
            throw new DataNotFoundException(taskId);
        }
        return task;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody TaskDTO taskDTO){
        logger.info("[POST] 생성 : " + taskDTO);
        return service.insert(taskDTO);
    }

    @RequestMapping(path = "/{taskId}" , method = {RequestMethod.PUT , RequestMethod.PATCH})
    public Task updateTask(@PathVariable long taskId ,
                           @RequestBody TaskDTO taskDTO){
        logger.info("[PUT , PATCH] 상세 조회 : " + taskId);
        Task task = service.update(taskId , taskDTO);
        if(task == null){
            throw new DataNotFoundException(taskId);
        }
        return task;
    }

    @DeleteMapping(path = "/{taskId}")
    public ResponseEntity<Task> deleteTask(@PathVariable long taskId){
        logger.info("[DELETE] 삭제 : " + taskId);
        service.delete(taskId);
        return ResponseEntity.noContent().build();
    }
}
