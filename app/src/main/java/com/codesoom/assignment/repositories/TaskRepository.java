package com.codesoom.assignment.repositories;

import com.codesoom.assignment.models.Task;

import java.util.Collection;

public interface TaskRepository {

    Collection<Task> findAllTasks();

    Task findById(Long id);

    Task addTask(Task task);

    Task changeTitle(Long id, String newTitle);

    Task deleteById(Long id);
}
