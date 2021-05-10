package com.codesoom.assignment.repositories;

import com.codesoom.assignment.dto.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskRepository {

    private final List<Task> tasks;

    public TaskRepository() {
        this.tasks = new ArrayList<>();
    }

    public List<Task> findAll() {
        return this.tasks;
    }
}
