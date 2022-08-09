package com.codesoom.assignment.application.impl;

import com.codesoom.assignment.application.ITaskService;
import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.repository.ITaskRepository;
import com.codesoom.assignment.repository.impl.TaskRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TaskService implements ITaskService {

    private final ITaskRepository taskRepository = new TaskRepository();

    @Override
    public Task getTask(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Long oldTaskId, Task task) {
        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.delete(id);
    }
}
