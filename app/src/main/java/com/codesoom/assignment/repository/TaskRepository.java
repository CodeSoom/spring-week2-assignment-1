package com.codesoom.assignment.repository;

import com.codesoom.assignment.domain.Task;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class TaskRepository {

    private final Map<Long, Task> tasks = new HashMap<>();

    public Map<Long, Task> findAll() {
        return tasks;
    }
}
