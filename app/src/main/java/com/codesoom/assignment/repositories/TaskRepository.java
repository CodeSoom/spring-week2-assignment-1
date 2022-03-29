package com.codesoom.assignment.repositories;

import com.codesoom.assignment.domains.Task;

import java.util.Map;

public interface TaskRepository {

    Long generateId();

    Map<Long, Task> getTasks();

    Task save(Task task);

    Task findById(Long id);

    Task update(Long id, Task updatedTask);

    void remove(Long id);

}
