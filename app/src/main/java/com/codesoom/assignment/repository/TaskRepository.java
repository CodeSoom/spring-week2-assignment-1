package com.codesoom.assignment.repository;

import com.codesoom.assignment.models.Task;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 할 일을 Map 형태로 저장하고, 그 저장소에 접근하는 코드를 모아둔 클래스
 *
 * @author developerOlive
 * @version 1.0.0 21/01/30
 */

@Repository
public class TaskRepository {

    public Map<Long, Task> taskStore = Collections.synchronizedMap(new LinkedHashMap<>());
    AtomicLong idCounter = new AtomicLong(0);

    public Long generateId() {
        return idCounter.getAndIncrement();
    }

    public Collection<Task> getTasks() {
        return taskStore.values();
    }

    public Task findTaskWithIdInTasks(Long id) {
        return taskStore.get(id);
    }

    public Task createTask(Long id, Task task) {
        return taskStore.put(task.getId(), task);
    }

    public Task updateTask(Long id, Task task) {
        return taskStore.replace(task.getId(), task);
    }

    public void deleteTask(Long id) {
        taskStore.remove(id);
    }
}
