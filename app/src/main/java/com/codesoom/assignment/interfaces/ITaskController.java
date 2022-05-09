package com.codesoom.assignment.interfaces;

import com.codesoom.assignment.controllers.dtos.TaskResponseDto;

import java.util.List;

public interface ITaskController {
    List<TaskResponseDto> showAll();

    TaskResponseDto showBy(long id);

    IControllerOutput output();
}
