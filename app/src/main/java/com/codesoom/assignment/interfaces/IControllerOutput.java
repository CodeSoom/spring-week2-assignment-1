package com.codesoom.assignment.interfaces;

import com.codesoom.assignment.controllers.dtos.*;

public interface IControllerOutput {

    void create(TaskRequestDtoCreating requestDto);

    void update(TaskRequestDtoUpdating requestDto);

    void deleteBy(long id);

    TaskResponseDto responseDtoCreated();

    TaskResponseDto responseDtoUpdated();
}
