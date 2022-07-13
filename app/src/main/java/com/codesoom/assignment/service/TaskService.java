package com.codesoom.assignment.service;

import com.codesoom.assignment.domain.Task;
import com.codesoom.assignment.exception.TaskNotFoundException;
import com.codesoom.assignment.repository.TaskMapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Task와 관련된 로직을 수행하는 서비스입니다.
 */
@Service
public class TaskService {
    private final TaskMapRepository taskRepository;

    @Autowired
    public TaskService(TaskMapRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * 입력 받은 숫자 형식의 taskId와 같은 id를 가진 요소가 존재하면 요소를 리턴, 존재하지 않으면 예외를 던집니다.
     *
     * @param taskId 입력 받은 숫자 형식의 taskId
     * @return taskId와 같은 id를 가진 요소가 존재하면 요소를 리턴, 존재하지 않으면 예외
     * @throws TaskNotFoundException Task가 존재하지 않으면 던집니다.
     */
    public Task getTask(Long taskId) {
        return taskRepository.get(taskId)
                .orElseThrow(TaskNotFoundException::new);
    }

    public List<Task> getAll() {
        return taskRepository.getAll();
    }

    public Task createTask(String title) {
        return taskRepository.add(title);
    }

    /**
     * 입력 받은 숫자 형식의 taskId와 같은 id를 가진 Task가 있으면 입력 받은 문자열 title로 바꾸고 바뀐 요소를 리턴한다.
     *
     * @param taskId 입력 받은 숫자 타입의 taskId
     * @param title 입력 받은 문자열 타입의 title
     * @return 바뀐 Task 리턴
     */
    public Task modifyTask(Long taskId, String title) {
        return getTask(taskId).changeTitle(title);
    }

    public void deleteTask(Long taskId) {
        taskRepository.remove(taskId);
    }
}
