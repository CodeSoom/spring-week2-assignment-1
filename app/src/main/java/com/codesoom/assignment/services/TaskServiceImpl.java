package com.codesoom.assignment.services;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.models.TaskDto;
import com.codesoom.assignment.repositories.TaskRepository;
import com.codesoom.assignment.utils.TaskGenerator;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskGenerator taskGenerator;
    private final TaskRepository repository;

    public TaskServiceImpl(TaskGenerator taskGenerator, TaskRepository repository) {
        this.taskGenerator = taskGenerator;
        this.repository = repository;
    }

    @Override
    public Collection<TaskDto> getAllTasks() {
        return repository.findAllTasks().stream()
                .map(TaskDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TaskDto> getTask(Long id) {
        final Optional<Task> task = repository.findById(id);
        return task.map(TaskDto::new);
    }

    @Override
    public TaskDto createNewTask(TaskDto dto) {
        final Task task = taskGenerator.generateNewTask(dto);
        final Task addedTask = repository.addTask(task);
        return new TaskDto(addedTask);
    }

    @Override
    public Optional<TaskDto> changeTitle(TaskDto dto) {
        final Optional<Task> optionalTask = repository.findById(dto.getId());
        if (optionalTask.isEmpty()) {
            return Optional.empty();
        }

        final Task originalTask = optionalTask.get();
        final Task taskWithTitleChanged = taskGenerator.changeTitle(originalTask, dto.getTitle());
        final Task addedTask = repository.addTask(taskWithTitleChanged);
        return Optional.of(new TaskDto(addedTask));
    }

    @Override
    public Optional<TaskDto> deleteTask(Long id) {
        final Optional<Task> task = repository.deleteById(id);
        return task.map(TaskDto::new);
    }
}
