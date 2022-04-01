package com.codesoom.assignment.services;

import com.codesoom.assignment.domains.Task;
import com.codesoom.assignment.exceptions.TaskNotFoundException;
import com.codesoom.assignment.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * 할 일을 조회하는 기능만 구현한 서비스입니다.
 */
@Service
public class TaskReadServiceImpl implements TaskReadService{

    private final TaskRepository repository;

    public TaskReadServiceImpl(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Task> getTasks() {
        return new ArrayList<>(repository.getTasks().values());
    }

    @Override
    public Task findTaskById(Long id) {
        final Task task = repository.findById(id);
        if (task == null) {
            throw new TaskNotFoundException(id);
        }
        return task;
    }

}
