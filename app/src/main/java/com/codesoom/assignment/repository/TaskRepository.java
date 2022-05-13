package com.codesoom.assignment.repository;

import com.codesoom.assignment.models.Task;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class TaskRepository {
    private static final Map<Long, Task> tasks = new HashMap<>();
    private static Long sequence = 1L;

    private static final TaskRepository instance = new TaskRepository();

    public static TaskRepository getInstance() {
        return instance;
    }

    public TaskRepository() {
    }

    public List<Task> findAll() {
        return new ArrayList<>(tasks.values());
    }

    public Task findById(Long id) {
        // id 에 대한 Task 가 있다면 return tasks.get(id)
        // id 에 대한 Task 가 없다면 return "할 일이 없습니다."
        return tasks.values().stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("TaskId(" + id + ") can not be found"));
    }

    public Task save(Task task) {
        task.setId(sequence);
        tasks.put(task.getId(), task);

        sequence += 1;
        return task;
    }

    public Task update(Long id, Task newTask) {
        newTask.setId(id);
        tasks.put(newTask.getId(), newTask);
        return newTask;
    }

    public void delete(Long id) {
        tasks.remove(id);
    }
}
