package com.codesoom.assignment.controllers;

import com.codesoom.assignment.model.TaskRequest;
import com.codesoom.assignment.model.TaskResponse;
import com.codesoom.assignment.service.TaskService;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Task 기능에 대한 Rest API를 제공한다.
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
     * 할 일 목록을 리턴한다.
     * @return 할 일 목록
     */
    @GetMapping
    public ResponseEntity<List<TaskResponse>> getTaskList() {
        return new ResponseEntity<>(taskService.taskList(), HttpStatus.OK);
    }

    /**
     * id에 해당하는 할 일을 찾아서 리턴한다.
     * @param id
     * @return 할 일
     */
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTask(@PathVariable Long id) {
        return new ResponseEntity<>(taskService.getTask(id), HttpStatus.OK);
    }

    /**
     * 할 일을 추가 한다.
     * @param taskRequest
     * @return 추가된 할 일
     */
    @PostMapping
    public ResponseEntity<TaskResponse> addTask(@RequestBody TaskRequest taskRequest) {
        return new ResponseEntity<>(taskService.addTask(taskRequest), HttpStatus.CREATED);
    }

    /**
     * id에 해당하는 할 일을 수정 한다.
     * @param id
     * @param taskRequest
     * @return 수정된 할 일
     */
    @RequestMapping(value = "/{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<TaskResponse> modifyTask(@PathVariable Long id, @RequestBody TaskRequest taskRequest) {
        taskRequest.setId(id);
        return new ResponseEntity<>(taskService.modifyTask(taskRequest), HttpStatus.OK);
    }

    /**
     * 할 일을 삭제 한다.
     * @param id
     * @return HttpStatus
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<TaskResponse> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
