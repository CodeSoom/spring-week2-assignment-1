package com.codesoom.assignment.services;

import com.codesoom.assignment.repositories.TaskStorage;
import com.codesoom.assignment.models.Task;
import java.util.Collection;
import org.springframework.stereotype.Service;

/**
 * 할 일 목록 관리를 담당합니다.
 */
@Service
public class TaskManager {

    private final TaskStorage taskStorage;
    private final TaskIdGenerator taskIdGenerator;

    public TaskManager(TaskStorage taskStorage,
        TaskIdGenerator taskLastIdManager) {
        this.taskStorage = taskStorage;
        this.taskIdGenerator = taskLastIdManager;
    }

    public Task createTask(Task task) {
        Long lastId = taskIdGenerator.getLastId();
        task.setId(lastId);

        taskStorage.insert(lastId, task);

        return task;
    }

    public Collection<Task> getAllTasks() {
        return taskStorage.getValues();
    }

    public Task getTaskWith(Long id) {
        return taskStorage.get(id);
    }

    public Task updateTask(Long id, Task content) {
        return taskStorage.update(id, content.getTitle());
    }

    public void deleteTask(Long id) {
        taskStorage.delete(id);
    }
}
