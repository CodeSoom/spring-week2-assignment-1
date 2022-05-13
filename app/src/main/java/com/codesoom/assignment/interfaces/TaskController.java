package com.codesoom.assignment.interfaces;

import com.codesoom.assignment.controllers.dtos.TaskRequestDto;
import com.codesoom.assignment.controllers.dtos.TaskResponseDto;

import java.util.List;

/**
 * Summary:
 * Task 객체에 연관된 HTTP CRUD 요청을 받고, 처리결과를 응답으로 반환
 * <p>
 * All Known Implementing Classes:
 * TaskCrudController
 * <p>
 * Method Parameters:
 * TaskRequestDto requestDto - Request Body로 전달된 JSON 객체를 직렬화하여 받기 위한 객체
 * Long id - Request Path Parameter 전달된 Task Id를 받기 위한 객체
 * <p>
 * Method Returns:
 * TaskResponseDto - HTTP Request를 처리한 결과를 JSON 객체로 역직렬화하기 위한 객체
 */
public interface TaskController {
    List<TaskResponseDto> showAll();

    TaskResponseDto showBy(Long id);

    TaskResponseDto create(TaskRequestDto requestDto);

    TaskResponseDto update(Long id, TaskRequestDto requestDto);

    void deleteBy(Long id);
}
