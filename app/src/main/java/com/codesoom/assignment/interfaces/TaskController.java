package com.codesoom.assignment.interfaces;

import com.codesoom.assignment.controllers.dtos.TaskRequestDto;
import com.codesoom.assignment.controllers.dtos.TaskResponseDto;

import java.util.List;

public interface TaskController {
    List<TaskResponseDto> showAll();

    TaskResponseDto showBy(Long id);

    TaskResponseDto create(TaskRequestDto requestDto);

    TaskResponseDto update(Long id, TaskRequestDto requestDto);

    void deleteBy(Long id);
}
