package com.codesoom.assignment;

import com.codesoom.assignment.models.Task;
import java.util.Collection;
import org.springframework.stereotype.Service;

/**
 * 할 일 목록 관리를 담당합니다.
 */
@Service
public class TaskManager {

    private final TaskStorage taskStorage;

    private Long lastId = 0L;

    private TaskManager(TaskStorage taskStorage) {
        this.taskStorage = taskStorage;
    }

    public Task createTask(Task task) {
        Long lastId = getLastId();
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

    private Long getLastId() {
        increaseLastId();
        return lastId;
    }

    private synchronized void increaseLastId() {
        lastId++;
    }

}
