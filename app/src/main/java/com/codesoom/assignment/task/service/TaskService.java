package com.codesoom.assignment.task.service;

import com.codesoom.assignment.task.domain.Task;
import com.codesoom.assignment.task.controller.dto.request.TaskRequestDto;

import java.util.List;

public interface TaskService {
    /**
     * 할 일의 총 목록을 반환합니다.
     *
     * @return 할 일의 총 목록 반환
     */
    List<Task> getTasks();

    /**
     * 할 일의 상세 정보를 반환합니다.
     *
     * @param id 반환할 기존 할 일의 고유 id
     * @return id에 대한 할 일의 상세 정보 반환
     */
    Task getTaskById(Long id);

    /**
     * 할 일을 생성합니다.
     *
     * @param taskRequestDto 생성할 할 일의 정보
     * @return 생성된 할 일 반환
     */
    Task createTask(TaskRequestDto taskRequestDto);

    /**
     * 기존 할 일에 대한 정보를 수정합니다.
     *
     * @param id             수정할 기존 할 일의 고유 id
     * @param taskRequestDto 수정할 할 일의 정보
     * @return 수정된 할 일 반환
     */
    Task updateTask(Long id, TaskRequestDto taskRequestDto);

    /**
     * 기존 할 일에 대한 정보를 삭제합니다.
     *
     * @param id 삭제할 기존 할 일의 고유 id
     */
    void deleteTask(Long id);
}
