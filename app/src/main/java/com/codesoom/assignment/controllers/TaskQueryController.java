package com.codesoom.assignment.controllers;

import com.codesoom.assignment.domain.Task;
import com.codesoom.assignment.repository.TaskRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 할 일의 조회 처리를 담당합니다.
 */
@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskQueryController {

    private final TaskRepository taskRepository;

    public TaskQueryController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * 저장된 모든 할 일을 조회합니다.
     * 요청 작업이 성공하면 200번, 실패하면 404번을 응답 코드로 함께 반환합니다.
     *
     * @return 할 일 전체 목록
     */
    @GetMapping
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    /**
     * 아이디에 해당하는 할 일을 찾아 조회합니다.
     * 요청 작업이 성공하면 200번, 실패하면 404번을 응답 코드로 함께 반환합니다.
     *
     * @param id 찾을 할 일 아이디
     * @throws IllegalArgumentException 할 일을 찾지 못한 경우
     * @return 아이디로 찾은 할 일
     */
    @GetMapping("/{id}")
    public Task getTask(@PathVariable Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(id + "에 해당하는 할 일을 찾지 못했습니다."));
    }
}
