package com.codesoom.assignment.interfaces;

import com.codesoom.assignment.Task;
import com.codesoom.assignment.controllers.RepositoryOutput;

import java.util.List;

public interface TaskRepository {
    List<Task> tasksAll();

    Task taskBy(Long id);

    RepositoryOutput output();
}
