package com.codesoom.assignment.interfaces;

import com.codesoom.assignment.Task;
import com.codesoom.assignment.controllers.dtos.TaskRequestDtoCreating;
import com.codesoom.assignment.controllers.dtos.TaskRequestDtoUpdating;
import com.codesoom.assignment.controllers.dtos.TaskResponseDto;

public interface IRepositoryOutput {

    void save(Task task);

    void update(long oldTaskId, Task newTask);

    void deleteBy(long id);


    Task taskSaved();

    Task taskUpdated();
}
