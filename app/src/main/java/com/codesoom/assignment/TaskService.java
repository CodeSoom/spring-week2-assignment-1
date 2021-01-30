package com.codesoom.assignment;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;

    }

    public List<Task> findAllTasks() {
        return new ArrayList<>(taskRepository.getTasks());
    }

    public Task findTask(Long id) {
        if (taskRepository.findTaskWithIdInTasks(id) == null) {
            throw new TaskIdNotFoundException();
        }
        return taskRepository.findTaskWithIdInTasks(id);
    }

    public Task createNewTask(Task task) {
        task.setId(taskRepository.generateId());
        taskRepository.createTask(task.getId(), task);
        return task;
    }

    public Task editTask(Long id, Task task) {
        findTask(id);
        Task editTask = task;
        editTask.setId(id);
        taskRepository.updateTask(task.getId(), editTask);
        return task;
    }

    public void removeTask(Long id) {
        Task task = findTask(id);
        taskRepository.deleteTask(id);
    }
}

