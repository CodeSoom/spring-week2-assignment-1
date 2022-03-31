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


    /**
     * id로 할 일을 찾아 반환한다.
     *
     * @param id 요청받은 id
     * @return 요청받은 id로 찾은 할 일
     * @throws TaskNotFoundException id와 일치하는 값이 없을 때
     */
    @Override
    public Task findTaskById(Long id) {
        final Task task = repository.findById(id);
        if (task == null) {
            throw new TaskNotFoundException(id);
        }
        return task;
    }
}
