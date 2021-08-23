package com.codesoom.assignment.services;

import com.codesoom.assignment.repositories.TaskRepository;
import com.codesoom.assignment.models.Task;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * 할 일 목록 관리를 담당합니다.
 */
@Service
public class TaskManager {

    private final TaskRepository taskRepository;
    private final TaskIdGenerator taskIdGenerator;

    public TaskManager(TaskRepository taskRepository,
        TaskIdGenerator taskLastIdManager) {
        this.taskRepository = taskRepository;
        this.taskIdGenerator = taskLastIdManager;
    }

    public Task createTask(Task source) {
        Long lastId = taskIdGenerator.getLastId();
        Task task = new Task(lastId, source.getTitle());

        taskRepository.save(lastId, task);

        return task;
    }

    public List<Task> getAllTasks() {
        return taskRepository.getAllTasks();
    }

    public Task getTaskWith(Long id) {
        return taskRepository.get(id);
    }

    public Task updateTask(Long id, Task content) {
        return taskRepository.update(id, content.getTitle());
    }

    public void deleteTask(Long id) {
        taskRepository.delete(id);
    }
}
