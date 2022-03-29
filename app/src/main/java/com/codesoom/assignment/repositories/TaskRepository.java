package com.codesoom.assignment.repositories;

import com.codesoom.assignment.domains.Task;
import com.codesoom.assignment.dtos.TaskDto;
import com.codesoom.assignment.exceptions.TaskNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class TaskRepository {
    private final Map<Long, Task> taskMap = new ConcurrentHashMap<>();
    private long sequence = 0L;

    public long incrementSequence() {
        return ++sequence;
    }

    public Task save(TaskDto taskDto) {
        Task task = new Task(incrementSequence(), taskDto.getTitle());
        taskMap.put(task.getId(), task);
        return task;
    }

    public Task find(Long id) {
        if(taskMap.containsKey(id)) {
            return taskMap.get(id);
        }

        throw new TaskNotFoundException("Task 를 찾을 수 없습니다.");
    }

    public void remove(long id) {
        Task task = find(id);
        taskMap.remove(task.getId());
    }

    public int count() {
        return taskMap.size();
    }

    public void removeAll() {
        taskMap.clear();
        sequence = 0L;
    }

    public List<Task> findAll() {
        return new ArrayList<>(taskMap.values());
    }

    public Task update(long id, String title) {
        Task task = find(id);
        task.setTitle(title);
        return task;
    }
}
