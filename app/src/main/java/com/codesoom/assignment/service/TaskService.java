package com.codesoom.assignment.service;

import com.codesoom.assignment.domain.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    private List<Task> tasks = new ArrayList<>();
    private long maxId = 0L;
    public List<Task> findAll() {
        return tasks;
    }

    public Task create(Task task) {
        task.setId(generateId());
        tasks.add(task);
        return task;
    }

    public Task update(Task task) {
        Task source = findTaskById(task.getId());
        source.setTitle(task.getTitle());
        return task;
    }

    public void delete(Long id) {
        Task target = findTaskById(id);
        tasks.remove(target);
    }

    /**
     * Task id 생성기
     * 0번부터 시작하게 하기 위해 후위 연산자를 사용
     * @return id
     */
    private Long generateId() {
        return maxId++;
    }

    private Task findTaskById(Long id) {
        return tasks.stream().filter(source -> source.getId() == id).findFirst().orElse(null);
    }

    public Task findOne(Long id) {
        return findTaskById(id);
    }
}
