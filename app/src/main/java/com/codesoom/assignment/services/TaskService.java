package com.codesoom.assignment.services;

import com.codesoom.assignment.domains.Task;
import com.codesoom.assignment.domains.TaskDto;

import java.util.List;

/**
 *  할 일에 대한 CRUD를 정의한 인터페이스 입니다.
 */
public interface TaskService {

    List<Task> getTasks();

    Task addTask(TaskDto taskDto);

    Task findTaskById(Long id);

    Task updateTaskById(Long id, TaskDto taskDto);

    void deleteTaskById(Long id);

}
