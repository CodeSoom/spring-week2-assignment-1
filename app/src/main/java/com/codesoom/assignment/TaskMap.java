package com.codesoom.assignment;

import com.codesoom.assignment.errors.TaskIdNotFoundException;
import com.codesoom.assignment.models.Task;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TaskMap {

    private final Map<Long, Task> taskMap = new HashMap<>();

    public void insert(Long lastId, Task task) {
        taskMap.put(lastId, task);
    }

    public Collection<Task> getValues() {
        return taskMap.values();
    }

    public Task getWith(Long id) {
        return Optional.ofNullable(taskMap.get(id))
            .orElseThrow(TaskIdNotFoundException::new);
    }
}
