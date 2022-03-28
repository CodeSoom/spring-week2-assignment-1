package com.codesoom.assignment.service;


import com.codesoom.assignment.domain.Task;
import com.codesoom.assignment.dto.TaskDto;
import com.codesoom.assignment.dto.TaskSaveDto;
import com.codesoom.assignment.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<TaskDto> getTaskList() {
        Map<Long, Task> tasks = taskRepository.findAll();
        return tasks.values().stream()
                .map(TaskDto::new)
                .collect(Collectors.toList());
    }

    public TaskDto save(final TaskSaveDto taskSaveDto) {
        Task task = new Task(taskSaveDto.getTitle());
        taskRepository.save(task);
        return new TaskDto(task);
    }

    public Optional<TaskDto> getTask(final Long taskId) {
        Task task = taskRepository.findById(taskId);
        if (task == null) {
            return Optional.empty();
        }
        TaskDto taskDto = new TaskDto(task);
        return Optional.of(taskDto);
    }

    public boolean delete(Long taskId) {

        Task findTask = taskRepository.findById(taskId);

        if(findTask == null) {
            return false;
        }

        taskRepository.deleteById(findTask.getId());
        return true;
    }
}
