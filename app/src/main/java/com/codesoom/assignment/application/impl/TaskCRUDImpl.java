package com.codesoom.assignment.application.impl;

import com.codesoom.assignment.application.TaskCRUD;
import com.codesoom.assignment.dto.TaskDto;
import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.repository.TaskRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TaskCRUDImpl implements TaskCRUD {

    private final TaskRepository taskRepository;

    public TaskCRUDImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task getTask(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }

    @Override
    public synchronized Task createTask(String title) {
        List<Task> tasks = taskRepository.findAll();
        TaskDto taskDto = TaskDto.from((long) (tasks.size() + 1), title);
        Task task = Task.from(taskDto);
        System.out.println(task.toString());
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
