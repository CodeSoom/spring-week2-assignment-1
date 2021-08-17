package com.codesoom.assignment;

import com.codesoom.assignment.models.Task;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TaskMap {

    private final Map<Long, Task> taskMap = new HashMap<>();

    public void put(Long lastId, Task task) {
        taskMap.put(lastId, task);
    }

    public Collection<Task> getValues() {
        return taskMap.values();
    }
}
