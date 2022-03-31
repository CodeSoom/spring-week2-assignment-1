package com.codesoom.assignment.controllers;

import com.codesoom.assignment.application.TaskService;
import com.codesoom.assignment.dto.TaskDto;
import com.codesoom.assignment.models.Task;

import org.springframework.http.HttpStatus;
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

import javax.validation.Valid;
import java.util.List;

/**
 * {@link Task} 객체를 생성, 조회, 수정, 삭제 하는 Controller
 * @see RestController
 * @see CrossOrigin
 * @see HttpStatus
 * @see ResponseStatus
 * @see PathVariable
 * @see RequestMapping
 * @see RequestBody
 */
@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {
    private final TaskService taskService;

    /**
     * <p>Constructor based Injection</p>
     * @param taskService {@link TaskService}
     */
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * 모든 {@link Task} 객체를 조회하는 API
     * @return 배열의 Task객체를 반환
     * @see List
     * @see java.util.ArrayList
     */
    @GetMapping(path = "")
    @ResponseStatus(HttpStatus.OK)
    private List<Task> readAll() {
        return this.taskService.getTasks();
    }

    /**
     * Param인 id에 해당하는 {@link Task} 객체 조회 API
     * @param id Task 객체의 id
     * @return 조회한 {@link Task} 객체 반환
     */
    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    private Task readOne(@PathVariable("id") Long id) {
        return this.taskService.getTask(id);
    }

    /**
     * {@code Task} 객체를 생성하는 API
     * @param taskDto RequestBody 를 통해 Request의 Body에 있는 데이터를 {@link TaskDto} 객체로 바인딩
     * @return 생성된 {@code Task} 객체를 반환
     * @see Valid
     */
    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    private Task create(@Valid @RequestBody TaskDto taskDto) {
        return this.taskService.createNewTask(taskDto);
    }

    /**
     * Task 객체를 변경하는 API
     * 업데이트 하는 PUT 과 PATCH RequestMethod를 하나의 API 로 통합하여 관리
     * @param id {@link Task} 객체의 id
     * @param taskDto RequestBody 를 통해 Request의 Body에 있는 데이터를 {@link TaskDto} 객체로 바인딩
     * @return 업데이트 된 {@code Task} 객체를 반환
     */
    @RequestMapping(path = "/{id}", method = { RequestMethod.PUT, RequestMethod.PATCH })
    @ResponseStatus(HttpStatus.OK)
    private Task update(@PathVariable("id") Long id, @RequestBody TaskDto taskDto) {
        taskDto.setId(id);
        return this.taskService.updateTaskById(taskDto);
    }

    /**
     * Task 객체를 삭제하는 API
     * @param id {@link Task} 객체의 id
     * @return {@code void}
     */
    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void remove(@PathVariable("id") Long id) {
        this.taskService.deleteTaskById(id);
    }
}
