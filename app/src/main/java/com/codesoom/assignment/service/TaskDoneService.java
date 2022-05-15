package com.codesoom.assignment.service;

import com.codesoom.assignment.exception.TaskNotFoundException;
import com.codesoom.assignment.repository.TaskRepository;
import org.springframework.stereotype.Service;

/**
 * Task를 끝냈을 때 TaskRepository에 저장된 Task를 제거하는 서비스 클레스 입니다
 */
@Service
public class TaskDoneService {

    private final TaskRepository taskRepository;

    public TaskDoneService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void done(Long id) {
        taskRepository.findById(id).orElseThrow(TaskNotFoundException::new);
        taskRepository.delete(id);
    }
}
