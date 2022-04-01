package com.codesoom.assignment.controllers;

import com.codesoom.assignment.domains.Task;
import com.codesoom.assignment.domains.TaskDto;
import com.codesoom.assignment.services.TaskUpdateService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * 할 일 수정 요청과 매핑됩니다.
 */
@CrossOrigin
@RequestMapping("/tasks")
@RestController
public class TaskUpdateController {

    private final TaskUpdateService service;

    public TaskUpdateController(TaskUpdateService service) {
        this.service = service;
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public Task updateTask(@PathVariable(name = "id") Long id,
                           @RequestBody TaskDto taskDto) {
        return service.updateTaskById(id, taskDto);
    }

}
