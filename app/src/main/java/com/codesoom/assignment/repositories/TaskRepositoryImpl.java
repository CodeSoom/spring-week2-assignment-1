package com.codesoom.assignment.repositories;

import com.codesoom.assignment.utils.TaskIdGenerator;
import com.codesoom.assignment.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class TaskRepositoryImpl implements TaskRepository {

    @Autowired
    private final TaskIdGenerator idGenerator;
    private final Map<Long, Task> taskMap = new ConcurrentHashMap<>();

    public TaskRepositoryImpl(TaskIdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    public Collection<Task> findAllTasks() {
        return taskMap.values();
    }

    public Task findById(Long id) {
        return taskMap.get(id);
    }

    public Task addTask(Task task) {
        task.setId(idGenerator.allocateId());
        taskMap.put(task.getId(), task);
        return task;
    }

    public Task changeTitle(Long id, String newTitle) {
        final Task originalTask = taskMap.get(id);
        if (originalTask == null) {
            return null;
        }

        originalTask.setTitle(newTitle);
        return originalTask;
    }

    public Task deleteById(Long id) {
        return taskMap.remove(id);
    }
}
