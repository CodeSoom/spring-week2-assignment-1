package com.codesoom.assignment.application;

import com.codesoom.assignment.dto.TaskDto;
import com.codesoom.assignment.exception.TaskBadRequestException;
import com.codesoom.assignment.exception.TaskNotFoundException;
import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.repository.TaskRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getTasks() {
        return this.taskRepository.findAll();
    }

    public Task getTask(Long id) throws TaskNotFoundException {
        return this.taskRepository.findOneById(id)
                .orElseThrow(() -> { throw new TaskNotFoundException(id); });
    }

    public Task createNewTask(TaskDto taskDto) throws TaskBadRequestException {
        return this.taskRepository.create(taskDto.getTitle());
    }

    public Task updateTaskById(TaskDto taskDto) throws TaskNotFoundException {
        Task task = this.taskRepository.findOneById(taskDto.getId())
                .orElseThrow(() -> {
                    throw new TaskNotFoundException(taskDto.getId(), "UPDATE");
                });
        return this.taskRepository.update(task, taskDto.getTitle());
    }

    public void deleteTaskById(Long id) throws TaskNotFoundException {
        Task task = this.taskRepository.findOneById(id)
                .orElseThrow(() -> {
                    throw new TaskNotFoundException(id, "DELETE");
                });
        this.taskRepository.remove(task);
        return;
    }

}
