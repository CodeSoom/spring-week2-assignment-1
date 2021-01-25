package com.codesoom.assignment.repositories;

import com.codesoom.assignment.IdGenerator;
import com.codesoom.assignment.models.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class TaskRepository {

    private Map<Long, Task> taskMap = new ConcurrentHashMap<>();

    private final IdGenerator idGenerator;

    public TaskRepository(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    public List<Task> findAll() {
        return new ArrayList<>(taskMap.values());
    }

    public Task save(Task task) {
        Long newId = idGenerator.generateId();
        task.setId(newId);

        taskMap.put(task.getId(), task);

        return task;
    }

}
