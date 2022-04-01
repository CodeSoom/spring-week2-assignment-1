package com.codesoom.assignment.controllers;


import com.codesoom.assignment.services.TaskDeleteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


/**
 * 할 일 삭제 요청과 매핑됩니다.
 */
@CrossOrigin
@RequestMapping("/tasks")
@RestController
public class TaskDeleteController {

    private final TaskDeleteService service;

    public TaskDeleteController(TaskDeleteService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable(name = "id") Long id) {
        service.deleteTaskById(id);
    }

}
