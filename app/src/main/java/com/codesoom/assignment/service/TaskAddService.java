package com.codesoom.assignment.service;

import com.codesoom.assignment.dto.TaskDto;
import com.codesoom.assignment.dto.TaskForm;
import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.models.Title;
import com.codesoom.assignment.repository.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskAddService {

    private final TaskRepository taskRepository;

    public TaskAddService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskDto add(TaskForm taskForm) {
        Task task = taskRepository.save(new Title(taskForm.getTitle()));
        return new TaskDto(task.getId(), task.getTitle().getValue());
    }
}
