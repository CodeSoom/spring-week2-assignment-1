package com.codesoom.assignment.controllers;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.service.TaskService;
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

import java.util.Collection;


/**
 * http 요청에 따라 할 일들을 관리합니다.
 */
@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * 등록된 모든 할 일을 반환 합니다.
     * @return 등록된 모든 할 일
     */
    @GetMapping
    public Collection<Task> getTasks() {
        return taskService.getTasks();
    }

    /**
     * 요청된 id에 해당하는 할 일 반환합니다.
     * @param id
     * @return 할 일
     */
    @GetMapping("/{id}")
    public Task getTask(@PathVariable("id") Long id) {
        return taskService.getTask(id).orElseThrow();
    }

    /**
     * 할 일을 생성합니다.
     * @param task
     * @return 생성 된 할일
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task saveTask(@RequestBody Task task) {
        return taskService.saveTask(task);
    }

    /**
     * 요청된 id에 해당하는 할 일을 수정 후 반환합니다.
     * @param id
     * @param task
     * @return 수정 된 할 일
     */
    @RequestMapping(path = "/{id}", method = {RequestMethod.PATCH, RequestMethod.PUT})
    public Task updateTask(@PathVariable("id") Long id, @RequestBody Task task) {
        return taskService.updateTask(id, task).orElseThrow();
    }

    /**
     * 요청된 id에 해당하는 할 일을 삭제합니다.
     * @param id
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Task deleteTask(@PathVariable("id") Long id) {
        return  taskService.deleteTask(id).orElseThrow();
    }


}
