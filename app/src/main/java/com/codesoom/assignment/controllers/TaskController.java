package com.codesoom.assignment.controllers;

import com.codesoom.assignment.domain.Task;
import com.codesoom.assignment.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
