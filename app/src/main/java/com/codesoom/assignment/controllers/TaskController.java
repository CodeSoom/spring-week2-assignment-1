package com.codesoom.assignment.controllers;

import com.codesoom.assignment.domain.ChangeTaskRequest;
import com.codesoom.assignment.domain.Task;
import com.codesoom.assignment.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST를 관리하며 비즈니스 로직의 인터페이스 역할을 한다.
 * 외부에서 사용할 수 있도록 작업과 관련한 기능을 노출할 책임을 가지고 있다.
 */
@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * 주어진 식별자를 갖는 작업을 찾아 리턴한다.
     *
     * @param taskId 작업의 식별자
     * @return 작업
     */
    @GetMapping("/{taskId}")
    public Task getTask(@PathVariable Long taskId) {
        return taskService.getTask(taskId);
    }

    /**
     * 저장된 모든 작업 목록을 리턴합니다.
     *
     * @return 저장된 모든 작업 목록 리턴
     */
    @GetMapping
    public List<Task> getAll() {
        return taskService.getAll();
    }

    /**
     * 입력 받은 문자열 title을 받아 작업을 생성하고 리턴합니다.
     *
     * @param title 입력 받은 문자열 title
     * @return 생성한 작업을 리턴
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Task createTask(@RequestBody String title) {
        return taskService.createTask(title);
    }

    /**
     * 변경 정보를 담은 요청을 받아 작업을 변경하고 리턴합니다.
     *
     * @param id 식별자
     * @param request 변경 정보를 담은 request
     * @return 변경한 작업 리턴
     */
    @PutMapping("/{id}")
    public Task changeTask(@PathVariable("id") Long id, @RequestBody ChangeTaskRequest request) {
        return taskService.modifyTask(id, request.getTitle());
    }

    /**
     * 입력 받은 숫자 형식의 taskId와 같은 taskId를 가진 작업을 제거합니다.
     *
     * @param taskId 입력 받은 숫자 형식의 taskId
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{taskId}")
    public void removeTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
    }
}
