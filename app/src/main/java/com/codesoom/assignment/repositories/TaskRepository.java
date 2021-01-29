package com.codesoom.assignment.repositories;

import com.codesoom.assignment.models.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
    private List<Task> tasks = new ArrayList<>();

    public List<Task> getTasks() {
        return tasks;
    }
}
