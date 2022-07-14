package com.codesoom.assignment.controllers;

import com.codesoom.assignment.domain.ChangeTaskRequest;
import com.codesoom.assignment.domain.Task;
import com.codesoom.assignment.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * 입력 받은 숫자 타입의 taskId와 같은 id를 가진 Task를 조회해 리턴한다.
     *
     * @param taskId 입력 받은 숫자 타입 taskId
     * @return 입력 받은 taskId와 같은 id를 가진 Task 리턴
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
    @GetMapping("/all")
    public List<Task> getAll() {
        return taskService.getAll();
    }

    /**
     * 입력 받은 문자열 title을 받아 작업을 생성하고 리턴합니다.
     *
     * @param title 입력 받은 문자열 title
     * @return 생성한 작업을 리턴
     */
    @PostMapping
    public Task createTask(@RequestBody String title) {
        return taskService.createTask(title);
    }

    /**
     * 변경 정보를 담은 요청을 받아 작업을 변경하고 리턴합니다.
     *
     * @param request 변경 정보를 담은 request
     * @return 변경한 작업 리턴
     */
    @PutMapping
    public Task changeTask(@RequestBody ChangeTaskRequest request) {
        return taskService.modifyTask(request.getTaskId(), request.getTitle());
    }

    /**
     * 입력 받은 숫자 형식의 taskId와 같은 taskId를 가진 작업을 제거합니다.
     *
     * @param taskId 입력 받은 숫자 형식의 taskId
     */
    @DeleteMapping("/{taskId}")
    public void removeTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
    }
}
