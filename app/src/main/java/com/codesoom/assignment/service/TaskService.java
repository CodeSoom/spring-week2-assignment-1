package com.codesoom.assignment.service;

import com.codesoom.assignment.domain.Task;
import com.codesoom.assignment.dto.TaskDto;
import com.codesoom.assignment.dto.TaskEditDto;
import com.codesoom.assignment.dto.TaskSaveDto;
import com.codesoom.assignment.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    public List<TaskDto> getTaskDtoList() {
        Map<Long, Task> tasks = taskRepository.findAll();
        return tasks.values().stream()
                .map(TaskDto::from)
                .collect(Collectors.toList());
    }

    public TaskDto save(final TaskSaveDto taskSaveDto) {
        Task task = new Task(taskSaveDto.getTitle());
        taskRepository.save(task);
        return TaskDto.from(task);
    }

    public Optional<Task> getTask(final Long taskId) {
        Task task = taskRepository.findById(taskId);
        if (task == null) {
            return Optional.empty();
        }
        return Optional.of(task);
    }

    public void delete(final Long taskId) {
        taskRepository.deleteById(taskId);
    }

    public void replaceTask(final Task task, final TaskEditDto taskEditDto) {
        task.setTitle(taskEditDto.getTitle());
    }

    public void modifyTask(final Task task, final TaskEditDto taskEditDto) {
        if (StringUtils.hasText(taskEditDto.getTitle())) {
            task.setTitle(taskEditDto.getTitle());
        }
    }
}
