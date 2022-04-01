package com.codesoom.assignment.services;

import com.codesoom.assignment.domains.Task;
import com.codesoom.assignment.exceptions.TaskNotFoundException;
import com.codesoom.assignment.repositories.TaskRepository;
import org.springframework.stereotype.Service;


/** 할 일을 삭제하는 기능만 구현한 서비스 입니다. */
@Service
public class TaskDeleteServiceImpl implements TaskDeleteService{

    private final TaskRepository repository;

    public TaskDeleteServiceImpl(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public void deleteTaskById(Long id) {
        final Task task = findTaskById(id);
        repository.remove(task.getId());
    }

    /**
     * id로 할 일을 찾아 반환합니다.
     * @throws TaskNotFoundException
     *         id와 매핑되는 할 일을 찾지 못한 경우
     */
    private Task findTaskById(Long id) {
        final Task task = repository.findById(id);
        if (task == null) {
            throw new TaskNotFoundException(id);
        }
        return task;
    }

}
