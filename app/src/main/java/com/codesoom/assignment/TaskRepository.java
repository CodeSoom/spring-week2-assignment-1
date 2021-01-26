package com.codesoom.assignment;

import com.codesoom.assignment.models.Task;

import java.util.HashMap;
import java.util.Map;

public class TaskRepository {

    public Map<Long, Task> taskStore = new HashMap<>();
    private Long newId = 0L;

    public Long generateId() {
        newId += 1;
        return newId;
    }

}
