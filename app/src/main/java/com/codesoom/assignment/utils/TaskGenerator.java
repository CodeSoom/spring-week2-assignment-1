package com.codesoom.assignment.utils;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.models.TaskDto;

public interface TaskGenerator {

    Task generateNewTask(TaskDto dto);

    Task changeTitle(Task originalTask, String title);
}
