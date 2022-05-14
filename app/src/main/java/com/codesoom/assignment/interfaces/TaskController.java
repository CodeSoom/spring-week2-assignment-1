package com.codesoom.assignment.interfaces;

import com.codesoom.assignment.controllers.dtos.TaskRequestDto;
import com.codesoom.assignment.controllers.dtos.TaskResponseDto;

import java.util.List;

/**
 * Task 객체에 연관된 HTTP CRUD 요청을 받고, 처리결과를 응답으로 반환
 * <p>
 * All Known Implementing Classes:
 * TaskCrudController
 * </p>
 */
public interface TaskController {
    /**
     * 목록 조회 요청에 따른 처리 결과를 List<TaskResponseDto> 형태로 가공하여 반환
     * <p>
     * Method Returns:
     * List<TaskResponseDto> - HTTP Request를 처리한 결과를 JSON 객체로 역직렬화하기 위한 객체
     * </p>
     */
    List<TaskResponseDto> showAll();

    /**
     * 상세 조히 요청에 따른 처리 결과를 TaskResponseDto 형태로 가공하여 반환
     * <p>
     * Method Parameters:
     * Long id - Request Path Parameter 전달된 Task Id를 받기 위한 객체
     * </p>
     * <p>
     * Method Returns:
     * TaskResponseDto - HTTP Request를 처리한 결과를 JSON 객체로 역직렬화하기 위한 객체
     * </p>
     */
    TaskResponseDto showBy(Long id);

    /**
     * 생성 요청에 따른 처리 결과를 TaskResponseDto 형태로 가공하여 반환
     * <p>
     * Method Parameters:
     * TaskRequestDto requestDto - Request Body로 전달된 JSON 객체를 직렬화하여 받기 위한 객체
     * </p>
     * <p>
     * Method Returns:
     * TaskResponseDto - HTTP Request를 처리한 결과를 JSON 객체로 역직렬화하기 위한 객체
     * </p>
     */
    TaskResponseDto create(TaskRequestDto requestDto);

    /**
     * 수정 요청에 따른 처리 결과를 TaskResponseDto 형태로 가공하여 반환
     * <p>
     * Method Parameters:
     * TaskRequestDto requestDto - Request Body로 전달된 JSON 객체를 직렬화하여 받기 위한 객체
     * Long id - Request Path Parameter 전달된 Task Id를 받기 위한 객체
     * </p>
     * <p>
     * Method Returns:
     * TaskResponseDto - HTTP Request를 처리한 결과를 JSON 객체로 역직렬화하기 위한 객체
     * </p>
     */
    TaskResponseDto update(Long id, TaskRequestDto requestDto);

    /**
     * 삭제 요청에 따른 처리 결과를 TaskResponseDto 형태로 가공하여 반환
     * <p>
     * Method Parameters:
     * Long id - Request Path Parameter 전달된 Task Id를 받기 위한 객체
     * </p>
     * <p>
     * Method Returns:
     * TaskResponseDto - HTTP Request를 처리한 결과를 JSON 객체로 역직렬화하기 위한 객체
     * </p>
     */
    void deleteBy(Long id);
}
