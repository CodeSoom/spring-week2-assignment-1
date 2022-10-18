package com.codesoom.assignment.services;

import com.codesoom.assignment.models.BaseTask;
import com.codesoom.assignment.models.TaskDto;
import com.codesoom.assignment.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;

    public TaskServiceImpl(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public Collection<BaseTask> getAllTasks() {
        return repository.findAllTasks();
    }

    @Override
    public Optional<BaseTask> getTask(Long id) {
        final Optional<BaseTask> task = repository.findById(id);
        return task.or(Optional::empty);
    }

    @Override
    public BaseTask createNewTask(TaskDto dto) {
        return repository.addTask(dto);
    }

    @Override
    public Optional<BaseTask> changeTitle(TaskDto dto) {
        final Optional<BaseTask> task = repository.changeTitle(dto.toTask());
        return task.or(Optional::empty);
    }

    @Override
    public Optional<BaseTask> deleteTask(Long id) {
        final Optional<BaseTask> task = repository.deleteById(id);
        return task.or(Optional::empty);
    }
}
