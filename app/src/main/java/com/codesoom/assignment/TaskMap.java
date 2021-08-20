package com.codesoom.assignment;

import com.codesoom.assignment.errors.TaskIdNotFoundException;
import com.codesoom.assignment.models.Task;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 등록된 할 일들을 관리합니다.
 */
public class TaskMap {

    private final Map<Long, Task> taskMap = new HashMap<>();

    public void insert(Long id, Task task) {
        taskMap.put(id, task);
    }

    public Collection<Task> getValues() {
        return taskMap.values();
    }

    public Task get(Long id) {
        return findWith(id);
    }

    public Task update(Long id, String title) {
        Task task = findWith(id);

        task.setTitle(title);

        return task;
    }

    public void delete(Long id) {
        findWith(id);

        taskMap.remove(id);
    }

    private Task findWith(Long id) {
        return Optional.ofNullable(taskMap.get(id))
            .orElseThrow(() -> new TaskIdNotFoundException(id));
    }
}
