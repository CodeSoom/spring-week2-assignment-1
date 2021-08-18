package com.codesoom.assignment;

import com.codesoom.assignment.errors.TaskIdNotFoundException;
import com.codesoom.assignment.models.Task;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TaskMap {

    private final Map<Long, Task> taskMap = new HashMap<>();

    public void insert(Long id, Task task) {
        taskMap.put(id, task);
    }

    public Collection<Task> getValues() {
        return taskMap.values();
    }

    public Task getWith(Long id) {
        return Optional.ofNullable(taskMap.get(id))
            .orElseThrow(TaskIdNotFoundException::new);
    }

    public Task update(Long id, String title) {
        Task task = getWith(id);

        task.setTitle(title);

        return task;
    }
}
