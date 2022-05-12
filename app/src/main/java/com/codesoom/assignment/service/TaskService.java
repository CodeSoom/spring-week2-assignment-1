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

    public Task findOne(Long id) {
        return findTaskById(id);
    }

    public Task create(Task task) {
        task.setId(generateId());
        tasks.add(task);
        return task;
    }

    public Task update(Long id, Task task) {
        Task source = findTaskById(id);
        source.setTitle(task.getTitle());
        return task;
    }

    public void delete(Long id) {
        Task target = findTaskById(id);
        tasks.remove(target);
    }

    /**
     * Task id 생성
     * @return id
     */
    private Long generateId() {
        this.maxId += 1;
        return this.maxId;
    }

    /**
     * Task id로 해당되는 task를 조회함
     * @param id
     * @return
     */
    private Task findTaskById(Long id) {
        return tasks.stream()
                .filter(source -> source.getId() == id)
                .findFirst()
                .orElse(new Task());
    }
}
