package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.models.TaskDTO;
import com.codesoom.assignment.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "http://localhost:3000")
public class TaskController {
    Logger logger = Logger.getLogger("TaskController");

    private final TaskService service;

    public TaskController(TaskService service){
        this.service = service;
    }

    @GetMapping
    public List<Task> searchTasks(){
        logger.info("[GET] 목록 얻기");
        return service.getAllTask();
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> searchTask(@PathVariable long taskId){
        logger.info("[GET] 상세 조회 : " + taskId);
        Optional<Task> task = service.getTaskById(taskId);
//        if(task.isPresent()){
//            return new ResponseEntity<>(task.get(), HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        자동완성
        return task.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createTask(@RequestBody TaskDTO taskDTO){
        logger.info("[POST] 생성 : " + taskDTO);
        service.createTask(taskDTO);
    }

    @RequestMapping(path = "/{taskId}" , method = {RequestMethod.PUT , RequestMethod.PATCH})
    public ResponseEntity<Task> updateTask(@PathVariable long taskId ,
                           @RequestBody TaskDTO taskDTO){
        logger.info("[PUT , PATCH] 상세 조회 : " + taskId);
        Optional<Task> task = service.updateTask(taskId , taskDTO);
        return task.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(path = "/{taskId}")
    public ResponseEntity<Task> deleteTask(@PathVariable long taskId){
        logger.info("[DELETE] 삭제 : " + taskId);
        Optional<Task> task = service.deleteTask(taskId);
        return task.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
