package com.codesoom.assignment.service;

import com.codesoom.assignment.dto.TaskDto;
import com.codesoom.assignment.entity.Task;
import com.codesoom.assignment.exception.TaskNotFoundException;
import com.codesoom.assignment.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    public Task getTask(Long id) {
        Task task = taskRepository.findById(id).orElse(null);
        if (Objects.isNull(task)) {
            throw new TaskNotFoundException("id에 해당하는 Task가 없습니다.");
        }
        return task;
    }

    public Task addTask(TaskDto taskDto) {
        Task task = new Task();
        task.setId(taskDto.getId());
        task.setTitle(taskDto.getTitle());
        return taskRepository.addTask(task);
    }

    public Task updateTask(Long id, TaskDto inputTaskDto) {
        Task task = taskRepository.findById(id).orElse(null);
        if (Objects.isNull(task)) {
            throw new TaskNotFoundException("수정할 Task가 없습니다.");
        }
        task.setTitle(inputTaskDto.getTitle());
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException("삭제할 Task가 없습니다.");
        }
        taskRepository.deleteById(id);
    }

}
