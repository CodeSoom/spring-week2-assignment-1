package com.codesoom.assignment.interfaces;

import com.codesoom.assignment.Task;
import com.codesoom.assignment.TaskRepositoryOutput;

import java.util.List;

public interface DefaultRepository {
    List<Task> tasksAll();

    Task taskBy(Long id);

    TaskRepositoryOutput output();
}
