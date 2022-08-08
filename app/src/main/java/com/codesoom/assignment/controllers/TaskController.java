package com.codesoom.assignment.controllers;

import org.springframework.web.bind.annotation.*;


import java.util.logging.Logger;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "http://localhost:3000")
public class TaskController {
    Logger logger = Logger.getLogger("TaskController");

    @GetMapping
    public String searchTasks(){
        logger.info("[GET] 목록 얻기");
        return "";
    }

    @GetMapping("/{taskId}")
    public String searchTask(@PathVariable long taskId){
        logger.info("[GET] 상세 조회 : " + taskId);
        return "";
    }

    @PostMapping
    public String createTask(){
        logger.info("[POST] 생성");
        return "";
    }

    @RequestMapping(path = "/{taskId}" , method = {RequestMethod.PUT , RequestMethod.PATCH})
    public String updateTask(@PathVariable long taskId){
        logger.info("[PUT , PATCH] 상세 조회 : " + taskId);
        return "";
    }

    @DeleteMapping(path = "/{taskId}")
    public void deleteTask(@PathVariable long taskId){
        logger.info("[DELETE] 삭제 : " + taskId);
    }
}
