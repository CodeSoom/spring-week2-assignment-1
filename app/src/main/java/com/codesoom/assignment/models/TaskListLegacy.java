package com.codesoom.assignment.models;


import java.util.ArrayList;
import java.util.List;

public class TaskListLegacy {
    private final List<Task> taskList = new ArrayList<>();
    private int newId = 0;
    public int getId() {
        return newId;
    }
    public void add(Task task) {
        task.updateId(generatedId());
        taskList.add(task);
    }

    private int generatedId() {
        return newId += 1;
    }
}
