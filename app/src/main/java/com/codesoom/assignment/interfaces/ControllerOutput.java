package com.codesoom.assignment.interfaces;

import com.codesoom.assignment.controllers.dtos.*;

public interface ControllerOutput {

    void create(TaskRequestDto requestDto);

    void update(Long id, TaskRequestDto requestDto);

    void deleteBy(Long id);

    TaskResponseDto responseDtoCreated();

    TaskResponseDto responseDtoUpdated();
}
