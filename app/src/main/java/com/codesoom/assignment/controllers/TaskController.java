package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.models.TaskDTO;
import com.codesoom.assignment.services.CRUDInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Collection;
import java.util.logging.Logger;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "http://localhost:3000")
public class TaskController {
    Logger logger = Logger.getLogger("TaskController");

    private final CRUDInterface<TaskDTO , Task> service;

    public TaskController(CRUDInterface<TaskDTO , Task> service){
        this.service = service;
    }

    @GetMapping
    public Collection<Task> searchTasks(){
        logger.info("[GET] 목록 얻기");
        return service.selectAll();
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> searchTask(@PathVariable long taskId){
        logger.info("[GET] 상세 조회 : " + taskId);
        Task task = service.selectById(taskId);
        if(task == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createTask(@RequestBody TaskDTO taskDTO){
        logger.info("[POST] 생성 : " + taskDTO);
        Task task = service.insert(taskDTO);
    }

    @RequestMapping(path = "/{taskId}" , method = {RequestMethod.PUT , RequestMethod.PATCH})
    public ResponseEntity<Task> updateTask(@PathVariable long taskId ,
                           @RequestBody TaskDTO taskDTO){
        logger.info("[PUT , PATCH] 상세 조회 : " + taskId);
        Task task = service.update(taskId , taskDTO);
        if(task == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{taskId}")
    public ResponseEntity<Task> deleteTask(@PathVariable long taskId){
        logger.info("[DELETE] 삭제 : " + taskId);
        Task task = service.delete(taskId);
        if(task == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(task, HttpStatus.OK);
    }
}
