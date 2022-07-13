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

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * Get 요청시 path에 taskId가 포함되어 있으면 서비스에서 taskId와 id가 같은 Task를 리턴한다.
     *
     * @param taskId 입력 받은 Long 타입의 taskId
     * @return 입력 받은 taskId와 같은 id를 가진 Task 리턴
     */
    @GetMapping("/{taskId}")
    public Task getTask(@PathVariable Long taskId) {
        return taskService.getTask(taskId);
    }

    /**
     * 저장소에 저장된 모든 요소들을 컬렉션 타입으로 리턴합니다.
     *
     * @return 저장소에 저장된 컬렉션 타입의 요소
     */
    @GetMapping("/all")
    public List<Task> getAll() {
        return taskService.getAll();
    }

    /**
     * 입력 받은 문자열 title을 가진 요소를 만들어 리턴합니다.
     *
     * @param title 입력 받은 문자열 title
     * @return 생성된 요소를 리턴
     */
    @PostMapping
    public Task createTask(@RequestBody String title) {
        return taskService.createTask(title);
    }

    /**
     * 입력 받은 변경 정보를 가지고 있는 객체를 받아 작업을 변경하고 변경한 작업을 리턴합니다.
     *
     * @param request 입력받은 변경 정보 request
     * @return 변경된 작업 리턴
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
