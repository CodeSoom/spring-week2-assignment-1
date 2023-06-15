package com.codesoom.assignment.repositories;


import com.codesoom.assignment.models.Task;

import java.util.List;

public interface TaskRepository {

    Task insert(Task task);

    Task select(Long id);

    List<Task> selectAll();

    Task update(Task task);

    void delete(Long id);

}

