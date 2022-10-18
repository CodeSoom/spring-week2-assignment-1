package com.codesoom.assignment.utils;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.models.TaskDto;

public interface TaskGenerator {

    Task generateTask(TaskDto dto);
}
