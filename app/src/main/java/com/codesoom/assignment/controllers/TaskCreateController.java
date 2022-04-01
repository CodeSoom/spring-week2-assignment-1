package com.codesoom.assignment.controllers;

import com.codesoom.assignment.domains.Task;
import com.codesoom.assignment.domains.TaskDto;
import com.codesoom.assignment.services.TaskCreateService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


/**
 * 할 일 생성 요청과 매핑됩니다.
 */
@CrossOrigin
@RequestMapping("/tasks")
@RestController
public class TaskCreateController {

    private final TaskCreateService service;

    public TaskCreateController(TaskCreateService service) {
        this.service = service;
    }

    /**
     * 입력받은 정보로 할 일을 저장합니다.
     *
     * @param taskDto 사용자가 입력한 할 일 정보
     * @return 저장된 할 일
     */
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    public Task addTask(@RequestBody TaskDto taskDto) {
        return service.addTask(taskDto);
    }

}
